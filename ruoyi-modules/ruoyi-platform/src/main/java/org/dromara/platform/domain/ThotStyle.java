package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 样式信息对象 thot_style
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("thot_style")
public class ThotStyle extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 样式id
     */
    @TableId(value = "style_id")
    private Long styleId;

    /**
     * 接入App
     */
    private Long appId;

    /**
     * 样式编码
     */
    private String styleCode;

    /**
     * 样式名称
     */
    private String styleName;

    /**
     * 样式
     */
    private String styleContent;

    /**
     * 状态
     */
    private String status;

    /**
     * 样式描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
