package org.dromara.platform.domain.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.platform.domain.AppExtend;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 应用扩展信息视图对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AppExtend.class)
public class AppExtendVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 组件id
     */
    @ExcelProperty(value = "组件id")
    private Long extendId;

    /**
     * AppId
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 数据key
     */
    @ExcelProperty(value = "数据key")
    private String key;

    /**
     * 数据标签
     */
    @ExcelProperty(value = "数据标签")
    private String label;

    /**
     * 扩展数据
     */
    private String value;

    /**
     * 扩展数据版本
     */
    @ExcelProperty(value = "扩展数据版本")
    private String version;

    /**
     * 数据说明
     */
    @ExcelProperty(value = "数据说明")
    private String description;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}
