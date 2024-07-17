package org.dromara.basis.app.bo;

import org.dromara.basis.app.entity.AppVersion;
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
 * 应用版本信息业务对象 app_version
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppVersion.class, reverseConvertGenerate = false)
public class AppVersionBo extends BaseEntity {

    /**
     * 版本id
     */
    @NotNull(message = "版本id不能为空", groups = { EditGroup.class })
    private Long versionId;

    /**
     * AppId
     */
//    @NotNull(message = "应用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 版本号
     */
    @NotBlank(message = "版本号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String showVersion;

    /**
     * 内部版本号
     */
    @NotBlank(message = "内部版本号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String buildVersion;

    /**
     * 强制升级
     */
    private String forced;

    /**
     * 发布时间
     */
    @NotNull(message = "发布时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date publishTime;

     /**
     * 状态
     */
    private String status;

     /**
     * 版本描述
     */
    private String description;

}
