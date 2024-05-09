package org.dromara.platform.domain.vo.thoughts;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.domain.ThotTopic;
import org.dromara.platform.translation.ThoughtsTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 话题信息视图对象 thot_topic
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotTopic.class)
public class ThotTopicVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 话题ID
     */
    @ExcelProperty(value = "话题ID")
    private Long topicId;

    /**
     * 接入App
     */
    private String appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = ThoughtsTranslation.key, mapper = "appId", other = ThoughtsTranslation.Other.APP)
    private String appName;

    /**
     * 话题编码
     */
    @ExcelProperty(value = "话题编码")
    private String topicCode;

    /**
     * 话题名称
     */
    @ExcelProperty(value = "话题名称")
    private String topicName;

    /**
     * 话题主图
     */
    @ExcelProperty(value = "话题主图")
    private String topicBannerImg;

    /**
     * 话题说明
     */
    @ExcelProperty(value = "话题说明")
    private String topicDescription;

    /**
     * 话题内容
     */
    @ExcelProperty(value = "话题内容")
    private String topicContent;

    /**
     * 话题状态
     */
    @ExcelProperty(value = "话题状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "en_data_status2")
    private Integer status;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;


}
