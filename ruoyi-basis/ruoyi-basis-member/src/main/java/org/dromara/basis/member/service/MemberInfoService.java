package org.dromara.basis.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberInfoBo;
import org.dromara.basis.member.entity.MemberInfo;
import org.dromara.basis.member.mapper.MemberInfoMapper;
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
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberInfoService implements IBaseService<MemberInfo, MemberInfoBo> {

    private final MemberInfoMapper memberInfoMapper;

    @Override
    public IBaseMapper<MemberInfo> mapper() {
        return memberInfoMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberInfo> buildQueryWrapper(MemberInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberInfo::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getTypeId()), MemberInfo::getTypeId, bo.getTypeId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), MemberInfo::getAppId, bo.getAppIds());
        lqw.in(CollectionUtils.isNotEmpty(bo.getTypeIds()), MemberInfo::getTypeId, bo.getTypeIds());
        lqw.like(StringUtils.isNotBlank(bo.getUnionId()), MemberInfo::getUnionId, bo.getUnionId());
        lqw.like(StringUtils.isNotBlank(bo.getNickName()), MemberInfo::getNickName, bo.getNickName());
        lqw.eq(bo.getStatus() != null, MemberInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改状态
     *
     * @return 结果
     */
    public int updateStatus(Long appId, String status) {
        return memberInfoMapper.update(null, new LambdaUpdateWrapper<MemberInfo>().set(MemberInfo::getStatus, status)
            .eq(MemberInfo::getMemberId, appId));
    }


}
