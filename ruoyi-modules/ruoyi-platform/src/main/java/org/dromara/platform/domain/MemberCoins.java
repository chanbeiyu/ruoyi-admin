package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 代币信息对象 member_coins
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_coins")
public class MemberCoins extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
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
     * 代币信息0点数1时常2天数
     */
    private Long coinsType;

    /**
     * 剩余点数/时常/天数
     */
    private Long lastCoins;

    /**
     * 过期时间
     */
    private Date expiredDate;

    /**
     * 级别说明
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


}
