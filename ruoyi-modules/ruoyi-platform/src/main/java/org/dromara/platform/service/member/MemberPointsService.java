package org.dromara.platform.service.member;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.MemberPoints;
import org.dromara.platform.domain.bo.MemberPointsBo;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.member.MemberPointsVo;
import org.dromara.platform.mapper.MemberPointsMapper;
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
public class MemberPointsService implements IBaseService<MemberPoints, MemberPointsVo, MemberPointsBo> {

    private final MemberPointsMapper memberPointsMapper;

    @Override
    public IBaseMapper<MemberPoints> mapper() {
        return memberPointsMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberPoints> buildQueryWrapper(MemberPointsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberPoints> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberPoints::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getMemberId()), MemberPoints::getMemberId, bo.getMemberId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), MemberPoints::getAppId, bo.getAppIds());
        lqw.in(CollectionUtils.isNotEmpty(bo.getMemberTypeIds()), MemberPoints::getMemberTypeId, bo.getMemberTypeIds());
        lqw.eq(Objects.nonNull(bo.getMemberTypeId()), MemberPoints::getMemberTypeId, bo.getMemberTypeId());
        lqw.eq(Objects.nonNull(bo.getStatus()), MemberPoints::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改状态
     *
     * @return 结果
     */
    public int updateStatus(Long appId, Integer status) {
        return memberPointsMapper.update(null,
            new LambdaUpdateWrapper<MemberPoints>().set(MemberPoints::getStatus, status)
                .eq(MemberPoints::getId, appId));
    }


}
