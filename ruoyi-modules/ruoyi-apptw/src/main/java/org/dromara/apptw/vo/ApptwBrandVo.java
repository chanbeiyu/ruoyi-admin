package org.dromara.apptw.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basis.apptw.entity.ApptwBrand;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * 香烟品牌视图对象 apptw_brand
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApptwBrand.class)
public class ApptwBrandVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 品牌名称
     */
    @ExcelProperty(value = "品牌名称")
    private String name;

    /**
     * 品牌英文名称
     */
    @ExcelProperty(value = "品牌英文名称")
    private String enname;

    /**
     * 类型1大陆2港澳台3国外品牌4历史品牌
     */
    @ExcelProperty(value = "类型1大陆2港澳台3国外品牌4历史品牌", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "apptw_brand_type")
    private String type;

    /**
     * 简介
     */
    @ExcelProperty(value = "简介")
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
