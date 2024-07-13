package org.dromara.platform.vo.social;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.basis.social.entity.SocialLike;


/**
 * 点赞信息视图对象 social_like
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialLike.class)
public class SocialLikeVo extends AppBaseVo {

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long likeId;

    /**
     * 点赞用户id
     */
    @ExcelProperty(value = "点赞用户id")
    private Long memberId;

    /**
     * 被点赞用户id
     */
    @ExcelProperty(value = "被点赞用户id")
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
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
