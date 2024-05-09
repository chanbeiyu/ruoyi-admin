package org.dromara.platform.service.trade;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basal.trade.domain.TradeOrderOperate;
import org.dromara.platform.domain.bo.TradeOrderOperateBo;
import org.dromara.platform.mapper.TradeOrderOperateMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.trade.TradeOrderOperateVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单操作历史记录Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Service
@RequiredArgsConstructor
public class TradeOrderOperateService
    implements IBaseService<TradeOrderOperate, TradeOrderOperateVo, TradeOrderOperateBo> {

    private final TradeOrderOperateMapper tradeOrderOperateMapper;

    @Override
    public IBaseMapper<TradeOrderOperate> mapper() {
        return tradeOrderOperateMapper;
    }

    @Override
    public LambdaQueryWrapper<TradeOrderOperate> buildQueryWrapper(TradeOrderOperateBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrderOperate> lqw = Wrappers.lambdaQuery();
        lqw.like(bo.getOrderId() != null, TradeOrderOperate::getOrderId, bo.getOrderId());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrderOperate::getOrderNo, bo.getOrderNo());
        return lqw;
    }

}
