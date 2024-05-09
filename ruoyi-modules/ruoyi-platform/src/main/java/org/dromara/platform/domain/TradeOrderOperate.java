package org.dromara.basal.trade.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 订单操作历史记录对象 trade_order_operate
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("trade_order_operate")
public class TradeOrderOperate extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单操作id
     */
    @TableId(value = "operate_id")
    private Long operateId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 操作前订单状态
     */
    private Long beforStatus;

    /**
     * 操作后订单状态
     */
    private Long afterStatus;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
