package org.dromara.platform.domain.social.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.social.SocialSubject;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 内容主题视图对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialSubject.class)
public class SocialSubjectVo extends AppBaseVo {

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long subjectId;

    /**
     * 主题编码
     */
    @ExcelProperty(value = "主题编码")
    private String subjectCode;

    /**
     * 主题名称
     */
    @ExcelProperty(value = "主题名称")
    private String subjectName;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 主题描述
     */
    @ExcelProperty(value = "主题描述")
    private String description;

}
