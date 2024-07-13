package org.dromara.basis.trade.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import org.dromara.basis.trade.entity.TradeOrderInvoice;

import java.util.Date;
import java.util.List;

/**
 * 订单信息业务对象 trade_order_invoice
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TradeOrderInvoice.class, reverseConvertGenerate = false)
public class TradeOrderInvoiceBo extends BaseEntity {

    /**
     * 开票id
     */
    @NotNull(message = "开票id不能为空", groups = { EditGroup.class })
    private Long invoiceId;

    /**
     * 订单来源应用
     */
    @NotNull(message = "订单来源应用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 订单用户id
     */
    @NotNull(message = "订单用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单编号
     */
    @NotBlank(message = "订单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    @NotNull(message = "发票类型：0->不开发票；1->电子发票；2->纸质发票不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long invoiceType;

    /**
     * 发票抬头
     */
    @NotBlank(message = "发票抬头不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceHeader;

    /**
     * 发票内容
     */
    @NotBlank(message = "发票内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceContent;

    /**
     * 收票下载地址
     */
    @NotBlank(message = "收票下载地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceUrl;

    /**
     * 收票人电话
     */
    @NotBlank(message = "收票人电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String receiverPhone;

    /**
     * 收票人邮箱
     */
    @NotBlank(message = "收票人邮箱不能为空", groups = { AddGroup.class, EditGroup.class })
    private String receiverEmail;

    /**
     * 发票状态
     */
    @NotNull(message = "发票状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 开票时间
     */
    @NotNull(message = "开票时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date buildTime;


}
