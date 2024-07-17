package org.dromara.basis.app.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppSubject;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.List;

/**
 * 内容主题业务对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppSubject.class, reverseConvertGenerate = false)
public class AppSubjectBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long subjectId;

    /**
     * 主题编码
     */
    @NotBlank(message = "主题编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String subjectCode;

    /**
     * 主题名称
     */
    @NotBlank(message = "主题名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String subjectName;

    /**
     * 状态
     */
    private String status;

    /**
     * 主题描述
     */
    private String description;

}
