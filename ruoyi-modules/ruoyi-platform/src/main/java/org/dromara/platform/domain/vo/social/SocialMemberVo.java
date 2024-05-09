package org.dromara.platform.domain.vo.social;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.SocialMember;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 成员信息视图对象 social_member
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialMember.class)
public class SocialMemberVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long memberId;

    /**
     * 关联用户标识
     */
    @ExcelProperty(value = "关联用户标识")
    private String unionId;

    /**
     * 应用名称
     */
    private String appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 会员等级
     */
    @ExcelProperty(value = "会员等级")
    private Long vipLevel;

    /**
     * 积分
     */
    @ExcelProperty(value = "积分")
    private Long points;

    /**
     * 积分等级
     */
    @ExcelProperty(value = "积分等级")
    private Long pointsLevel;

    /**
     * 状态:0正常1锁定2注销
     */
    @ExcelProperty(value = "状态:0正常1锁定2注销", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
