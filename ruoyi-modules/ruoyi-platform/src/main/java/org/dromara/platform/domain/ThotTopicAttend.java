package org.dromara.platform.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 话题参与对象 thot_topic_attend
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@TableName("thot_topic_attend")
public class ThotTopicAttend {

    @TableId(value = "id")
    private Long id;

    /**
     * 评论id
     */
    private Long attendId;

    /**
     * 话题ID
     */
    private Long topicId;

    /**
     * 参与思绪集
     */
    private Long albumId;

    /**
     * 思绪ID
     */
    private Long thoughtId;

    /**
     * 回复图片地址
     */
    private String img;

    /**
     * 标题
     */
    private String title;

    /**
     * 话题评论
     */
    private String content;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
