package org.dromara.platform.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import org.dromara.platform.domain.TradeOrderItem;

/**
 * 订单商品业务对象 trade_order_item
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TradeOrderItem.class, reverseConvertGenerate = false)
public class TradeOrderItemBo extends BaseEntity {

    /**
     * 订单商品id
     */
    @NotNull(message = "订单商品id不能为空", groups = { EditGroup.class })
    private Long itemId;

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
     * 产品id
     */
    @NotNull(message = "产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 产品品牌
     */
    @NotBlank(message = "产品品牌不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productBrand;

    /**
     * 商品sku编号
     */
    @NotNull(message = "商品sku编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productSkuId;

    /**
     * 商品sku条码
     */
    @NotBlank(message = "商品sku条码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productSkuCode;

    /**
     * 产品图片地址
     */
    @NotBlank(message = "产品图片地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productPicUrl;

    /**
     * 商品销售属性
     */
    @NotBlank(message = "商品销售属性不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productAttr;

    /**
     * 销售价格
     */
    @NotNull(message = "销售价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productPrice;

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productQuantity;

    /**
     * 商品分类id
     */
    @NotNull(message = "商品分类id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productCategoryId;

    /**
     * 商品促销名称
     */
    @NotBlank(message = "商品促销名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String promotionName;

    /**
     * 商品促销分解金额
     */
    @NotNull(message = "商品促销分解金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long promotionAmount;

    /**
     * 优惠券优惠分解金额
     */
    @NotNull(message = "优惠券优惠分解金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long couponAmount;

    /**
     * 积分优惠分解金额
     */
    @NotNull(message = "积分优惠分解金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long integrationAmount;

    /**
     * 实际金额
     */
    @NotNull(message = "实际金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long paymentAmount;


}
