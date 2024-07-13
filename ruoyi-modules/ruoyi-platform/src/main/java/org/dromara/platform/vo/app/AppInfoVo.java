package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.mapstruct.AppExtendMapstructMapper;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;
import org.dromara.platform.core.AppBaseVo;

import java.util.List;

/**
 * 应用信息视图对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMappers({@AutoMapper(target = AppInfo.class, uses = AppExtendMapstructMapper.class)})
public class AppInfoVo extends AppBaseVo {

    @ExcelProperty(value = "内部项目", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String isInternal;

    /**
     * AccessKeyId
     */
    @Sensitive(strategy = SensitiveStrategy.SECRET_KEY)
    @ExcelProperty(value = "AccessKeyId")
    private String accessKeyId;

    /**
     * SecretAccessKey
     */
    @Sensitive(strategy = SensitiveStrategy.SECRET_KEY)
    @ExcelProperty(value = "SecretAccessKey")
    private String secretAccessKey;

    /**
     * 签名加盐值
     */
    @ExcelProperty(value = "签名加盐值")
    private String salt;

    /**
     * 允许的域,支持模糊匹配
     */
    @ExcelProperty(value = "允许的域")
    private String domains;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 扩展信息
     */
    @ExcelProperty(value = "扩展信息")
    private List<Object> extend;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

}
