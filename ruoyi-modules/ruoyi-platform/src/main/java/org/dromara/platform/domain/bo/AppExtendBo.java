package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.AppExtend;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 应用扩展信息业务对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppExtend.class, reverseConvertGenerate = false)
public class AppExtendBo extends BaseEntity {

    /**
     * 组件id
     */
    @NotNull(message = "组件id不能为空", groups = { EditGroup.class })
    private Long extendId;

    /**
     * AppId
     */
    @NotNull(message = "AppId不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 数据key
     */
    @NotBlank(message = "数据key不能为空", groups = { AddGroup.class, EditGroup.class })
    private String key;

    /**
     * 数据标签
     */
    @NotBlank(message = "数据标签不能为空", groups = { AddGroup.class, EditGroup.class })
    private String label;

    /**
     * 扩展数据
     */
    private String value;

    /**
     * 扩展数据版本
     */
    @NotBlank(message = "扩展数据版本不能为空", groups = { AddGroup.class, EditGroup.class })
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
