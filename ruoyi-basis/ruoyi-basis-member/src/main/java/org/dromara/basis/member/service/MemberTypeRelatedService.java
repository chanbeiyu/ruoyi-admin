package org.dromara.basis.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberTypeRelatedBo;
import org.dromara.basis.member.entity.MemberTypeRelated;
import org.dromara.basis.member.mapper.MemberTypeRelatedMapper;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员类型关联信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberTypeRelatedService
    implements IBaseService<MemberTypeRelated, MemberTypeRelatedBo> {

    private final MemberTypeRelatedMapper memberTypeRelatedMapper;

    @Override
    public IBaseMapper<MemberTypeRelated> mapper() {
        return memberTypeRelatedMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberTypeRelated> buildQueryWrapper(MemberTypeRelatedBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberTypeRelated> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, MemberTypeRelated::getMemberId, bo.getMemberId());
        lqw.eq(bo.getMemberTypeId() != null, MemberTypeRelated::getMemberTypeId, bo.getMemberTypeId());
        return lqw;
    }

}
