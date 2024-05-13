package org.dromara.platform.domain.app;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.tenant.core.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 意见反馈信息对象 app_advice
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_advice")
public class AppAdvice extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 留言id
     */
    @TableId(value = "advice_id")
    private Long adviceId;

    /**
     * AppId
     */
    private Long appId;

    /**
     * 成员id
     */
    private Long memberId;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;


}
