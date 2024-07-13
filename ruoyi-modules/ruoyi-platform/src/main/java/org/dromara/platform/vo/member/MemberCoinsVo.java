package org.dromara.platform.vo.member;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.basis.member.entity.MemberCoins;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;

import java.util.Date;


/**
 * 代币信息视图对象 member_coins
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberCoins.class)
public class MemberCoinsVo extends AppBaseVo {

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.key, mapper = "memberId", other = TranslationConst.MEMBER_INFO)
    private String memberName;

    /**
     * 代币信息0点数1时常2天数
     */
    @ExcelProperty(value = "代币信息0点数1时常2天数")
    private Long coinsType;

    /**
     * 剩余点数/时常/天数
     */
    @ExcelProperty(value = "剩余点数/时常/天数")
    private Long lastCoins;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expiredDate;

    /**
     * 级别说明
     */
    @ExcelProperty(value = "级别说明")
    private String description;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Integer status;

}
