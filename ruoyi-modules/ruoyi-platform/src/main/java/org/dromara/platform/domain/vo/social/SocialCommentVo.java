package org.dromara.platform.domain.vo.social;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.SocialComment;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 评论信息视图对象 social_comment
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialComment.class)
public class SocialCommentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @ExcelProperty(value = "评论id")
    private Long commentId;

    /**
     * 父评论(回复)id
     */
    @ExcelProperty(value = "父评论(回复)id")
    private Long commentPid;

    /**
     * 应用名称
     */
    private String appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 评论用户id
     */
    @ExcelProperty(value = "评论用户id")
    private Long memberId;

    /**
     * 被评论用户id
     */
    @ExcelProperty(value = "被评论用户id")
    private Long toMemberId;

    /**
     * 所属主题
     */
    @ExcelProperty(value = "所属主题")
    private Long subjectId;

    /**
     * 目标id
     */
    @ExcelProperty(value = "目标id")
    private String targetId;

    /**
     * 目标内容
     */
    @ExcelProperty(value = "目标内容")
    private String targetContent;

    /**
     * 评论标题
     */
    @ExcelProperty(value = "评论标题")
    private String commentTitle;

    /**
     * 评论内容
     */
    @ExcelProperty(value = "评论内容")
    private String commentContnet;

    /**
     * 点赞数
     */
    @ExcelProperty(value = "点赞数")
    private Long likeNum;

    /**
     * 允许评论0允许1关注用户2不需要
     */
    @ExcelProperty(value = "允许评论0允许1关注用户2不需要")
    private Integer allowComment;

    /**
     * 状态:0发布1删除
     */
    @ExcelProperty(value = "状态:0发布1删除")
    private Long status;

    /**
     * 删除时间
     */
    @ExcelProperty(value = "删除时间")
    private Date deleteTime;


}
