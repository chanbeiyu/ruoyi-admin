package org.dromara.basis.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.trade.bo.TradeOrderInvoiceBo;
import org.dromara.basis.trade.entity.TradeOrderInvoice;
import org.dromara.basis.trade.mapper.TradeOrderInvoiceMapper;
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
public class TradeOrderInvoiceService
    implements IBaseService<TradeOrderInvoice, TradeOrderInvoiceBo> {

    private final TradeOrderInvoiceMapper tradeOrderInvoiceMapper;

    @Override
    public IBaseMapper<TradeOrderInvoice> mapper() {
        return tradeOrderInvoiceMapper;
    }

    @Override
    public LambdaQueryWrapper<TradeOrderInvoice> buildQueryWrapper(TradeOrderInvoiceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrderInvoice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, TradeOrderInvoice::getAppId, bo.getAppId());
        lqw.in(bo.getAppIds() != null, TradeOrderInvoice::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, TradeOrderInvoice::getMemberId, bo.getMemberId());
        lqw.like(bo.getOrderId() != null, TradeOrderInvoice::getOrderId, bo.getOrderId());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrderInvoice::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getInvoiceType() != null, TradeOrderInvoice::getInvoiceType, bo.getInvoiceType());
        lqw.like(StringUtils.isNotBlank(bo.getInvoiceHeader()), TradeOrderInvoice::getInvoiceHeader,
            bo.getInvoiceHeader());
        lqw.eq(bo.getStatus() != null, TradeOrderInvoice::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginBuildTime"), params.get("endBuildTime")),
            TradeOrderInvoice::getBuildTime, params.get("beginBuildTime"), params.get("endBuildTime"));
        return lqw;
    }


}
