package org.dromara.basis.social.bo;

import org.dromara.basis.social.entity.SocialFavorite;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 收藏信息业务对象 social_favorite
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialFavorite.class, reverseConvertGenerate = false)
public class SocialFavoriteBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long favoriteId;


    /**
     * 收藏用户id
     */
    @NotNull(message = "收藏用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 被收藏用户id
     */
    @NotNull(message = "被收藏用户id不能为空", groups = { AddGroup.class, EditGroup.class })
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
