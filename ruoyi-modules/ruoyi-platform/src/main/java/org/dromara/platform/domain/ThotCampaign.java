package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 活动信息对象 thot_campaign
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("thot_campaign")
public class ThotCampaign extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(value = "campaign_id")
    private Long campaignId;

    /**
     * 接入App
     */
    private String appId;

    /**
     * 活动编号
     */
    private String campaignCode;

    /**
     * 活动名称
     */
    private String campaignName;

    /**
     * 活动主图
     */
    private String campaignBannerImg;

    /**
     * 活动内容
     */
    private String campaignContent;

    /**
     * 活动说明
     */
    private String description;

    /**
     * 活动类型
     */
    private String typeCode;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;


}
