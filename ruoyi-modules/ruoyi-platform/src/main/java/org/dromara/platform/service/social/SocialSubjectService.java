package org.dromara.platform.service.social;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.constant.RedisKey;
import org.dromara.platform.domain.SocialSubject;
import org.dromara.platform.domain.bo.SocialSubjectBo;
import org.dromara.platform.mapper.SocialSubjectMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.platform.domain.vo.social.SocialSubjectVo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 内容主题Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Service
@RequiredArgsConstructor
public class SocialSubjectService implements IBaseService<SocialSubject, SocialSubjectVo, SocialSubjectBo> {

    private final SocialSubjectMapper socialSubjectMapper;

    @Override
    public IBaseMapper<SocialSubject> mapper() {
        return socialSubjectMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialSubject> buildQueryWrapper(SocialSubjectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialSubject> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialSubject::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialSubject::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getSubjectCode()), SocialSubject::getSubjectCode, bo.getSubjectCode());
        lqw.like(StringUtils.isNotBlank(bo.getSubjectName()), SocialSubject::getSubjectName, bo.getSubjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SocialSubject::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增内容主题
     */
    @Override
    public Boolean insertByBo(SocialSubjectBo bo) {
        SocialSubject add = MapstructUtils.convert(bo, SocialSubject.class);
        boolean flag = socialSubjectMapper.insert(add) > 0;
        if (flag) {
            bo.setSubjectId(add.getSubjectId());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_ID_NAME, add.getSubjectId() + "", bo.getSubjectName());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_CODE_NAME, add.getSubjectCode(), bo.getSubjectName());
        }
        return flag;
    }

    /**
     * 修改内容主题
     */
    @Override
    public Boolean updateByBo(SocialSubjectBo bo) {
        SocialSubject update = MapstructUtils.convert(bo, SocialSubject.class);
        boolean bool = socialSubjectMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_ID_NAME, update.getSubjectId() + "", update.getSubjectName());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_CODE_NAME, update.getSubjectCode(), update.getSubjectName());
        }
        return bool;
    }

    /**
     * 修改主题通知状态
     *
     * @param subjectId 主题ID
     * @param status    状态
     * @return 结果
     */
    public int updateStatus(Long subjectId, String status) {
        return socialSubjectMapper.update(null,
            new LambdaUpdateWrapper<SocialSubject>().set(SocialSubject::getStatus, status)
                .eq(SocialSubject::getSubjectId, subjectId));
    }

    /**
     * 批量删除内容主题
     */
    @Override
    public Boolean deleteByIds(Collection<Long> ids) {
        List<SocialSubject> socialSubjects = socialSubjectMapper.selectBatchIds(ids);
        boolean bool = socialSubjectMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialSubjects.forEach(o -> {
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getSubjectId() + "");
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getSubjectCode());
            });
        }
        return bool;
    }
}
