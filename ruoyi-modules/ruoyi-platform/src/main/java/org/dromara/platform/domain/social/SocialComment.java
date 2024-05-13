package org.dromara.platform.domain.social;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 评论信息对象 social_comment
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_comment")
public class SocialComment extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id")
    private Long commentId;

    /**
     * 父评论(回复)id
     */
    private Long commentPid;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 评论用户id
     */
    private Long memberId;

    /**
     * 被评论用户id
     */
    private Long toMemberId;

    /**
     * 所属主题
     */
    private Long subjectId;

    /**
     * 目标id
     */
    private String targetId;

    /**
     * 目标内容
     */
    private String targetContent;

    /**
     * 评论标题
     */
    private String commentTitle;

    /**
     * 评论内容
     */
    private String commentContnet;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * 允许评论0允许1关注用户2不需要
     */
    private Integer allowComment;

    /**
     * 状态:0发布1删除
     */
    private Long status;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 备注
     */
    private String remark;


}
