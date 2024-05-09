package org.dromara.platform.domain.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.AppVersion;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 应用版本信息视图对象 app_version
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AppVersion.class)
public class AppVersionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 版本id
     */
    @ExcelProperty(value = "版本id")
    private Long versionId;

    /**
     * AppId
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP)
    private String appName;

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


}
