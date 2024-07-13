package org.dromara.basis.trade.entity;

import org.dromara.common.tenant.core.SimpleTenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import java.io.Serial;

/**
 * 订单信息对象 trade_order_invoice
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("trade_order_invoice")
public class TradeOrderInvoice extends SimpleTenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 开票id
     */
    @TableId(value = "invoice_id")
    private Long invoiceId;

    /**
     * 订单来源应用
     */
    private Long appId;

    /**
     * 订单用户id
     */
    private Long memberId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    private Long invoiceType;

    /**
     * 发票抬头
     */
    private String invoiceHeader;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 收票下载地址
     */
    private String invoiceUrl;

    /**
     * 收票人电话
     */
    private String receiverPhone;

    /**
     * 收票人邮箱
     */
    private String receiverEmail;

    /**
     * 发票状态
     */
    private Long status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 开票时间
     */
    private Date buildTime;

    /**
     * 备注
     */
    private String remark;


}
