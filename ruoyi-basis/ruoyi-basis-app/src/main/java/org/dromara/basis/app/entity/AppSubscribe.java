package org.dromara.basis.app.entity;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * App订阅对象 app_subscribe
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Data
@TableName("app_subscribe")
@EqualsAndHashCode(callSuper = true)
public class AppSubscribe extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 内购id
     */
    @TableId(value = "subscribe_id")
    private Long subscribeId;

    /**
     * 接入App标识
     */
    private Long appId;

    /**
     * 订阅编码
     */
    private String code;

    /**
     * 订阅标题
     */
    private String title;

    /**
     * 原价
     */
    private Long originalPrice;

    /**
     * 折扣价
     */
    private Long discountPrice;

    /**
     * 订阅数量
     */
    private Long cycleCount;

    /**
     * 订阅周期:0永久1天2月3年
     */
    private Long cycleType;

    /**
     * 支持自动续订
     */
    private String supportRenew;

    /**
     * 启用状态
     */
    private String status;

    /**
     * 订阅特权说明JSON
     */
    private String privilege;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
