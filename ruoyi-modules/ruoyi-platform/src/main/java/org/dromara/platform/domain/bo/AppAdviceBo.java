package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.AppAdvice;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 意见反馈信息业务对象 app_advice
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppAdvice.class, reverseConvertGenerate = false)
public class AppAdviceBo extends BaseEntity {

    /**
     * 留言id
     */
    @NotNull(message = "留言id不能为空", groups = { EditGroup.class })
    private Long adviceId;

    /**
     * AppId
     */
    @NotNull(message = "AppId不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 成员id
     */
    @NotNull(message = "成员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contact;

    /**
     * 反馈内容
     */
    @NotBlank(message = "反馈内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;


}
