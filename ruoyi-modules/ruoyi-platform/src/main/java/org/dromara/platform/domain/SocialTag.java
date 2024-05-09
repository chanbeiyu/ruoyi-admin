package org.dromara.platform.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 标签信息对象 social_tag
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_tag")
public class SocialTag extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "tag_id")
    private Long tagId;

    /**
     * 标签编码
     */
    private String tagCode;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签类型
     */
    private String tagType;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 所属主题
     */
    private Long subjectId;

    /**
     * 状态
     */
    private String status;

    /**
     * 主题描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
