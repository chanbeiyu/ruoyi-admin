package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppSubscribe;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.platform.core.AppBaseVo;

import java.io.Serial;


/**
 * App订阅视图对象 app_subscribe
 *
 * @author chanbeiyu
 * @date 2024-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppSubscribe.class)
public class AppSubscribeVo extends AppBaseVo {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 内购id
     */
    @ExcelProperty(value = "内购id")
    private Long subscribeId;

    /**
     * 订阅编码
     */
    @ExcelProperty(value = "订阅编码")
    private String code;

    /**
     * 订阅标题
     */
    @ExcelProperty(value = "订阅标题")
    private String title;

    /**
     * 原价
     */
    @ExcelProperty(value = "原价")
    private Long originalPrice;

    /**
     * 折扣价
     */
    @ExcelProperty(value = "折扣价")
    private Long discountPrice;

    /**
     * 订阅数量
     */
    @ExcelProperty(value = "订阅数量")
    private Long cycleCount;

    /**
     * 订阅周期:0永久1天2月3年
     */
    @ExcelProperty(value = "订阅周期:0永久1天2月3年", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "app_subscribe_cycle_type")
    private Long cycleType;

    /**
     * 支持自动续订
     */
    @ExcelProperty(value = "支持自动续订", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String supportRenew;

    /**
     * 启用状态
     */
    @ExcelProperty(value = "启用状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

}
