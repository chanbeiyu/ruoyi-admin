package org.dromara.basis.member.entity;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员积分对象 member_action
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_action")
public class MemberAction extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @TableId(value = "action_id")
    private Long actionId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员类别
     */
    private Long memberTypeId;

    /**
     * 动作编号
     */
    private String actionCode;

    /**
     * 动作名称
     */
    private String actionName;

    /**
     * 积分点
     */
    private Long points;

    /**
     * 代币点
     */
    private Long conis;

    /**
     * 状态
     */
    private Long status;

    /**
     * 说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
