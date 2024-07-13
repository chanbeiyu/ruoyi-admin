package org.dromara.platform.vo.member;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.basis.member.entity.MemberPointsRecord;
import org.dromara.platform.translation.PlatformTranslation;

import java.util.Date;


/**
 * 会员积分记录视图对象 member_points_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberPointsRecord.class)
public class MemberPointsRecordVo extends AppBaseVo {

    /**
     * 积分id
     */
    @ExcelProperty(value = "积分id")
    private Long recordId;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = PlatformTranslation.key, mapper = "memberId", other = PlatformTranslation.Other.MEMBER_INFO)
    private String memberName;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "成员类型")
    private Long memberTypeId;

    @ExcelProperty(value = "成员类型")
    @Translation(type = PlatformTranslation.key, mapper = "memberTypeId", other = PlatformTranslation.Other.MEMBER_TYPE)
    private String memberTypeName;

    /**
     * 操作类型
     */
    @ExcelProperty(value = "操作类型")
    private String actionCode;

    /**
     * 上次积分
     */
    @ExcelProperty(value = "上次积分")
    private Long beforPoints;

    /**
     * 获取积分
     */
    @ExcelProperty(value = "获取积分")
    private Long points;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expiredDate;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Long status;

    /**
     * 获取积分说明
     */
    @ExcelProperty(value = "获取积分说明")
    private String description;

}
