package org.dromara.platform.mapper;

import org.dromara.platform.domain.trade.TradeOrderOperate;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 订单操作历史记录Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Repository
public interface TradeOrderOperateMapper extends IBaseMapper<TradeOrderOperate> {

}
