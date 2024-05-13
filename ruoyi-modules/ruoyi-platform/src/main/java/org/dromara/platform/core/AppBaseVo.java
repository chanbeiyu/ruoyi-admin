package org.dromara.platform.core;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AppBaseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * AppId
     */
    @ExcelProperty(value = "AppId")
    protected Long appId;

    /**
     * 编码
     */
    @ExcelProperty(value = "编码")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP_CODE)
    protected String appCode;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP_NAME)
    protected String appName;

}
