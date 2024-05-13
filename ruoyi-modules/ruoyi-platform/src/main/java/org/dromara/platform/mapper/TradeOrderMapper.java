package org.dromara.platform.mapper;

import org.dromara.platform.domain.trade.TradeOrder;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 订单信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Repository
public interface TradeOrderMapper extends IBaseMapper<TradeOrder> {

}
