package org.dromara.platform.domain.member.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.platform.domain.member.MemberInfo;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 成员信息视图对象 member_info
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberInfo.class)
public class MemberInfoVo extends AppBaseVo {

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
     * 成员类型
     */
    @ExcelProperty(value = "成员类型")
    private Long typeId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = PlatformTranslation.key, mapper = "typeId", other = PlatformTranslation.Other.MEMBER_TYPE)
    private String typeName;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickName;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatarImg;

    /**
     * 顶部背景图
     */
    @ExcelProperty(value = "顶部背景图")
    private String bannerImg;

    /**
     * 个性签名
     */
    @ExcelProperty(value = "个性签名")
    private String whatsup;

    /**
     * 状态:0正常1锁定2注销
     */
    @ExcelProperty(value = "状态")
    private String status;

}
