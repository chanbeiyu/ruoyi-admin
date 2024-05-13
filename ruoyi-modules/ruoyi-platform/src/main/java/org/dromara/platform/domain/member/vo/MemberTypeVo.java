package org.dromara.platform.domain.member.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.member.MemberType;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 会员类型信息视图对象 member_type
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberType.class)
public class MemberTypeVo extends AppBaseVo {

    /**
     * 级别id
     */
    @ExcelProperty(value = "级别id")
    private Long typeId;

    /**
     * 会员类型编码
     */
    @ExcelProperty(value = "会员类型编码")
    private String typeCode;

    /**
     * 会员类型名称
     */
    @ExcelProperty(value = "会员类型名称")
    private String typeName;

    /**
     * 级别编码
     */
    @ExcelProperty(value = "级别编码")
    private String pointsCode;

    /**
     * 级别名称
     */
    @ExcelProperty(value = "级别名称")
    private String pointsName;

    /**
     * 最大积分
     */
    @ExcelProperty(value = "最大积分")
    private Long maxPoints;

    /**
     * 最大级别
     */
    @ExcelProperty(value = "最大级别")
    private Integer maxLevel;

    /**
     * 会员类型说明
     */
    @ExcelProperty(value = "会员类型说明")
    private String description;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

}
