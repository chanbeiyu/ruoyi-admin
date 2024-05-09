package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.ThotThought;
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
 * 思绪信息业务对象 thot_thought
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotThought.class, reverseConvertGenerate = false)
public class ThotThoughtBo extends BaseEntity {

    /**
     * 思绪ID
     */
    @NotNull(message = "思绪ID不能为空", groups = { EditGroup.class })
    private Long thoughtId;

    /**
     * 接入App
     */
    @NotNull(message = "接入App不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 思绪编号
     */
    @NotBlank(message = "思绪编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 主图
     */
    @NotBlank(message = "主图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mainImg;

    /**
     * banner图
     */
    @NotBlank(message = "banner图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerImg;

    /**
     * 思绪标题
     */
    @NotBlank(message = "思绪标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 标题样式
     */
    @NotNull(message = "标题样式不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long titleStyle;

    /**
     * 思绪内容
     */
    @NotBlank(message = "思绪内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 内容样式
     */
    @NotNull(message = "内容样式不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cententStyle;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date publishTime;

}
