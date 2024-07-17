package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
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
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeCode()), SocialNoticeType::getNoticeTypeCode,
            bo.getNoticeTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeName()), SocialNoticeType::getNoticeTypeName,
            bo.getNoticeTypeName());
        lqw.like(StringUtils.isNotBlank(bo.getStatus()), SocialNoticeType::getStatus, bo.getStatus());
        return lqw;
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

}
