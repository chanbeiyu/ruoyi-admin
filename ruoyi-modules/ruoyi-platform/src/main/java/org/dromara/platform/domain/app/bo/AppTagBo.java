package org.dromara.platform.domain.app.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.platform.domain.app.AppTag;

import java.util.List;

/**
 * 标签信息业务对象 social_tag
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppTag.class, reverseConvertGenerate = false)
public class AppTagBo extends BaseEntity {

    /**
     * 标签id
     */
    @NotNull(message = "标签id不能为空", groups = {EditGroup.class})
    private Long tagId;

    /**
     * 标签编码
     */
    @NotBlank(message = "标签编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String tagCode;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String tagName;

    /**
     * 标签类型
     */
    @NotBlank(message = "标签类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String tagType;

    /**
     * 状态
     */
    private String status;

    /**
     * 应用名称
     */
    @NotBlank(message = "App标识不能为空", groups = {AddGroup.class, EditGroup.class})
    private String appId;

    /**
     * 所属主题
     */
    @NotNull(message = "所属主题不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long subjectId;

    private List<Long> subjectIds;

    /**
     * 主题描述
     */
    private String description;

}
