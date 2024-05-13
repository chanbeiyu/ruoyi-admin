package org.dromara.platform.mapper;

import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.platform.domain.trade.TradeOrderItem;
import org.springframework.stereotype.Repository;

/**
 * 订单商品Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Repository
public interface TradeOrderItemMapper extends IBaseMapper<TradeOrderItem> {

}
