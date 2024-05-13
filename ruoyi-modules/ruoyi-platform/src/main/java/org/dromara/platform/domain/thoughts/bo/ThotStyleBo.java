package org.dromara.platform.domain.thoughts.bo;

import org.dromara.platform.domain.thoughts.ThotStyle;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 样式信息业务对象 thot_style
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotStyle.class, reverseConvertGenerate = false)
public class ThotStyleBo extends BaseEntity {

    /**
     * 样式id
     */
    @NotNull(message = "样式id不能为空", groups = { EditGroup.class })
    private Long styleId;

    /**
     * 接入App
     */
    @NotNull(message = "接入App不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 样式编码
     */
    @NotBlank(message = "样式编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String styleCode;

    /**
     * 样式名称
     */
    @NotBlank(message = "样式名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String styleName;

    /**
     * 样式
     */
    @NotBlank(message = "样式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String styleContent;

    /**
     * 状态
     */
    private String status;

    /**
     * 样式描述
     */
    private String description;

}
