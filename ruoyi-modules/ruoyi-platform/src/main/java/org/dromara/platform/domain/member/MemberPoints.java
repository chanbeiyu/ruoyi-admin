package org.dromara.platform.domain.member;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员积分对象 member_points
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_points")
public class MemberPoints extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 成员id
     */
    private Long memberId;

    /**
     * 会员类别
     */
    private Long memberTypeId;

    /**
     * 总积分
     */
    private Long totalPoints;

    /**
     * 最终会员等级
     */
    private Long lastLevel;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 获取积分说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
