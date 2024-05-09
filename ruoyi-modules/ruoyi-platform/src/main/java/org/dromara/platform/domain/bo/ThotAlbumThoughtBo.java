package org.dromara.platform.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.platform.domain.ThotAlbumThought;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 思集信息视图对象 thot_album_thought
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@AutoMapper(target = ThotAlbumThought.class)
public class ThotAlbumThoughtBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思绪ID
     */
    @NotNull(message = "思集ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long thoughtId;

    /**
     * 是否为封面
     */
    @NotNull(message = "思集ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private String isCover;

    /**
     * 加入时间
     */
    @NotNull(message = "思集ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date createTime;

}
