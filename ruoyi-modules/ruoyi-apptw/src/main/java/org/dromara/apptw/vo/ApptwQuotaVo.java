package org.dromara.platform.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basis.apptw.entity.ApptwQuota;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 目标限额视图对象 apptw_quota
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApptwQuota.class)
public class ApptwQuotaVo implements Serializable {

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
     * 限额月份
     */
    @ExcelProperty(value = "限额月份")
    private String month;

    /**
     * 限额月总数
     */
    @ExcelProperty(value = "限额月总数")
    private Long total;

    /**
     * 限额月平均
     */
    @ExcelProperty(value = "限额月平均")
    private Long avg;

    /**
     * 限额天数
     */
    @ExcelProperty(value = "限额天数")
    private Long days;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;


}
