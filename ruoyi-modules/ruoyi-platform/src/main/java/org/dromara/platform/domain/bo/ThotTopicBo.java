package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.ThotTopic;
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
 * 话题信息业务对象 thot_topic
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotTopic.class, reverseConvertGenerate = false)
public class ThotTopicBo extends BaseEntity {

    /**
     * 话题ID
     */
    @NotNull(message = "话题ID不能为空", groups = { EditGroup.class })
    private Long topicId;

    /**
     * 接入App
     */
    @NotBlank(message = "接入App不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;
    private List<Long> appIds;

    /**
     * 话题编码
     */
    @NotBlank(message = "话题编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topicCode;

    /**
     * 话题名称
     */
    @NotBlank(message = "话题名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topicName;

    /**
     * 话题主图
     */
    @NotBlank(message = "话题主图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topicBannerImg;

    /**
     * 话题说明
     */
    @NotBlank(message = "话题说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topicDescription;

    /**
     * 话题内容
     */
    @NotBlank(message = "话题内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topicContent;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date beginTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

}
