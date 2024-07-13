package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppVersion;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.platform.core.AppBaseVo;

import java.util.Date;


/**
 * 应用版本信息视图对象 app_version
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppVersion.class)
public class AppVersionVo extends AppBaseVo {

    /**
     * 版本id
     */
    @ExcelProperty(value = "版本id")
    private Long versionId;

    /**
     * 版本号
     */
    @ExcelProperty(value = "版本号")
    private String version;

    /**
     * 内部版本号
     */
    @ExcelProperty(value = "内部版本号")
    private String buildVersion;

    /**
     * 强制升级
     */
    @ExcelProperty(value = "强制升级")
    private String forced;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 版本描述
     */
    @ExcelProperty(value = "版本描述")
    private String description;

}
