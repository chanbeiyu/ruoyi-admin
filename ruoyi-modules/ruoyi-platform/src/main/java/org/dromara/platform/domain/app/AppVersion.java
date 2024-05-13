package org.dromara.platform.domain.app;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import org.dromara.common.tenant.core.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 应用版本信息对象 app_version
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_version")
public class AppVersion extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 版本id
     */
    @TableId(value = "version_id")
    private Long versionId;

    /**
     * AppId
     */
    private Long appId;

    /**
     * 版本号
     */
    @Version
    private String version;

    /**
     * 内部版本号
     */
    private String buildVersion;

    /**
     * 强制升级
     */
    private String forced;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
