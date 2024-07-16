package org.dromara.platform.vo.member;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.basis.member.entity.MemberCoinsRecord;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;

import java.util.Date;


/**
 * 代币记录信息视图对象 member_coins_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberCoinsRecord.class)
public class MemberCoinsRecordVo extends AppBaseVo {

    /**
     * 级别id
     */
    @ExcelProperty(value = "级别id")
    private Long recordId;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "memberId", other = TranslationConst.MEMBER_INFO_NAME)
    private String memberName;

    /**
     * 代币类型0点数1时常2天数
     */
    @ExcelProperty(value = "代币类型0点数1时常2天数")
    private Long coinsType;

    /**
     * 动作类型
     */
    @ExcelProperty(value = "动作类型")
    private String actionCode;

    /**
     * 先前代币
     */
    @ExcelProperty(value = "先前代币")
    private Long beforCoins;

    /**
     * 先前过期时间
     */
    @ExcelProperty(value = "先前过期时间")
    private Date beforExpiredDate;

    /**
     * 代币变化
     */
    @ExcelProperty(value = "代币变化")
    private Long changeCoins;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String orderNo;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Long status;

    /**
     * 说明
     */
    @ExcelProperty(value = "说明")
    private String description;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}
