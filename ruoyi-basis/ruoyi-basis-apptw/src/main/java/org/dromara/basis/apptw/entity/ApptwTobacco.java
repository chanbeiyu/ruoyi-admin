package org.dromara.basis.apptw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 香烟信息对象 apptw_tobacco
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@TableName("apptw_tobacco")
public class ApptwTobacco {

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌英文名称
     */
    private String brandEnname;

    /**
     * 品类名称
     */
    private String name;

    /**
     * 品类英文名称
     */
    private String enname;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 英文全名
     */
    private String fullEnname;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 焦油量
     */
    private Long tar;

    /**
     * 烟气烟碱量
     */
    private Long nicotine;

    /**
     * 烟气一氧化碳量
     */
    private Long co;

    /**
     * 包装形式
     */
    private String packageType;

    /**
     * 每条包数
     */
    private Long stripCount;

    /**
     * 每包支数
     */
    private Long boxCount;

    /**
     * 规格
     */
    private String specs;

    /**
     * 小盒条码
     */
    private String boxBarcode;

    /**
     * 条盒条码
     */
    private String stripBarcode;

    /**
     * 小盒零售价
     */
    private Double boxPrice;

    /**
     * 条盒零售价
     */
    private Double stripPrice;

    /**
     * 简介
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
