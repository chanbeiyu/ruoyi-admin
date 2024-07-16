package org.dromara.platform.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basis.apptw.entity.ApptwSmoke;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 抽烟记录视图对象 apptw_smoke
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApptwSmoke.class)
public class ApptwSmokeVo implements Serializable {

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
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "memberId", other = TranslationConst.MEMBER_INFO_NAME)
    private String memberName;

    /**
     * 喜好ID
     */
    @ExcelProperty(value = "喜好ID")
    private Long likeId;

    /**
     * 品类ID
     */
    @ExcelProperty(value = "品类ID")
    private Long tobaccoId;

    /**
     * 时间
     */
    @ExcelProperty(value = "时间")
    private Date dateTime;

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
     * 支出
     */
    @ExcelProperty(value = "支出")
    private Double spend;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;


}
