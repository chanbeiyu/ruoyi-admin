package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.ThotChannel;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 频道信息业务对象 thot_channel
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotChannel.class, reverseConvertGenerate = false)
public class ThotChannelBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long channelId;

    /**
     * 应用名称
     */
    @NotNull(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 频道编码
     */
    @NotBlank(message = "频道编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channelCode;

    /**
     * 频道名称
     */
    @NotBlank(message = "频道名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channelName;

    /**
     * 状态0正常1锁定
     */
    private String status;

    /**
     * 频道描述
     */
    private String description;

}
