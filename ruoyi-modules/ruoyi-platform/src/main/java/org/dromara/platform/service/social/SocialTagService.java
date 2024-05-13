package org.dromara.platform.service.social;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.constant.RedisKey;
import org.dromara.platform.domain.social.SocialTag;
import org.dromara.platform.domain.social.bo.SocialTagBo;
import org.dromara.platform.mapper.SocialTagMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.platform.domain.social.vo.SocialSubjectVo;
import org.dromara.platform.domain.social.vo.SocialTagVo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 标签信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialTagService implements IBaseService<SocialTag, SocialTagVo, SocialTagBo> {

    private final SocialTagMapper socialTagMapper;
    private final SocialSubjectService socialSubjectService;

    @Override
    public IBaseMapper<SocialTag> mapper() {
        return socialTagMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialTag> buildQueryWrapper(SocialTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialTag> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialTag::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getSubjectId()), SocialTag::getSubjectId, bo.getSubjectId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getSubjectIds()), SocialTag::getSubjectId, bo.getSubjectIds());
        lqw.like(StringUtils.isNotBlank(bo.getTagCode()), SocialTag::getTagCode, bo.getTagCode());
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), SocialTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagType()), SocialTag::getTagType, bo.getTagType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SocialTag::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增标签信息
     */
    @Override
    public Boolean insertByBo(SocialTagBo bo) {
        SocialTag add = MapstructUtils.convert(bo, SocialTag.class);
        if (add == null) {
            return false;
        }

        SocialSubjectVo socialSubjectVo = socialSubjectService.queryById(bo.getSubjectId());
        if (socialSubjectVo == null) {
            return false;
        }

        add.setAppId(socialSubjectVo.getAppId());
        boolean flag = socialTagMapper.insert(add) > 0;

        if (flag) {
            bo.setTagId(add.getTagId());
            CacheUtils.put(RedisKey.SOCIAL_TAG_ID_NAME, add.getTagId() + "", bo.getTagName());
            CacheUtils.put(RedisKey.SOCIAL_TAG_CODE_NAME, add.getTagCode(), bo.getTagName());
        }
        return flag;
    }

    /**
     * 修改标签信息
     */
    @Override
    public Boolean updateByBo(SocialTagBo bo) {
        SocialTag update = MapstructUtils.convert(bo, SocialTag.class);
        if (update == null) {
            return false;
        }

        SocialSubjectVo socialSubjectVo = socialSubjectService.queryById(bo.getSubjectId());
        if (socialSubjectVo == null) {
            return false;
        }
        update.setAppId(socialSubjectVo.getAppId());
        boolean bool = socialTagMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.SOCIAL_TAG_ID_NAME, update.getTagId() + "", update.getTagName());
            CacheUtils.put(RedisKey.SOCIAL_TAG_CODE_NAME, update.getTagCode(), update.getTagName());
        }
        return bool;
    }

    /**
     * 修改标签通知状态
     *
     * @param tagId  标签ID
     * @param status 状态
     * @return 结果
     */
    public int updateStatus(Long tagId, String status) {
        return socialTagMapper.update(null,
            new LambdaUpdateWrapper<SocialTag>().set(SocialTag::getStatus, status).eq(SocialTag::getTagId, tagId));
    }

    /**
     * 批量删除标签信息
     */
    @Override
    public Boolean deleteByIds(Collection<Long> ids) {
        List<SocialTag> socialTags = socialTagMapper.selectBatchIds(ids);
        boolean bool = socialTagMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialTags.forEach(o -> {
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getTagId() + "");
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getTagCode());
            });
        }
        return bool;
    }

}
