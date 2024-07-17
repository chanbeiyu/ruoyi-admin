package org.dromara.basis.trade.bo;

import org.dromara.basis.trade.entity.TradeOrder;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 订单信息业务对象 trade_order
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TradeOrder.class, reverseConvertGenerate = false)
public class TradeOrderBo extends BaseEntity {

    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空", groups = { EditGroup.class })
    private Long orderId;

    /**
     * 订单用户id
     */
    @NotNull(message = "订单用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 订单编号
     */
    @NotBlank(message = "订单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 订单类型：0->虚拟订单；1->正常订单
     */
    private Long orderType;

    /**
     * 订单备注
     */
    private String orderNote;

    /**
     * 支付方式
     */
    private Long paymentType;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 交易编号
     */
    private String paymentNo;

    /**
     * 支付金额(实付)
     */
    private Long paymentAmount;

    /**
     * 订单总金额
     */
    private Long totalAmount;

    /**
     * 运费金额
     */
    private Long freightAmount;

    /**
     * 促销优化金额
     */
    private Long promotionAmount;

    /**
     * 积分抵扣金额
     */
    private Long integrationAmount;

    /**
     * 后台折扣金额
     */
    private Long discountAmount;

    /**
     * 优惠券抵扣金额
     */
    @NotNull(message = "优惠券抵扣金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long couponAmount;

    /**
     * 配送方式
     */
    private Long deliveryId;

    /**
     * 配送公司
     */
    private String deliveryName;

    /**
     * 配送时间
     */
    private Date deliveryTime;

    /**
     * 物流单号
     */
    private String deliveryNo;

    /**
     * 收货人
     */
    private String receiverUsername;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

    /**
     * 收货地址
     */
    private String receiverAddress;

    /**
     * 收货时间
     */
    private Date receiverTime;

    /**
     * 订单状态
     */
    private Long status;

    /**
     * 扩展信息
     */
    private String extend;

    /**
     * 描述
     */
    private String description;


}
