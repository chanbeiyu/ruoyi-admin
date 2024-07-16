package org.dromara.platform.vo.member;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.basis.member.entity.MemberPoints;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;


/**
 * 会员积分视图对象 member_points
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberPoints.class)
public class MemberPointsVo extends AppBaseVo {

    /**
     * 积分id
     */
    @ExcelProperty(value = "积分id")
    private Long id;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "memberId", other = TranslationConst.MEMBER_INFO_NAME)
    private String memberName;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "成员类型")
    private Long memberTypeId;

    /**
     * 会员类别名称
     */
    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "memberTypeId", other = TranslationConst.MEMBER_TYPE_NAME)
    private String memberTypeName;

    /**
     * 总积分
     */
    @ExcelProperty(value = "总积分")
    private Long totalPoints;

    /**
     * 最终会员等级
     */
    @ExcelProperty(value = "最终会员等级")
    private Long lastLevel;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Integer status;

    /**
     * 获取积分说明
     */
    @ExcelProperty(value = "获取积分说明")
    private String description;

}
