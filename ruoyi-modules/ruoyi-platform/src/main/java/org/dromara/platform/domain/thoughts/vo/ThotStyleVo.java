package org.dromara.platform.domain.thoughts.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.domain.thoughts.ThotStyle;
import org.dromara.platform.translation.ThoughtsTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 样式信息视图对象 thot_style
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotStyle.class)
public class ThotStyleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 样式id
     */
    @ExcelProperty(value = "样式id")
    private Long styleId;

    /**
     * 接入App
     */
    @ExcelProperty(value = "接入App")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = ThoughtsTranslation.key, mapper = "appId", other = ThoughtsTranslation.Other.APP)
    private String appName;

    /**
     * 样式编码
     */
    @ExcelProperty(value = "样式编码")
    private String styleCode;

    /**
     * 样式名称
     */
    @ExcelProperty(value = "样式名称")
    private String styleName;

    /**
     * 样式
     */
    @ExcelProperty(value = "样式")
    private String styleContent;

    /**
     * 状态0正常1锁定
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 样式描述
     */
    @ExcelProperty(value = "样式描述")
    private String description;


}
