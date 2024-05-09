package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.MemberLevel;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 会员级别信息业务对象 member_level
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberLevel.class, reverseConvertGenerate = false)
public class MemberLevelBo extends BaseEntity {

    /**
     * 级别id
     */
    @NotNull(message = "级别id不能为空", groups = { EditGroup.class })
    private Long levelId;

    /**
     * 应用名称
     */
    @NotNull(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;

    /**
     * 会员类别
     */
    @NotNull(message = "会员类别不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberTypeId;

    /**
     * 级别编码
     */
    @NotBlank(message = "级别编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String levelCode;

    /**
     * 级别名称
     */
    @NotBlank(message = "级别名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String levelName;

    /**
     * 最小积分
     */
    @NotNull(message = "最小积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long leastPoints;

    /**
     * 状态
     */
    private String status;

    /**
     * 最大积分
     */
    @NotNull(message = "最大积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long biggestPoints;

    /**
     * 级别说明
     */
    @NotBlank(message = "级别说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

}
