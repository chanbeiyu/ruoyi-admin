package org.dromara.platform.service.social;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.SocialNotice;
import org.dromara.platform.domain.bo.SocialNoticeBo;
import org.dromara.platform.mapper.SocialNoticeMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.social.SocialNoticeVo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 信息通知Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialNoticeService implements IBaseService<SocialNotice, SocialNoticeVo, SocialNoticeBo> {

    private final SocialNoticeMapper socialNoticeMapper;

    @Override
    public IBaseMapper<SocialNotice> mapper() {
        return socialNoticeMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialNotice> buildQueryWrapper(SocialNoticeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialNotice> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialNotice::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialNotice::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialNotice::getMemberId, bo.getMemberId());
        lqw.eq(bo.getTriggerMemberId() != null, SocialNotice::getTriggerMemberId, bo.getTriggerMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getTriggerId()), SocialNotice::getTriggerId, bo.getTriggerId());
        lqw.eq(StringUtils.isNotBlank(bo.getTriggerContent()), SocialNotice::getTriggerContent, bo.getTriggerContent());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), SocialNotice::getTypeCode, bo.getTypeCode());
        lqw.eq(bo.getStatus() != null, SocialNotice::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginSendTime"), params.get("endSendTime")),
            SocialNotice::getSendTime, params.get("beginSendTime"), params.get("endSendTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginReadTime"), params.get("endReadTime")),
            SocialNotice::getReadTime, params.get("beginReadTime"), params.get("endReadTime"));
        return lqw;
    }

}
