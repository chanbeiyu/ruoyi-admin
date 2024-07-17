package org.dromara.basis.app.bo;

import org.dromara.basis.app.entity.AppSubscribe;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * App订阅业务对象 app_subscribe
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppSubscribe.class, reverseConvertGenerate = false)
public class AppSubscribeBo extends BaseEntity {

    /**
     * 内购id
     */
    @NotNull(message = "内购id不能为空", groups = { EditGroup.class })
    private Long subscribeId;

    /**
     * 订阅编码
     */
    @NotBlank(message = "订阅编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 订阅标题
     */
    @NotBlank(message = "订阅标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 原价
     */
    @NotNull(message = "原价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long originalPrice;

    /**
     * 折扣价
     */
    @NotNull(message = "折扣价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long discountPrice;

    /**
     * 订阅数量
     */
    @NotNull(message = "订阅数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cycleCount;

    /**
     * 订阅周期:0永久1天2月3年
     */
    @NotNull(message = "订阅周期:0永久1天2月3年不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cycleType;

    /**
     * 支持自动续订
     */
    @NotBlank(message = "支持自动续订不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supportRenew;

    /**
     * 启用状态
     */
    @NotBlank(message = "启用状态不能为空", groups = { EditGroup.class })
    private String status;

    /**
     * 订阅特权说明JSON
     */
    private String privilege;

    /**
     * 描述
     */
    private String description;


}
