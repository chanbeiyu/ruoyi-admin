package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 频道信息对象 thot_channel
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("thot_channel")
public class ThotChannel extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "channel_id")
    private Long channelId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 频道编码
     */
    private String channelCode;

    /**
     * 频道名称
     */
    private String channelName;

    /**
     * 状态
     */
    private String status;

    /**
     * 频道描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
