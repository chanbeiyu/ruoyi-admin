package org.dromara.basis.social.bo;

import org.dromara.basis.social.entity.SocialComment;
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
 * 评论信息业务对象 social_comment
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialComment.class, reverseConvertGenerate = false)
public class SocialCommentBo extends BaseEntity {

    /**
     * 评论id
     */
    @NotNull(message = "评论id不能为空", groups = { EditGroup.class })
    private Long commentId;

    /**
     * 父评论(回复)id
     */
    @NotNull(message = "父评论(回复)id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long commentPid;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 评论用户id
     */
    @NotNull(message = "评论用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 被评论用户id
     */
    @NotNull(message = "被评论用户id不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 评论标题
     */
    @NotBlank(message = "评论标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String commentTitle;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String commentContnet;

    /**
     * 点赞数
     */
    @NotNull(message = "点赞数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long likeNum;

    /**
     * 允许评论0允许1关注用户2不需要
     */
    @NotNull(message = "允许评论0允许1关注用户2不需要不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer allowComment;

    /**
     * 状态:0发布1删除
     */
    @NotNull(message = "状态:0发布1删除不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 删除时间
     */
    @NotNull(message = "删除时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date deleteTime;

}
