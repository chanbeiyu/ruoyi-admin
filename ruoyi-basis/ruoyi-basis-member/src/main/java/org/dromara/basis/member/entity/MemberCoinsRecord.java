package org.dromara.basis.member.entity;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 代币记录信息对象 member_coins_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_coins_record")
public class MemberCoinsRecord extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @TableId(value = "record_id")
    private Long recordId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 成员id
     */
    private Long memberId;

    /**
     * 代币类型0点数1时常2天数
     */
    private Long coinsType;

    /**
     * 动作类型
     */
    private String actionCode;

    /**
     * 先前代币
     */
    private Long beforCoins;

    /**
     * 先前过期时间
     */
    private Date beforExpiredDate;

    /**
     * 代币变化
     */
    private Long changeCoins;

    /**
     * 订单号
     */
    private String orderNo;

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
