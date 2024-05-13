package org.dromara.platform.domain.thoughts.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.domain.thoughts.ThotChannel;
import org.dromara.platform.translation.ThoughtsTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道信息视图对象 thot_channel
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotChannel.class)
public class ThotChannelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long channelId;

    /**
     * 应用名称
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = ThoughtsTranslation.key, mapper = "appId", other = ThoughtsTranslation.Other.APP)
    private String appName;

    /**
     * 频道编码
     */
    @ExcelProperty(value = "频道编码")
    private String channelCode;

    /**
     * 频道名称
     */
    @ExcelProperty(value = "频道名称")
    private String channelName;

    /**
     * 状态0正常1锁定
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 频道描述
     */
    @ExcelProperty(value = "频道描述")
    private String description;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}
