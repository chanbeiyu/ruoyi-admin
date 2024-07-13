package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.constant.RedisKey;
import org.dromara.basis.social.bo.SocialNoticeTypeBo;
import org.dromara.basis.social.entity.SocialNoticeType;
import org.dromara.basis.social.mapper.SocialNoticeTypeMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 信息通知类型Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialNoticeTypeService implements IBaseService<SocialNoticeType, SocialNoticeTypeBo> {

    private final SocialNoticeTypeMapper socialNoticeTypeMapper;

    @Override
    public IBaseMapper<SocialNoticeType> mapper() {
        return socialNoticeTypeMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialNoticeType> buildQueryWrapper(SocialNoticeTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialNoticeType> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialNoticeType::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialNoticeType::getAppId, bo.getAppIds());
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeCode()), SocialNoticeType::getNoticeTypeCode,
            bo.getNoticeTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeName()), SocialNoticeType::getNoticeTypeName,
            bo.getNoticeTypeName());
        lqw.like(StringUtils.isNotBlank(bo.getStatus()), SocialNoticeType::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增信息通知类型
     */
    @Override
    public Boolean insertByBo(SocialNoticeTypeBo bo) {
        SocialNoticeType add = MapstructUtils.convert(bo, SocialNoticeType.class);
        boolean flag = socialNoticeTypeMapper.insert(add) > 0;
        if (flag) {
            bo.setNoticeTypeId(add.getNoticeTypeId());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, add.getNoticeTypeId() + "", bo.getNoticeTypeName());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, add.getNoticeTypeCode(), bo.getNoticeTypeName());
        }
        return flag;
    }

    /**
     * 修改信息通知类型
     */
    @Override
    public Boolean updateByBo(SocialNoticeTypeBo bo) {
        SocialNoticeType update = MapstructUtils.convert(bo, SocialNoticeType.class);
        boolean bool = socialNoticeTypeMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, update.getNoticeTypeId() + "",
                update.getNoticeTypeName());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, update.getNoticeTypeCode(), update.getNoticeTypeName());
        }
        return bool;
    }

    /**
     * 修改信息通知状态
     *
     * @param noticeId 信息通知ID
     * @param status   状态
     * @return 结果
     */
    public int updateStatus(Long noticeId, String status) {
        return socialNoticeTypeMapper.update(null,
            new LambdaUpdateWrapper<SocialNoticeType>().set(SocialNoticeType::getStatus, status)
                .eq(SocialNoticeType::getNoticeTypeId, noticeId));
    }

    /**
     * 批量删除信息通知类型
     */
    @Override
    public Boolean deleteByIds(Collection<Serializable> ids) {
        List<SocialNoticeType> socialNoticeTypes = socialNoticeTypeMapper.selectBatchIds(ids);
        boolean bool = socialNoticeTypeMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialNoticeTypes.forEach(o -> {
                CacheUtils.evict(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, o.getNoticeTypeId() + "");
                CacheUtils.evict(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, o.getNoticeTypeCode());
            });
        }
        return bool;
    }
}
