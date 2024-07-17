package org.dromara.basis.member.bo;

import org.dromara.basis.member.entity.MemberInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * 成员信息业务对象 member_info
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberInfo.class, reverseConvertGenerate = false)
public class MemberInfoBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long memberId;

    /**
     * 关联用户标识
     */
    @NotBlank(message = "关联用户标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String unionId;


    /**
     * 成员类型
     */
    @NotNull(message = "成员类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long typeId;
    private List<Long> typeIds;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nickName;

    /**
     * 头像
     */
    private String avatarImg;

    /**
     * 顶部背景图
     */
    private String bannerImg;

    /**
     * 个性签名
     */
    private String whatsup;

    /**
     * 状态
     */
    private String status;

}
