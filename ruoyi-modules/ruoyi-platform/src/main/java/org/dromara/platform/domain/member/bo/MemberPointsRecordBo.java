package org.dromara.platform.domain.member.bo;

import org.dromara.platform.domain.member.MemberPointsRecord;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

/**
 * 会员积分记录业务对象 member_points_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberPointsRecord.class, reverseConvertGenerate = false)
public class MemberPointsRecordBo extends BaseEntity {

    /**
     * 积分id
     */
    @NotNull(message = "积分id不能为空", groups = { EditGroup.class })
    private Long recordId;

    /**
     * 应用名称
     */
    @NotNull(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 成员id
     */
    @NotNull(message = "成员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 会员类别
     */
    @NotNull(message = "会员类别不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberTypeId;

    /**
     * 操作类型
     */
    @NotBlank(message = "操作类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String actionCode;

    /**
     * 上次积分
     */
    @NotNull(message = "上次积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long beforPoints;

    /**
     * 获取积分
     */
    @NotNull(message = "获取积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long points;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expiredDate;

    /**
     * 状态
     */
    private Long status;

    /**
     * 获取积分说明
     */
    private String description;

}
