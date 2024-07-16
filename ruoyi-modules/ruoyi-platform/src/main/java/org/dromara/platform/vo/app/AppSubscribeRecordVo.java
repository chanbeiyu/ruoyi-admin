package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppSubscribeRecord;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;

import java.io.Serial;
import java.util.Date;


/**
 * App订阅记录视图对象 app_subscribe_record
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppSubscribeRecord.class)
public class AppSubscribeRecordVo extends AppBaseVo {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long recordId;

    /**
     * 订阅编码
     */
    @ExcelProperty(value = "订阅编码")
    private Long subscribeId;

    /**
     * 订阅名称
     */
    @ExcelProperty(value = "订阅名称")
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "subscribeId", other = TranslationConst.APP_SUBSCRIBE_NAME)
    private String subscribeName;

    /**
     * 成员ID
     */
    @ExcelProperty(value = "成员ID")
    private Long memberId;

    /**
     * 成员名称
     */
    @ExcelProperty(value = "成员名称")
    @Translation(type = TranslationConst.TYPE_KEY, mapper = "memberId", other = TranslationConst.MEMBER_INFO_NAME)
    private String memberName;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date starTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 花费费用
     */
    @ExcelProperty(value = "花费费用")
    private Long cost;

    /**
     * 开启自动续订
     */
    @ExcelProperty(value = "开启自动续订", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String renewEnable;

    /**
     * 自动续费定录ID
     */
    @ExcelProperty(value = "自动续费定录ID")
    private Long renewRecordId;

    /**
     * 退款时间
     */
    @ExcelProperty(value = "退款时间")
    private Date refundTime;

    /**
     * 状态0未开始1正常2过期3退款
     */
    @ExcelProperty(value = "状态0未开始1正常2过期3退款", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "app_subscribe_record_status")
    private Integer status;


}
