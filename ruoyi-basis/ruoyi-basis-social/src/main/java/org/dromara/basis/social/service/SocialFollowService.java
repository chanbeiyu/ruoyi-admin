package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialFollowBo;
import org.dromara.basis.social.entity.SocialFollow;
import org.dromara.basis.social.mapper.SocialFollowMapper;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 关注信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialFollowService implements IBaseService<SocialFollow, SocialFollowBo> {

    private final SocialFollowMapper socialFollowMapper;

    @Override
    public IBaseMapper<SocialFollow> mapper() {
        return socialFollowMapper;
    }

    @Override
    public LambdaQueryWrapper<SocialFollow> buildQueryWrapper(SocialFollowBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialFollow> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialFollow::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialFollow::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialFollow::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialFollow::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getStatus() != null, SocialFollow::getStatus, bo.getStatus());
        lqw.eq(bo.getUnfollowTime() != null, SocialFollow::getUnfollowTime, bo.getUnfollowTime());
        return lqw;
    }

}
