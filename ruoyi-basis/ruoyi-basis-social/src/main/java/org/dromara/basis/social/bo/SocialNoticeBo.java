package org.dromara.basis.social.bo;

import org.dromara.basis.social.entity.SocialNotice;
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
 * 信息通知业务对象 social_notice
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialNotice.class, reverseConvertGenerate = false)
public class SocialNoticeBo extends BaseEntity {

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空", groups = { EditGroup.class })
    private Long noticeId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 触发通知用户id
     */
    @NotNull(message = "触发通知用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long triggerMemberId;

    /**
     * 触发通知内容id
     */
    @NotBlank(message = "触发通知内容id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String triggerId;

    /**
     * 通知内容
     */
    @NotBlank(message = "通知内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String triggerContent;

    /**
     * 通知类型
     */
    @NotBlank(message = "通知类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeCode;

    /**
     * 通知状态（0发送失败1已发送2已阅读3删除）
     */
    @NotNull(message = "通知状态（0发送失败1已发送2已阅读3删除）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 发送时间
     */
    @NotNull(message = "发送时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sendTime;

    /**
     * 阅读时间
     */
    @NotNull(message = "阅读时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date readTime;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
