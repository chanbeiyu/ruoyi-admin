package org.dromara.platform.domain.social;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 信息通知类型对象 social_notice_type
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_notice_type")
public class SocialNoticeType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 通知类型id
     */
    @TableId(value = "notice_type_id")
    private Long noticeTypeId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 通知类型编码
     */
    private String noticeTypeCode;

    /**
     * 通知类型名称
     */
    private String noticeTypeName;

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
