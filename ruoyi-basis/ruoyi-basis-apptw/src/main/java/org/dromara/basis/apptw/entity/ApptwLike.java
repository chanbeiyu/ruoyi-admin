package org.dromara.basis.apptw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 喜好品类对象 apptw_like
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@TableName("apptw_like")
public class ApptwLike {

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 成员ID
     */
    private Long memberId;

    /**
     * 品类ID
     */
    private Long tobaccoId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品类名称
     */
    private String tobaccoName;

    /**
     * 品牌英文名称
     */
    private String brandEnname;

    /**
     * 品类英文名称
     */
    private String tobaccoEnname;

    /**
     * 焦油量
     */
    private Double tar;

    /**
     * 烟气烟碱量
     */
    private Double nicotine;

    /**
     * 烟气一氧化碳量
     */
    private Double co;

    /**
     * 售价
     */
    private Double price;

    /**
     * 排序
     */
    private Double sort;

    /**
     * 描述
     */
    private String description;

}
