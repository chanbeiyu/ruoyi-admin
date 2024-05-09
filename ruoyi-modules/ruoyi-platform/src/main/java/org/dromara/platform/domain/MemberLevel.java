package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员级别信息对象 member_level
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_level")
public class MemberLevel extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @TableId(value = "level_id")
    private Long levelId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 会员类别
     */
    private Long memberTypeId;

    /**
     * 级别编码
     */
    private String levelCode;

    /**
     * 级别名称
     */
    private String levelName;

    /**
     * 最小积分
     */
    private Long leastPoints;

    /**
     * 状态
     */
    private String status;

    /**
     * 最大积分
     */
    private Long biggestPoints;

    /**
     * 级别说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
