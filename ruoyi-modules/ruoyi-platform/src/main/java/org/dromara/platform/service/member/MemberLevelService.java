package org.dromara.platform.service.member;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.MemberLevel;
import org.dromara.platform.domain.bo.MemberLevelBo;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.member.MemberLevelVo;
import org.dromara.platform.domain.vo.member.MemberTypeVo;
import org.dromara.platform.mapper.MemberLevelMapper;
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
public class MemberLevelService implements IBaseService<MemberLevel, MemberLevelVo, MemberLevelBo> {

    private final MemberLevelMapper memberLevelMapper;
    private final MemberTypeService memberTypeService;

    @Override
    public IBaseMapper<MemberLevel> mapper() {
        return memberLevelMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberLevel> buildQueryWrapper(MemberLevelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberLevel> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberLevel::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getMemberTypeId()), MemberLevel::getMemberTypeId, bo.getMemberTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getLevelCode()), MemberLevel::getLevelCode, bo.getLevelCode());
        lqw.like(StringUtils.isNotBlank(bo.getLevelName()), MemberLevel::getLevelName, bo.getLevelName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MemberLevel::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增会员级别信息
     */
    @Override
    public Boolean insertByBo(MemberLevelBo bo) {
        MemberLevel add = MapstructUtils.convert(bo, MemberLevel.class);
        MemberTypeVo memberTypeVo = memberTypeService.queryById(bo.getMemberTypeId());
        add.setAppId(memberTypeVo.getAppId());
        boolean flag = memberLevelMapper.insert(add) > 0;
        if (flag) {
            bo.setLevelId(add.getLevelId());
        }
        return flag;
    }

    /**
     * 修改会员级别信息
     */
    @Override
    public Boolean updateByBo(MemberLevelBo bo) {
        MemberLevel update = MapstructUtils.convert(bo, MemberLevel.class);
        return memberLevelMapper.updateById(update) > 0;
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
