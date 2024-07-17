package org.dromara.basis.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberLevelBo;
import org.dromara.basis.member.entity.MemberLevel;
import org.dromara.basis.member.entity.MemberType;
import org.dromara.basis.member.mapper.MemberLevelMapper;
import org.dromara.basis.member.mapper.MemberTypeMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.result.ModifyResult;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 会员级别信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberLevelService implements IBaseService<MemberLevel, MemberLevelBo> {

    private final MemberLevelMapper memberLevelMapper;
    private final MemberTypeMapper memberTypeMapper;

    @Override
    public IBaseMapper<MemberLevel> mapper() {
        return memberLevelMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberLevel> buildQueryWrapper(MemberLevelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberLevel> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getMemberTypeId()), MemberLevel::getMemberTypeId, bo.getMemberTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getLevelCode()), MemberLevel::getLevelCode, bo.getLevelCode());
        lqw.like(StringUtils.isNotBlank(bo.getLevelName()), MemberLevel::getLevelName, bo.getLevelName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MemberLevel::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改状态
     *
     * @return 结果
     */
    public int updateStatus(Long appId, String status) {
        return memberLevelMapper.update(null, new LambdaUpdateWrapper<MemberLevel>().set(MemberLevel::getStatus, status)
            .eq(MemberLevel::getLevelId, appId));
    }

}
