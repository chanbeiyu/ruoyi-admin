package org.dromara.platform.domain.member;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 成员信息对象 member_info
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_info")
public class MemberInfo extends TenantEntity {

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
     * 成员类型
     */
    private Long typeId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarImg;

    /**
     * 顶部背景图
     */
    private String bannerImg;

    /**
     * 个性签名
     */
    private String whatsup;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
