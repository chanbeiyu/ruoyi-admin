package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 订单商品对象 trade_order_item
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("trade_order_item")
public class TradeOrderItem extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单商品id
     */
    @TableId(value = "item_id")
    private Long itemId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品品牌
     */
    private String productBrand;

    /**
     * 商品sku编号
     */
    private Long productSkuId;

    /**
     * 商品sku条码
     */
    private String productSkuCode;

    /**
     * 产品图片地址
     */
    private String productPicUrl;

    /**
     * 商品销售属性
     */
    private String productAttr;

    /**
     * 销售价格
     */
    private Long productPrice;

    /**
     * 购买数量
     */
    private Long productQuantity;

    /**
     * 商品分类id
     */
    private Long productCategoryId;

    /**
     * 商品促销名称
     */
    private String promotionName;

    /**
     * 商品促销分解金额
     */
    private Long promotionAmount;

    /**
     * 优惠券优惠分解金额
     */
    private Long couponAmount;

    /**
     * 积分优惠分解金额
     */
    private Long integrationAmount;

    /**
     * 实际金额
     */
    private Long paymentAmount;

    /**
     * 备注
     */
    private String remark;


}
