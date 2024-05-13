package org.dromara.platform.domain.member.vo;

import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.member.MemberTypeRelated;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 会员类型关联信息视图对象 member_type_related
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberTypeRelated.class)
public class MemberTypeRelatedVo {

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 会员id
     */
    @ExcelProperty(value = "会员id")
    private Long memberId;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "会员类别")
    private Long memberTypeId;

}
