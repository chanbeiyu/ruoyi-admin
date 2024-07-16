package org.dromara.basis.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberTypeBo;
import org.dromara.basis.member.entity.MemberType;
import org.dromara.basis.member.mapper.MemberTypeMapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.result.ModifyResult;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 会员类型信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberTypeService implements IBaseService<MemberType, MemberTypeBo> {

    private final MemberTypeMapper memberTypeMapper;

    @Override
    public IBaseMapper<MemberType> mapper() {
        return memberTypeMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberType> buildQueryWrapper(MemberTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberType> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberType::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), MemberType::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), MemberType::getTypeCode, bo.getTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getTypeName()), MemberType::getTypeName, bo.getTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MemberType::getStatus, bo.getStatus());
        return lqw;
    }


    /**
     * 修改状态
     *
     * @return 结果
     */
    public int updateStatus(Long appId, String status) {
        return memberTypeMapper.update(null,
            new LambdaUpdateWrapper<MemberType>().set(MemberType::getStatus, status).eq(MemberType::getTypeId, appId));
    }

}
