package org.dromara.platform.service.member;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.constant.RedisKey;
import org.dromara.platform.domain.MemberType;
import org.dromara.platform.domain.bo.MemberTypeBo;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.platform.domain.vo.member.MemberTypeVo;
import org.dromara.platform.mapper.MemberTypeMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 会员类型信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberTypeService implements IBaseService<MemberType, MemberTypeVo, MemberTypeBo> {

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
     * 新增会员类型信息
     */
    @Override
    public Boolean insertByBo(MemberTypeBo bo) {
        MemberType add = MapstructUtils.convert(bo, MemberType.class);
        boolean flag = memberTypeMapper.insert(add) > 0;
        if (flag) {
            bo.setTypeId(add.getTypeId());
            CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, bo.getTypeId(), bo.getTypeName());
            CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, bo.getTypeCode(), bo.getTypeName());
        }
        return flag;
    }

    /**
     * 修改会员类型信息
     */
    @Override
    public Boolean updateByBo(MemberTypeBo bo) {
        MemberType update = MapstructUtils.convert(bo, MemberType.class);
        boolean bool = memberTypeMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, bo.getTypeId(), bo.getTypeName());
            CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, bo.getTypeCode(), bo.getTypeName());
        }
        return bool;
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

    /**
     * 批量删除会员类型信息
     */
    @Override
    public Boolean deleteByIds(Collection<Long> ids) {
        List<MemberType> memberTypes = memberTypeMapper.selectList();
        boolean bool = memberTypeMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            memberTypes.forEach(bo -> {
                CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, bo.getTypeId(), bo.getTypeName());
                CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, bo.getTypeCode(), bo.getTypeName());
            });
        }
        return bool;
    }
}
