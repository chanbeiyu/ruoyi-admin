package org.dromara.platform.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;

/**
 * 应用扩展信息对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_extend")
public class AppExtend extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 组件id
     */
    @TableId(value = "extend_id")
    private Long extendId;

    /**
     * AppId
     */
    private Long appId;

    /**
     * 数据key
     */
    @TableField("`key`")
    private String key;

    /**
     * 数据标签
     */
    @TableField("`label`")
    private String label;

    /**
     * 扩展数据
     */
    @TableField("`value`")
    private String value;

    /**
     * 扩展数据版本
     */
    private String version;

    /**
     * 数据说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
