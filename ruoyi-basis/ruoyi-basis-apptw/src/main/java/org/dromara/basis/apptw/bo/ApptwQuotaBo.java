package org.dromara.basis.apptw.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.apptw.entity.ApptwQuota;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

import java.io.Serializable;
import java.util.Date;

/**
 * 目标限额业务对象 apptw_quota
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApptwQuota.class, reverseConvertGenerate = false)
public class ApptwQuotaBo extends BaseBo {

    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberId;

    /**
     * 限额月份
     */
    @NotNull(message = "限额月份不能为空", groups = {AddGroup.class, EditGroup.class})
    private String month;

    /**
     * 限额月总数
     */
    @NotNull(message = "限额月总数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long total;

    /**
     * 限额月平均
     */
    @NotNull(message = "限额月平均不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long avg;

    /**
     * 限额天数
     */
    @NotNull(message = "限额天数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long days;

    /**
     * 描述
     */
    private String description;


}
