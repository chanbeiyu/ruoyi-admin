package org.dromara.platform.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;
import org.dromara.platform.mapstruct.AppExtendMapstructMapper;

import java.io.Serial;

/**
 * 应用信息对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_info")
public class AppInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * AppId
     */
    @TableId(value = "app_id")
    private Long appId;

    /**
     * 编码
     */
    private String appCode;

    /**
     * 名称
     */
    private String appName;

    /**
     * 是否为内部项目
     */
    private String isInternal;

    /**
     * AccessKeyId
     */
    private String accessKeyId;

    /**
     * SecretAccessKey
     */
    private String secretAccessKey;

    /**
     * 签名加盐值
     */
    private String salt;

    /**
     * 允许的域,支持模糊匹配
     */
    private String domains;

    /**
     * 状态
     */
    private String status;

    /**
     * 扩展信息
     */
    private String extend;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
