package org.dromara.platform.domain.vo.thoughts;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.domain.ThotCampaign;
import org.dromara.platform.translation.ThoughtsTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 活动信息视图对象 thot_campaign
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotCampaign.class)
public class ThotCampaignVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long campaignId;

    /**
     * 接入App
     */
    private String appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = ThoughtsTranslation.key, mapper = "appId", other = ThoughtsTranslation.Other.APP)
    private String appName;

    /**
     * 活动编号
     */
    @ExcelProperty(value = "活动编号")
    private String campaignCode;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String campaignName;

    /**
     * 活动主图
     */
    @ExcelProperty(value = "活动主图")
    private String campaignBannerImg;

    /**
     * 活动内容
     */
    @ExcelProperty(value = "活动内容")
    private String campaignContent;

    /**
     * 活动说明
     */
    @ExcelProperty(value = "活动说明")
    private String description;

    /**
     * 活动类型
     */
    @ExcelProperty(value = "活动类型")
    private String typeCode;

    /**
     * 活动状态（0创建1发布2结束3删除）
     */
    @ExcelProperty(value = "活动状态", converter = ExcelDictConvert.class)
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
