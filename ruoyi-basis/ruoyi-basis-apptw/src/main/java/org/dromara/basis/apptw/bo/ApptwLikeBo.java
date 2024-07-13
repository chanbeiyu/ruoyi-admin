package org.dromara.basis.apptw.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.apptw.entity.ApptwLike;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

/**
 * 喜好品类业务对象 apptw_like
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApptwLike.class, reverseConvertGenerate = false)
public class ApptwLikeBo extends BaseBo {

    @NotNull(message = "品类ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberId;

    /**
     * 品类ID
     */
    @NotNull(message = "品类ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long tobaccoId;

    /**
     * 品牌名称
     */
    @NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String brandName;

    /**
     * 品类名称
     */
    @NotBlank(message = "品类名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String tobaccoName;

    /**
     * 品牌英文名称
     */
    @NotBlank(message = "品牌英文名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String brandEnname;

    /**
     * 品类英文名称
     */
    @NotBlank(message = "品类英文名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String tobaccoEnname;

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
     * 售价
     */
    @NotNull(message = "售价不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double price;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double sort;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = {AddGroup.class, EditGroup.class})
    private String description;


}
