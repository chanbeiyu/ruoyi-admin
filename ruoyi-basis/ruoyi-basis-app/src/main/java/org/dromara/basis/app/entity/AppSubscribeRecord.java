package org.dromara.basis.app.entity;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * App订阅记录对象 app_subscribe_record
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Data
@TableName("app_subscribe_record")
@EqualsAndHashCode(callSuper = true)
public class AppSubscribeRecord extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "record_id")
    private Long recordId;

    /**
     * APPID
     */
    private Long appId;

    /**
     * 订阅编码
     */
    private Long subscribeId;

    /**
     * 成员ID
     */
    private Long memberId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 开始时间
     */
    private Date starTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 花费费用
     */
    private Long cost;

    /**
     * 开启自动续订
     */
    private String renewEnable;

    /**
     * 自动续费定录ID
     */
    private Long renewRecordId;

    /**
     * 退款时间
     */
    private Date refundTime;

    /**
     * 状态0未开始1正常2过期3退款
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
