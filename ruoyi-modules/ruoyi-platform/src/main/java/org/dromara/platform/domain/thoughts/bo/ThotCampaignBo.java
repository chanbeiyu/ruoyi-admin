package org.dromara.platform.domain.thoughts.bo;

import org.dromara.platform.domain.thoughts.ThotCampaign;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 活动信息业务对象 thot_campaign
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotCampaign.class, reverseConvertGenerate = false)
public class ThotCampaignBo extends BaseEntity {

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { EditGroup.class })
    private Long campaignId;

    /**
     * 接入App
     */
    @NotBlank(message = "接入App不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;
    private List<Long> appIds;

    /**
     * 活动编号
     */
    @NotNull(message = "活动编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignCode;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignName;

    /**
     * 活动主图
     */
    @NotBlank(message = "活动主图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignBannerImg;

    /**
     * 活动内容
     */
    @NotBlank(message = "活动内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignContent;

    /**
     * 活动说明
     */
    @NotBlank(message = "活动说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 活动类型
     */
    @NotBlank(message = "活动类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date beginTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;


}
