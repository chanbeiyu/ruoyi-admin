package org.dromara.platform.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.platform.domain.ThotAlbum;

import java.util.Date;
import java.util.List;

/**
 * 思集信息业务对象 thot_album
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotAlbum.class, reverseConvertGenerate = false)
public class ThotAlbumBo extends BaseEntity {

    /**
     * 思集ID
     */
    @NotNull(message = "思集ID不能为空", groups = {EditGroup.class})
    private Long albumId;

    /**
     * 接入App
     */
    @NotNull(message = "接入App不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long appId;

    /**
     * 思集标题
     */
    @NotBlank(message = "思集标题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String albumTitle;

    /**
     * 思绪描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date publishTime;


    @TableField(exist = false)
    private List<Long> appIds;

    /**
     * 思绪编号
     */
    @TableField(exist = false)
    @NotEmpty(message = "思绪不能为空", groups = {AddGroup.class, EditGroup.class})
    private List<ThotAlbumThoughtBo> albumThoughts;

}
