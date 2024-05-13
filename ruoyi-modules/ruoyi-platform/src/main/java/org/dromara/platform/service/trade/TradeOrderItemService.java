package org.dromara.platform.service.trade;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.trade.TradeOrderItem;
import org.dromara.platform.domain.trade.bo.TradeOrderItemBo;
import org.dromara.platform.mapper.TradeOrderItemMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.platform.domain.trade.vo.TradeOrderItemVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单商品Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Service
@RequiredArgsConstructor
public class TradeOrderItemService implements IBaseService<TradeOrderItem, TradeOrderItemVo, TradeOrderItemBo> {

    private final TradeOrderItemMapper tradeOrderItemMapper;

    @Override
    public IBaseMapper<TradeOrderItem> mapper() {
        return tradeOrderItemMapper;
    }

    @Override
    public LambdaQueryWrapper<TradeOrderItem> buildQueryWrapper(TradeOrderItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrderItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, TradeOrderItem::getOrderId, bo.getOrderId());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrderItem::getOrderNo, bo.getOrderNo());
        lqw.like(bo.getProductId() != null, TradeOrderItem::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), TradeOrderItem::getProductName, bo.getProductName());
        lqw.like(StringUtils.isNotBlank(bo.getProductBrand()), TradeOrderItem::getProductBrand, bo.getProductBrand());
        lqw.like(bo.getProductSkuId() != null, TradeOrderItem::getProductSkuId, bo.getProductSkuId());
        lqw.like(StringUtils.isNotBlank(bo.getProductSkuCode()), TradeOrderItem::getProductSkuCode,
            bo.getProductSkuCode());
        return lqw;
    }

}
