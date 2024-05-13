package org.dromara.platform.domain.social.bo;

import org.dromara.platform.domain.social.SocialLike;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 点赞信息业务对象 social_like
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialLike.class, reverseConvertGenerate = false)
public class SocialLikeBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long likeId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 点赞用户id
     */
    @NotNull(message = "点赞用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 被点赞用户id
     */
    @NotNull(message = "被点赞用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long toMemberId;

    /**
     * 所属主题
     */
    @NotNull(message = "所属主题不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long subjectId;

    /**
     * 目标id
     */
    @NotBlank(message = "目标id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetId;

    /**
     * 目标内容
     */
    @NotBlank(message = "目标内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String targetContent;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
