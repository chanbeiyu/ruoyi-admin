package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.MemberAction;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 会员积分业务对象 member_action
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberAction.class, reverseConvertGenerate = false)
public class MemberActionBo extends BaseEntity {

    /**
     * 积分id
     */
    @NotNull(message = "积分id不能为空", groups = { EditGroup.class })
    private Long actionId;

    /**
     * 应用名称
     */
    @NotNull(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 会员id
     */
    @NotNull(message = "会员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 会员类别
     */
    @NotNull(message = "会员类别不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberTypeId;

    /**
     * 动作编号
     */
    @NotBlank(message = "动作编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String actionCode;

    /**
     * 动作名称
     */
    @NotBlank(message = "动作名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String actionName;

    /**
     * 积分点
     */
    @NotNull(message = "积分点不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long points;

    /**
     * 代币点
     */
    @NotNull(message = "代币点不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long conis;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 说明
     */
    @NotBlank(message = "说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

}
