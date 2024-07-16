package org.dromara.basis.app.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppSubscribeRecord;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * App订阅记录业务对象 app_subscribe_record
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppSubscribeRecord.class, reverseConvertGenerate = false)
public class AppSubscribeRecordBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long recordId;

    /**
     * APPID
     */
    @NotNull(message = "应用不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long appId;
    private List<Long> appIds;

    /**
     * 订阅编码
     */
    @NotNull(message = "订阅编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long subscribeId;

    /**
     * 成员ID
     */
    @NotNull(message = "成员名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单号不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long orderId;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date starTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date endTime;

    /**
     * 花费费用
     */
    @NotNull(message = "花费费用不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long cost;

    /**
     * 开启自动续订
     */
    @NotBlank(message = "开启自动续订不能为空", groups = {AddGroup.class, EditGroup.class})
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

}
