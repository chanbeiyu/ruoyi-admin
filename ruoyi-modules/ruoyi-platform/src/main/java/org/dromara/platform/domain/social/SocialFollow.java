package org.dromara.platform.domain.social;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 关注信息对象 social_follow
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_follow")
public class SocialFollow extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "follow_id")
    private Long followId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 关注用户id
     */
    private Long toMemberId;

    /**
     * 关注状态0关注1互相关注2取消关注
     */
    private Integer status;

    /**
     * 取消关注时间
     */
    private Date unfollowTime;

    /**
     * 备注
     */
    private String remark;


}
