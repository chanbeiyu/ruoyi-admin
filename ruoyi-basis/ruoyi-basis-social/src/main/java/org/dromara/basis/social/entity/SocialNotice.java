package org.dromara.basis.social.entity;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 信息通知对象 social_notice
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_notice")
public class SocialNotice extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "notice_id")
    private Long noticeId;

    /**
     * 应用名称
     */
    private Long appId;

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 触发通知用户id
     */
    private Long triggerMemberId;

    /**
     * 触发通知内容id
     */
    private String triggerId;

    /**
     * 通知内容
     */
    private String triggerContent;

    /**
     * 通知类型
     */
    private String typeCode;

    /**
     * 通知状态（0发送失败1已发送2已阅读3删除）
     */
    private Integer status;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 阅读时间
     */
    private Date readTime;

    /**
     * 备注
     */
    private String remark;


}
