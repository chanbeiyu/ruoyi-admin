package org.dromara.basis.member.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberCoinsBo;
import org.dromara.basis.member.entity.MemberCoins;
import org.dromara.basis.member.mapper.MemberCoinsMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 代币信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberCoinsService implements IBaseService<MemberCoins, MemberCoinsBo> {

    private final MemberCoinsMapper memberCoinsMapper;

    @Override
    public IBaseMapper<MemberCoins> mapper() {
        return memberCoinsMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberCoins> buildQueryWrapper(MemberCoinsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberCoins> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getMemberId()), MemberCoins::getMemberId, bo.getMemberId());
        lqw.eq(Objects.nonNull(bo.getCoinsType()), MemberCoins::getCoinsType, bo.getCoinsType());
        lqw.eq(Objects.nonNull(bo.getStatus()), MemberCoins::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginExpiredDate"), params.get("endExpiredDate")),
            MemberCoins::getExpiredDate, params.get("beginExpiredDate"), params.get("endExpiredDate"));
        lqw.eq(Objects.nonNull(bo.getStatus()), MemberCoins::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 修改状态
     *
     * @return 结果
     */
    public int updateStatus(Long appId, Integer status) {
        return memberCoinsMapper.update(null,
            new LambdaUpdateWrapper<MemberCoins>().set(MemberCoins::getStatus, status).eq(MemberCoins::getId, appId));
    }

}
