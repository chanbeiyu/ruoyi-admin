package org.dromara.platform.domain.vo.trade;

import java.util.Date;

import org.dromara.basal.trade.domain.TradeOrder;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 订单信息视图对象 trade_order
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TradeOrder.class)
public class TradeOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @ExcelProperty(value = "订单id")
    private Long orderId;

    /**
     * 订单来源应用
     */
    @ExcelProperty(value = "订单来源应用")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 订单用户id
     */
    @ExcelProperty(value = "订单用户id")
    private Long memberId;

    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 订单类型：0->虚拟订单；1->正常订单
     */
    @ExcelProperty(value = "订单类型：0->虚拟订单；1->正常订单")
    private Long orderType;

    /**
     * 订单备注
     */
    @ExcelProperty(value = "订单备注")
    private String orderNote;

    /**
     * 支付方式
     */
    @ExcelProperty(value = "支付方式")
    private Long paymentType;

    /**
     * 支付时间
     */
    @ExcelProperty(value = "支付时间")
    private Date paymentTime;

    /**
     * 交易编号
     */
    @ExcelProperty(value = "交易编号")
    private String paymentNo;

    /**
     * 支付金额(实付)
     */
    @ExcelProperty(value = "支付金额(实付)")
    private Long paymentAmount;

    /**
     * 订单总金额
     */
    @ExcelProperty(value = "订单总金额")
    private Long totalAmount;

    /**
     * 运费金额
     */
    @ExcelProperty(value = "运费金额")
    private Long freightAmount;

    /**
     * 促销优化金额
     */
    @ExcelProperty(value = "促销优化金额")
    private Long promotionAmount;

    /**
     * 积分抵扣金额
     */
    @ExcelProperty(value = "积分抵扣金额")
    private Long integrationAmount;

    /**
     * 后台折扣金额
     */
    @ExcelProperty(value = "后台折扣金额")
    private Long discountAmount;

    /**
     * 优惠券抵扣金额
     */
    @ExcelProperty(value = "优惠券抵扣金额")
    private Long couponAmount;

    /**
     * 配送方式
     */
    @ExcelProperty(value = "配送方式")
    private Long deliveryId;

    /**
     * 配送公司
     */
    @ExcelProperty(value = "配送公司")
    private String deliveryName;

    /**
     * 配送时间
     */
    @ExcelProperty(value = "配送时间")
    private Date deliveryTime;

    /**
     * 物流单号
     */
    @ExcelProperty(value = "物流单号")
    private String deliveryNo;

    /**
     * 收货人
     */
    @ExcelProperty(value = "收货人")
    private String receiverUsername;

    /**
     * 收货人手机号
     */
    @ExcelProperty(value = "收货人手机号")
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    @ExcelProperty(value = "收货人邮编")
    private String receiverPostCode;

    /**
     * 收货地址
     */
    @ExcelProperty(value = "收货地址")
    private String receiverAddress;

    /**
     * 收货时间
     */
    @ExcelProperty(value = "收货时间")
    private Date receiverTime;

    /**
     * 订单状态
     */
    @ExcelProperty(value = "订单状态")
    private Long status;

    /**
     * 扩展信息
     */
    @ExcelProperty(value = "扩展信息")
    private String extend;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
