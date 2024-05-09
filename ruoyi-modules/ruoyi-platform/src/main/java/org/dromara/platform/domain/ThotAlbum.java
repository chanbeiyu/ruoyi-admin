package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 思集信息对象 thot_album
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("thot_album")
public class ThotAlbum extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思集ID
     */
    @TableId(value = "album_id")
    private Long albumId;

    /**
     * 接入App
     */
    private Long appId;

    /**
     * 思绪编号
     */
    private String albumTitle;

    /**
     * 思绪描述
     */
    private String description;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 备注
     */
    private String remark;


}
