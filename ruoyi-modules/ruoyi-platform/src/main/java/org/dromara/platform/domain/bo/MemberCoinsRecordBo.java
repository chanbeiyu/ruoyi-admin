package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.MemberCoinsRecord;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 代币记录信息业务对象 member_coins_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberCoinsRecord.class, reverseConvertGenerate = false)
public class MemberCoinsRecordBo extends BaseEntity {

    /**
     * 级别id
     */
    @NotNull(message = "级别id不能为空", groups = { EditGroup.class })
    private Long recordId;

    /**
     * 应用名称
     */
    @NotNull(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 成员id
     */
    @NotNull(message = "成员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 代币类型0点数1时常2天数
     */
    @NotNull(message = "代币类型0点数1时常2天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long coinsType;

    /**
     * 动作类型
     */
    @NotBlank(message = "动作类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String actionCode;

    /**
     * 先前代币
     */
    @NotNull(message = "先前代币不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long beforCoins;

    /**
     * 先前过期时间
     */
    @NotNull(message = "先前过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date beforExpiredDate;

    /**
     * 代币变化
     */
    @NotNull(message = "代币变化不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long changeCoins;

    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 状态
     */
    private Long status;

    /**
     * 说明
     */
    private String description;

}
