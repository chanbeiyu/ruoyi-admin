package org.dromara.platform.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basis.apptw.entity.ApptwSmokeDays;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * 日抽烟数据视图对象 apptw_smoke_days
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApptwSmokeDays.class)
public class ApptwSmokeDaysVo implements Serializable {

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
     * 日期
     */
    @ExcelProperty(value = "日期")
    private LocalDate day;

    /**
     * 抽烟数
     */
    @ExcelProperty(value = "抽烟数")
    private Integer total;

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
     * 支出
     */
    @ExcelProperty(value = "支出")
    private Long spend;


}
