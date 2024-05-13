package org.dromara.platform.domain.trade.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.trade.TradeOrderItem;

import java.io.Serial;
import java.io.Serializable;


/**
 * 订单商品视图对象 trade_order_item
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = TradeOrderItem.class)
public class TradeOrderItemVo extends AppBaseVo {

    /**
     * 订单商品id
     */
    @ExcelProperty(value = "订单商品id")
    private Long itemId;

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
     * 产品id
     */
    @ExcelProperty(value = "产品id")
    private Long productId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String productName;

    /**
     * 产品品牌
     */
    @ExcelProperty(value = "产品品牌")
    private String productBrand;

    /**
     * 商品sku编号
     */
    @ExcelProperty(value = "商品sku编号")
    private Long productSkuId;

    /**
     * 商品sku条码
     */
    @ExcelProperty(value = "商品sku条码")
    private String productSkuCode;

    /**
     * 产品图片地址
     */
    @ExcelProperty(value = "产品图片地址")
    private String productPicUrl;

    /**
     * 商品销售属性
     */
    @ExcelProperty(value = "商品销售属性")
    private String productAttr;

    /**
     * 销售价格
     */
    @ExcelProperty(value = "销售价格")
    private Long productPrice;

    /**
     * 购买数量
     */
    @ExcelProperty(value = "购买数量")
    private Long productQuantity;

    /**
     * 商品分类id
     */
    @ExcelProperty(value = "商品分类id")
    private Long productCategoryId;

    /**
     * 商品促销名称
     */
    @ExcelProperty(value = "商品促销名称")
    private String promotionName;

    /**
     * 商品促销分解金额
     */
    @ExcelProperty(value = "商品促销分解金额")
    private Long promotionAmount;

    /**
     * 优惠券优惠分解金额
     */
    @ExcelProperty(value = "优惠券优惠分解金额")
    private Long couponAmount;

    /**
     * 积分优惠分解金额
     */
    @ExcelProperty(value = "积分优惠分解金额")
    private Long integrationAmount;

    /**
     * 实际金额
     */
    @ExcelProperty(value = "实际金额")
    private Long paymentAmount;


}
