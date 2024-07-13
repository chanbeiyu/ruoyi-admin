package org.dromara.basis.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberPointsRecordBo;
import org.dromara.basis.member.entity.MemberPointsRecord;
import org.dromara.basis.member.mapper.MemberPointsRecordMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员积分记录Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberPointsRecordService
    implements IBaseService<MemberPointsRecord, MemberPointsRecordBo> {

    private final MemberPointsRecordMapper memberPointsRecordMapper;

    @Override
    public IBaseMapper<MemberPointsRecord> mapper() {
        return memberPointsRecordMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberPointsRecord> buildQueryWrapper(MemberPointsRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberPointsRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberPointsRecord::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, MemberPointsRecord::getMemberId, bo.getMemberId());
        lqw.eq(bo.getMemberTypeId() != null, MemberPointsRecord::getMemberTypeId, bo.getMemberTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getActionCode()), MemberPointsRecord::getActionCode, bo.getActionCode());
        lqw.eq(bo.getStatus() != null, MemberPointsRecord::getStatus, bo.getStatus());
        return lqw;
    }

}
