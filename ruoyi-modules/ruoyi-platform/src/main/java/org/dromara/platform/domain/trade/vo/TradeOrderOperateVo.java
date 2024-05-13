package org.dromara.platform.domain.trade.vo;

import lombok.EqualsAndHashCode;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.trade.TradeOrderOperate;
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
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = TradeOrderOperate.class)
public class TradeOrderOperateVo extends AppBaseVo {

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
