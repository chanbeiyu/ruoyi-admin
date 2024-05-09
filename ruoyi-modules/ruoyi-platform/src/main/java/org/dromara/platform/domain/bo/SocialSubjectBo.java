package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.SocialSubject;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 内容主题业务对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialSubject.class, reverseConvertGenerate = false)
public class SocialSubjectBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long subjectId;

    /**
     * 应用名称
     */
    @NotNull(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 主题编码
     */
    @NotBlank(message = "主题编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String subjectCode;

    /**
     * 主题名称
     */
    @NotBlank(message = "主题名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
