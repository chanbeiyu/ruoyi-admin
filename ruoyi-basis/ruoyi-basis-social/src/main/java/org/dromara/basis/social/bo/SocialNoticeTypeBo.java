package org.dromara.basis.social.bo;

import org.dromara.basis.social.entity.SocialNoticeType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 信息通知类型业务对象 social_notice_type
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialNoticeType.class, reverseConvertGenerate = false)
public class SocialNoticeTypeBo extends BaseEntity {

    /**
     * 通知类型id
     */
    @NotNull(message = "通知类型id不能为空", groups = { EditGroup.class })
    private Long noticeTypeId;


    /**
     * 通知类型编码
     */
    @NotBlank(message = "通知类型编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String noticeTypeCode;

    /**
     * 通知类型名称
     */
    @NotBlank(message = "通知类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String noticeTypeName;

    /**
     * 状态
     */
    private String status;

    /**
     * 主题描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
