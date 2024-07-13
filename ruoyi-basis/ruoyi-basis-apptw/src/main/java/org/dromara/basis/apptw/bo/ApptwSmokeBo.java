package org.dromara.basis.apptw.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.apptw.entity.ApptwSmoke;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽烟记录业务对象 apptw_smoke
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApptwSmoke.class, reverseConvertGenerate = false)
public class ApptwSmokeBo extends BaseBo {

    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberId;

    /**
     * 喜好ID
     */
    @NotNull(message = "喜好ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long likeId;

    /**
     * 品类ID
     */
    @NotNull(message = "品类ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long tobaccoId;

    /**
     * 时间
     */
    @NotNull(message = "时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date dateTime;

    /**
     * 焦油量
     */
    @NotNull(message = "焦油量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double tar;

    /**
     * 烟气烟碱量
     */
    @NotNull(message = "烟气烟碱量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double nicotine;

    /**
     * 烟气一氧化碳量
     */
    @NotNull(message = "烟气一氧化碳量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double co;

    /**
     * 支出
     */
    @NotNull(message = "支出不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double spend;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = {AddGroup.class, EditGroup.class})
    private String description;


}
