package org.dromara.platform.vo.social;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.platform.core.AppBaseVo;
import org.dromara.basis.social.entity.SocialNoticeType;


/**
 * 信息通知类型视图对象 social_notice_type
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialNoticeType.class)
public class SocialNoticeTypeVo extends AppBaseVo {

    /**
     * 通知类型id
     */
    @ExcelProperty(value = "通知类型id")
    private Long noticeTypeId;

    /**
     * 通知类型编码
     */
    @ExcelProperty(value = "通知类型编码")
    private String noticeTypeCode;

    /**
     * 通知类型名称
     */
    @ExcelProperty(value = "通知类型名称")
    private String noticeTypeName;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 主题描述
     */
    @ExcelProperty(value = "主题描述")
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
