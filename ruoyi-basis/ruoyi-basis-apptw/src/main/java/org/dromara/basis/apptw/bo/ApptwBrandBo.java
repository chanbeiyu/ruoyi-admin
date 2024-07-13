package org.dromara.basis.apptw.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.apptw.entity.ApptwBrand;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

import java.io.Serializable;

/**
 * 香烟品牌业务对象 apptw_brand
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApptwBrand.class, reverseConvertGenerate = false)
public class ApptwBrandBo extends BaseBo {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 品牌名称
     */
    @NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 品牌英文名称
     */
    @NotBlank(message = "品牌英文名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String enname;

    /**
     * 类型1大陆2港澳台3国外品牌4历史品牌
     */
    @NotBlank(message = "类型1大陆2港澳台3国外品牌4历史品牌不能为空", groups = {AddGroup.class, EditGroup.class})
    private String type;

    /**
     * 简介
     */
    private String description;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;


}
