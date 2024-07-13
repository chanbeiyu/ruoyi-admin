package org.dromara.basis.trade.bo;

import org.dromara.basis.trade.entity.TradeOrderOperate;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 订单操作历史记录业务对象 trade_order_operate
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TradeOrderOperate.class, reverseConvertGenerate = false)
public class TradeOrderOperateBo extends BaseEntity {

    /**
     * 订单操作id
     */
    @NotNull(message = "订单操作id不能为空", groups = { EditGroup.class })
    private Long operateId;

    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 操作前订单状态
     */
    @NotNull(message = "操作前订单状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long beforStatus;

    /**
     * 操作后订单状态
     */
    @NotNull(message = "操作后订单状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long afterStatus;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;


}
