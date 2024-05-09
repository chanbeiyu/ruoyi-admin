package org.dromara.platform.domain.vo.social;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.SocialFavorite;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 收藏信息视图对象 social_favorite
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialFavorite.class)
public class SocialFavoriteVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long favoriteId;

    /**
     * 应用名称
     */
    private String appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 收藏用户id
     */
    @ExcelProperty(value = "收藏用户id")
    private Long memberId;

    /**
     * 被收藏用户id
     */
    @ExcelProperty(value = "被收藏用户id")
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
