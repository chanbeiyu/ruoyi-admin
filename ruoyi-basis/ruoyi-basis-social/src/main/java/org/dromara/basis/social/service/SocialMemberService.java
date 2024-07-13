package org.dromara.basis.social.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialMemberBo;
import org.dromara.basis.social.entity.SocialMember;
import org.dromara.basis.social.mapper.SocialMemberMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 成员信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialMemberService implements IBaseService<SocialMember, SocialMemberBo> {

    private final SocialMemberMapper socialMemberMapper;

    @Override
    public IBaseMapper<SocialMember> mapper() {
        return socialMemberMapper;
    }


    @Override
    public LambdaQueryWrapper<SocialMember> buildQueryWrapper(SocialMemberBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialMember> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUnionId()), SocialMember::getUnionId, bo.getUnionId());
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialMember::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialMember::getAppId, bo.getAppIds());
        lqw.eq(bo.getVipLevel() != null, SocialMember::getVipLevel, bo.getVipLevel());
        lqw.eq(bo.getPoints() != null, SocialMember::getPoints, bo.getPoints());
        lqw.eq(bo.getPointsLevel() != null, SocialMember::getPointsLevel, bo.getPointsLevel());
        lqw.eq(bo.getStatus() != null, SocialMember::getStatus, bo.getStatus());
        return lqw;
    }

}
