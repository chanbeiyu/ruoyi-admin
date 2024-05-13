package org.dromara.platform.domain.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 会员类型关联信息对象 member_type_related
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@TableName("member_type_related")
public class MemberTypeRelated {

    /**
     * 会员id
     */
    @TableId(type = IdType.INPUT)
    private Long memberId;

    /**
     * 会员类别
     */
    private Long memberTypeId;

}
