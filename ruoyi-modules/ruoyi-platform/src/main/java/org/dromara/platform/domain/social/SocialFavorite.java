package org.dromara.platform.domain.social;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 收藏信息对象 social_favorite
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_favorite")
public class SocialFavorite extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "favorite_id")
    private Long favoriteId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 收藏用户id
     */
    private Long memberId;

    /**
     * 被收藏用户id
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
