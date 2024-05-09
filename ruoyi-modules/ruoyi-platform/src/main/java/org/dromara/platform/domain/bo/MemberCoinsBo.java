package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.MemberCoins;
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
 * 代币信息业务对象 member_coins
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberCoins.class, reverseConvertGenerate = false)
public class MemberCoinsBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long id;

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
     * 代币类型
     */
    @NotNull(message = "代币类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long coinsType;

    /**
     * 剩余点数/时常/天数
     */
    @NotNull(message = "剩余点数/时常/天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long lastCoins;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expiredDate;

    /**
     * 级别说明
     */
    @NotBlank(message = "级别说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 状态
     */
    private Integer status;

}
