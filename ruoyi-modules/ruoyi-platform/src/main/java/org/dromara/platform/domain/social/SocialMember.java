package org.dromara.platform.domain.social;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 成员信息对象 social_member
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_member")
public class SocialMember extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "member_id")
    private Long memberId;

    /**
     * 关联用户标识
     */
    private String unionId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 会员等级
     */
    private Long vipLevel;

    /**
     * 积分
     */
    private Long points;

    /**
     * 积分等级
     */
    private Long pointsLevel;

    /**
     * 状态:0正常1锁定2注销
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


}
