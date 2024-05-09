package org.dromara.platform.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 内容主题对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_subject")
public class SocialSubject extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "subject_id")
    private Long subjectId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 主题编码
     */
    private String subjectCode;

    /**
     * 主题名称
     */
    private String subjectName;

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
