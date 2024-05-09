package org.dromara.platform.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 思集信息对象 thot_album_thought
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@TableName("thot_album_thought")
public class ThotAlbumThought {

    @TableId(value = "id")
    private Long id;

    /**
     * 思集ID
     */
    private Long albumId;

    /**
     * 思绪ID
     */
    private Long thoughtId;

    /**
     * 是否为封面
     */
    private String isCover = "N";

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
