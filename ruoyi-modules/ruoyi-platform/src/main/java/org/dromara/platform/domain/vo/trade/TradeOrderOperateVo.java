package org.dromara.platform.domain.vo.trade;

import org.dromara.basal.trade.domain.TradeOrderOperate;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 订单操作历史记录视图对象 trade_order_operate
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TradeOrderOperate.class)
public class TradeOrderOperateVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单操作id
     */
    @ExcelProperty(value = "订单操作id")
    private Long operateId;

    /**
     * 订单id
     */
    @ExcelProperty(value = "订单id")
    private Long orderId;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String orderNo;

    /**
     * 操作前订单状态
     */
    @ExcelProperty(value = "操作前订单状态")
    private Long beforStatus;

    /**
     * 操作后订单状态
     */
    @ExcelProperty(value = "操作后订单状态")
    private Long afterStatus;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;


}
