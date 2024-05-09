package org.dromara.platform.domain.vo.member;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.MemberType;
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
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberType.class)
public class MemberTypeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @ExcelProperty(value = "级别id")
    private Long typeId;

    /**
     * 应用名称
     */
    @ExcelProperty(value = "应用名称")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP)
    private String appName;

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
