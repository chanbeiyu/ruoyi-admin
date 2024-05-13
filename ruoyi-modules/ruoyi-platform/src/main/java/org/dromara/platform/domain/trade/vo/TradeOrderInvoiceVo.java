package org.dromara.platform.domain.trade.vo;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.trade.TradeOrderInvoice;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 订单信息视图对象 trade_order_invoice
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = TradeOrderInvoice.class)
public class TradeOrderInvoiceVo extends AppBaseVo {

    /**
     * 开票id
     */
    @ExcelProperty(value = "开票id")
    private Long invoiceId;

    /**
     * 订单用户id
     */
    @ExcelProperty(value = "订单用户id")
    private Long memberId;

    /**
     * 订单id
     */
    @ExcelProperty(value = "订单id")
    private Long orderId;

    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    @ExcelProperty(value = "发票类型：0->不开发票；1->电子发票；2->纸质发票")
    private Long invoiceType;

    /**
     * 发票抬头
     */
    @ExcelProperty(value = "发票抬头")
    private String invoiceHeader;

    /**
     * 发票内容
     */
    @ExcelProperty(value = "发票内容")
    private String invoiceContent;

    /**
     * 收票下载地址
     */
    @ExcelProperty(value = "收票下载地址")
    private String invoiceUrl;

    /**
     * 收票人电话
     */
    @ExcelProperty(value = "收票人电话")
    private String receiverPhone;

    /**
     * 收票人邮箱
     */
    @ExcelProperty(value = "收票人邮箱")
    private String receiverEmail;

    /**
     * 发票状态
     */
    @ExcelProperty(value = "发票状态")
    private Long status;

    /**
     * 开票时间
     */
    @ExcelProperty(value = "开票时间")
    private Date buildTime;

}
