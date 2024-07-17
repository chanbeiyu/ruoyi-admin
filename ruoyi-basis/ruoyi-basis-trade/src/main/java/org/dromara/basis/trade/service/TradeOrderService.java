package org.dromara.basis.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.trade.bo.TradeOrderBo;
import org.dromara.basis.trade.entity.TradeOrder;
import org.dromara.basis.trade.mapper.TradeOrderMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Service
@RequiredArgsConstructor
public class TradeOrderService implements IBaseService<TradeOrder, TradeOrderBo> {

    private final TradeOrderMapper tradeOrderMapper;

    @Override
    public IBaseMapper<TradeOrder> mapper() {
        return tradeOrderMapper;
    }

    @Override
    public LambdaQueryWrapper<TradeOrder> buildQueryWrapper(TradeOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, TradeOrder::getMemberId, bo.getMemberId());
        lqw.eq(bo.getOrderType() != null, TradeOrder::getOrderType, bo.getOrderType());
        lqw.eq(bo.getPaymentType() != null, TradeOrder::getPaymentType, bo.getPaymentType());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrder::getOrderNo, bo.getOrderNo());
        lqw.like(StringUtils.isNotBlank(bo.getPaymentNo()), TradeOrder::getPaymentNo, bo.getPaymentNo());
        lqw.eq(bo.getDeliveryId() != null, TradeOrder::getDeliveryId, bo.getDeliveryId());
        lqw.eq(bo.getStatus() != null, TradeOrder::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginPaymentTime"), params.get("endPaymentTime")),
            TradeOrder::getPaymentTime, params.get("beginPaymentTime"), params.get("endPaymentTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginDeliveryTime"), params.get("endDeliveryTime")),
            TradeOrder::getDeliveryTime, params.get("beginDeliveryTime"), params.get("endDeliveryTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginReceiverTime"), params.get("endReceiverTime")),
            TradeOrder::getReceiverTime, params.get("beginReceiverTime"), params.get("endReceiverTime"));
        return lqw;
    }
}
