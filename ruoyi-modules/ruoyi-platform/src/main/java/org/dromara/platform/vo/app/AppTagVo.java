package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppTag;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;

/**
 * 标签信息视图对象 social_tag
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppTag.class)
public class AppTagVo extends AppBaseVo {

    /**
     * 标签id
     */
    @ExcelProperty(value = "标签id")
    private Long tagId;

    /**
     * 标签编码
     */
    @ExcelProperty(value = "标签编码")
    private String tagCode;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String tagName;

    /**
     * 标签类型
     */
    @ExcelProperty(value = "标签类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String tagType;

    /**
     * 所属主题
     */
    @ExcelProperty(value = "所属主题", converter = ExcelDictConvert.class)
    private Long subjectId;

    @ExcelProperty(value = "所属主题")
    @Translation(type = TranslationConst.key, mapper = "subjectId", other = TranslationConst.SOCIAL_SUBJECT)
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

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
