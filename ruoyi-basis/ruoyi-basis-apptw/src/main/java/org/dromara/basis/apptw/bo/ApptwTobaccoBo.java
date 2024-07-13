package org.dromara.basis.apptw.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.apptw.entity.ApptwTobacco;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

import java.io.Serializable;

/**
 * 香烟信息业务对象 apptw_tobacco
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApptwTobacco.class, reverseConvertGenerate = false)
public class ApptwTobaccoBo extends BaseBo {

    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 品牌ID
     */
    @NotNull(message = "品牌ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long brandId;

    /**
     * 品牌名称
     */
    @NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String brandName;

    /**
     * 品牌英文名称
     */
    @NotBlank(message = "品牌英文名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String brandEnname;

    /**
     * 品类名称
     */
    @NotBlank(message = "品类名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 品类英文名称
     */
    @NotBlank(message = "品类英文名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String enname;

    /**
     * 全名
     */
    @NotBlank(message = "全名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String fullName;

    /**
     * 英文全名
     */
    @NotBlank(message = "英文全名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String fullEnname;

    /**
     * 产品类型
     */
    @NotBlank(message = "产品类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String productType;

    /**
     * 焦油量
     */
    @NotNull(message = "焦油量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long tar;

    /**
     * 烟气烟碱量
     */
    @NotNull(message = "烟气烟碱量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long nicotine;

    /**
     * 烟气一氧化碳量
     */
    @NotNull(message = "烟气一氧化碳量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long co;

    /**
     * 包装形式
     */
    @NotBlank(message = "包装形式不能为空", groups = {AddGroup.class, EditGroup.class})
    private String packageType;

    /**
     * 每条包数
     */
    @NotNull(message = "每条包数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long stripCount;

    /**
     * 每包支数
     */
    @NotNull(message = "每包支数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long boxCount;

    /**
     * 规格
     */
    @NotBlank(message = "规格不能为空", groups = {AddGroup.class, EditGroup.class})
    private String specs;

    /**
     * 小盒条码
     */
    @NotBlank(message = "小盒条码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String boxBarcode;

    /**
     * 条盒条码
     */
    @NotBlank(message = "条盒条码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String stripBarcode;

    /**
     * 小盒零售价
     */
    @NotNull(message = "小盒零售价不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double boxPrice;

    /**
     * 条盒零售价
     */
    @NotNull(message = "条盒零售价不能为空", groups = {AddGroup.class, EditGroup.class})
    private Double stripPrice;

    /**
     * 简介
     */
    private String description;


}
