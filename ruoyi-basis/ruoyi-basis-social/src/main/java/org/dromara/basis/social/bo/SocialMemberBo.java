package org.dromara.basis.social.bo;

import org.dromara.basis.social.entity.SocialMember;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 成员信息业务对象 social_member
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialMember.class, reverseConvertGenerate = false)
public class SocialMemberBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long memberId;

    /**
     * 关联用户标识
     */
    @NotBlank(message = "关联用户标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String unionId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 会员等级
     */
    @NotNull(message = "会员等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long vipLevel;

    /**
     * 积分
     */
    @NotNull(message = "积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long points;

    /**
     * 积分等级
     */
    @NotNull(message = "积分等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pointsLevel;

    /**
     * 状态:0正常1锁定2注销
     */
    @NotNull(message = "状态:0正常1锁定2注销不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
