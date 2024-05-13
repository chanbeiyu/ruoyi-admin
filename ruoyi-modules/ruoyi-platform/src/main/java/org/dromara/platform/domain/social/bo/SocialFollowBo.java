package org.dromara.platform.domain.social.bo;

import org.dromara.platform.domain.social.SocialFollow;
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
 * 关注信息业务对象 social_follow
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialFollow.class, reverseConvertGenerate = false)
public class SocialFollowBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long followId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 关注用户id
     */
    @NotNull(message = "关注用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long toMemberId;

    /**
     * 关注状态0关注1互相关注2取消关注
     */
    @NotNull(message = "关注状态0关注1互相关注2取消关注不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 取消关注时间
     */
    @NotNull(message = "取消关注时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date unfollowTime;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
