package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 话题信息对象 thot_topic
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("thot_topic")
public class ThotTopic extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 话题ID
     */
    @TableId(value = "topic_id")
    private Long topicId;

    /**
     * 接入App
     */
    private String appId;

    /**
     * 话题编码
     */
    private String topicCode;

    /**
     * 话题名称
     */
    private String topicName;

    /**
     * 话题主图
     */
    private String topicBannerImg;

    /**
     * 话题说明
     */
    private String topicDescription;

    /**
     * 话题内容
     */
    private String topicContent;

    /**
     * 话题状态
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
