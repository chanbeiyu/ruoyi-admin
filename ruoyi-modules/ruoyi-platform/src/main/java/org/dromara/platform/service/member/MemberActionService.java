package org.dromara.platform.service.member;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.member.MemberAction;
import org.dromara.platform.domain.member.bo.MemberActionBo;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.member.vo.MemberActionVo;
import org.dromara.platform.mapper.MemberActionMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 会员积分Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberActionService implements IBaseService<MemberAction, MemberActionVo, MemberActionBo> {

    private final MemberActionMapper memberActionMapper;

    @Override
    public IBaseMapper<MemberAction> mapper() {
        return memberActionMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberAction> buildQueryWrapper(MemberActionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberAction> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberAction::getAppId, bo.getAppId());
        lqw.eq(CollectionUtils.isNotEmpty(bo.getAppIds()), MemberAction::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getActionCode()), MemberAction::getActionCode, bo.getActionCode());
        lqw.like(StringUtils.isNotBlank(bo.getActionName()), MemberAction::getActionName, bo.getActionName());
        lqw.eq(Objects.nonNull(bo.getStatus()), MemberAction::getStatus, bo.getStatus());
        return lqw;
    }


}
