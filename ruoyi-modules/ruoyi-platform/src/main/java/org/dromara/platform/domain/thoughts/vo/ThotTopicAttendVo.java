package org.dromara.platform.domain.thoughts.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.thoughts.ThotTopicAttend;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 话题参与视图对象 thot_topic_attend
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotTopicAttend.class)
public class ThotTopicAttendVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @ExcelProperty(value = "评论id")
    private Long attendId;

    /**
     * 话题ID
     */
    @ExcelProperty(value = "话题ID")
    private Long topicId;

    /**
     * 参与思绪集
     */
    @ExcelProperty(value = "参与思绪集")
    private Long albumId;

    /**
     * 思绪ID
     */
    @ExcelProperty(value = "思绪ID")
    private Long thoughtId;

    /**
     * 回复图片地址
     */
    @ExcelProperty(value = "回复图片地址")
    private String img;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 话题评论
     */
    @ExcelProperty(value = "话题评论")
    private String content;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
