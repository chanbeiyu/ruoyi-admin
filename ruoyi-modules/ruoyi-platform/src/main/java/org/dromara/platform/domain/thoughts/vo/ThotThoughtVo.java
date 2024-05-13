package org.dromara.platform.domain.thoughts.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.domain.thoughts.ThotThought;
import org.dromara.platform.translation.ThoughtsTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 思绪信息视图对象 thot_thought
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotThought.class)
public class ThotThoughtVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思绪ID
     */
    @ExcelProperty(value = "思绪ID")
    private Long thoughtId;

    /**
     * 接入App
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = ThoughtsTranslation.key, mapper = "appId", other = ThoughtsTranslation.Other.APP)
    private String appName;

    /**
     * 思绪编号
     */
    @ExcelProperty(value = "思绪编号")
    private String code;

    /**
     * 主图
     */
    @ExcelProperty(value = "主图")
    private String mainImg;

    /**
     * banner图
     */
    @ExcelProperty(value = "banner图")
    private String bannerImg;

    /**
     * 思绪标题
     */
    @ExcelProperty(value = "思绪标题")
    private String title;

    /**
     * 标题样式
     */
    @ExcelProperty(value = "标题样式")
    private Long titleStyle;

    /**
     * 思绪内容
     */
    @ExcelProperty(value = "思绪内容")
    private String content;

    /**
     * 内容样式
     */
    @ExcelProperty(value = "内容样式")
    private Long cententStyle;

    /**
     * 活动状态
     */
    @ExcelProperty(value = "活动状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "en_data_status1")
    private Integer status;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;

}
