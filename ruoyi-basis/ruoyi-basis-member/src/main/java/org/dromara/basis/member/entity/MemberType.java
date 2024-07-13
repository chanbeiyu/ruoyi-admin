package org.dromara.basis.member.entity;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员类型信息对象 member_type
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_type")
public class MemberType extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @TableId(value = "type_id")
    private Long typeId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 会员类型编码
     */
    private String typeCode;

    /**
     * 会员类型名称
     */
    private String typeName;

    /**
     * 最大积分
     */
    private Long maxPoints;

    /**
     * 最大级别
     */
    private Integer maxLevel;

    /**
     * 会员类型说明
     */
    private String description;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
