package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 思绪信息对象 thot_thought
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("thot_thought")
public class ThotThought extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思绪ID
     */
    @TableId(value = "thought_id")
    private Long thoughtId;

    /**
     * 接入App
     */
    private Long appId;

    /**
     * 思绪编号
     */
    private String code;

    /**
     * 主图
     */
    private String mainImg;

    /**
     * banner图
     */
    private String bannerImg;

    /**
     * 思绪标题
     */
    private String title;

    /**
     * 标题样式
     */
    private Long titleStyle;

    /**
     * 思绪内容
     */
    private String content;

    /**
     * 内容样式
     */
    private Long cententStyle;

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
