package org.dromara.platform.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 点赞信息对象 social_like
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_like")
public class SocialLike extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "like_id")
    private Long likeId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 点赞用户id
     */
    private Long memberId;

    /**
     * 被点赞用户id
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
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
