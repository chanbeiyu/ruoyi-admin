package org.dromara.platform.domain.vo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
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
public class ThotAlbumThoughtVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联信息ID
     */
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
     * 思绪编号
     */
    private String code;

    /**
     * 思绪标题
     */
    private String title;

    /**
     * 主图
     */
    private String mainImg;

    /**
     * banner图
     */
    private String bannerImg;

    /**
     * 是否为封面
     */
    private String isCover;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 加入时间
     */
    private Date createTime;

}
