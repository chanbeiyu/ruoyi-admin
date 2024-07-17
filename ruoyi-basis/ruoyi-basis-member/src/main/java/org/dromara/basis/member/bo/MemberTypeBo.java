package org.dromara.basis.member.bo;

import org.dromara.basis.member.entity.MemberType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 会员类型信息业务对象 member_type
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberType.class, reverseConvertGenerate = false)
public class MemberTypeBo extends BaseEntity {

    /**
     * 级别id
     */
    @NotNull(message = "级别id不能为空", groups = { EditGroup.class })
    private Long typeId;


    /**
     * 会员类型编码
     */
    @NotBlank(message = "会员类型编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeCode;

    /**
     * 会员类型名称
     */
    @NotBlank(message = "会员类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeName;

    /**
     * 最大积分
     */
    @NotNull(message = "最大积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long maxPoints;

    /**
     * 最大积分
     */
    @NotNull(message = "最大级别不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer maxLevel;

    /**
     * 会员类型说明
     */
    @NotBlank(message = "会员类型说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 状态
     */
    private String status;

}
