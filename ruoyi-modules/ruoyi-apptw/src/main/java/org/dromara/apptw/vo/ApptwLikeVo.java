package org.dromara.apptw.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basis.apptw.entity.ApptwLike;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 喜好品类视图对象 apptw_like
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApptwLike.class)
public class ApptwLikeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 成员ID
     */
    @ExcelProperty(value = "成员ID")
    private Long memberId;

    /**
     * 成员名称
     */
    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.key, mapper = "memberId", other = TranslationConst.MEMBER_INFO)
    private String memberName;

    /**
     * 品类ID
     */
    @ExcelProperty(value = "品类ID")
    private Long tobaccoId;

    /**
     * 品牌名称
     */
    @ExcelProperty(value = "品牌名称")
    private String brandName;

    /**
     * 品类名称
     */
    @ExcelProperty(value = "品类名称")
    private String tobaccoName;

    /**
     * 品牌英文名称
     */
    @ExcelProperty(value = "品牌英文名称")
    private String brandEnname;

    /**
     * 品类英文名称
     */
    @ExcelProperty(value = "品类英文名称")
    private String tobaccoEnname;

    /**
     * 焦油量
     */
    @ExcelProperty(value = "焦油量")
    private Double tar;

    /**
     * 烟气烟碱量
     */
    @ExcelProperty(value = "烟气烟碱量")
    private Double nicotine;

    /**
     * 烟气一氧化碳量
     */
    @ExcelProperty(value = "烟气一氧化碳量")
    private Double co;

    /**
     * 售价
     */
    @ExcelProperty(value = "售价")
    private Double price;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Double sort;


}
