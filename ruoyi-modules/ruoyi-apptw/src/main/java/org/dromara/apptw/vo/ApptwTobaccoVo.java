package org.dromara.platform.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basis.apptw.entity.ApptwTobacco;

import java.io.Serial;
import java.io.Serializable;


/**
 * 香烟信息视图对象 apptw_tobacco
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApptwTobacco.class)
public class ApptwTobaccoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 品牌ID
     */
    @ExcelProperty(value = "品牌ID")
    private Long brandId;

    /**
     * 品牌名称
     */
    @ExcelProperty(value = "品牌名称")
    private String brandName;

    /**
     * 品牌英文名称
     */
    @ExcelProperty(value = "品牌英文名称")
    private String brandEnname;

    /**
     * 品类名称
     */
    @ExcelProperty(value = "品类名称")
    private String name;

    /**
     * 品类英文名称
     */
    @ExcelProperty(value = "品类英文名称")
    private String enname;

    /**
     * 全名
     */
    @ExcelProperty(value = "全名")
    private String fullName;

    /**
     * 英文全名
     */
    @ExcelProperty(value = "英文全名")
    private String fullEnname;

    /**
     * 产品类型
     */
    @ExcelProperty(value = "产品类型")
    private String productType;

    /**
     * 焦油量
     */
    @ExcelProperty(value = "焦油量")
    private Long tar;

    /**
     * 烟气烟碱量
     */
    @ExcelProperty(value = "烟气烟碱量")
    private Long nicotine;

    /**
     * 烟气一氧化碳量
     */
    @ExcelProperty(value = "烟气一氧化碳量")
    private Long co;

    /**
     * 小盒条码
     */
    @ExcelProperty(value = "小盒条码")
    private String boxBarcode;

    /**
     * 条盒条码
     */
    @ExcelProperty(value = "条盒条码")
    private String stripBarcode;

    /**
     * 小盒零售价
     */
    @ExcelProperty(value = "小盒零售价")
    private Double boxPrice;

    /**
     * 条盒零售价
     */
    @ExcelProperty(value = "条盒零售价")
    private Double stripPrice;

    /**
     * 简介
     */
    @ExcelProperty(value = "简介")
    private String description;


}
