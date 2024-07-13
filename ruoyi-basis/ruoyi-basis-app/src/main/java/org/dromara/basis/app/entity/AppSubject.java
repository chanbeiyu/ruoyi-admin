package org.dromara.basis.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;

/**
 * 内容主题对象 app_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_subject")
public class AppSubject extends TenantEntity {

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
