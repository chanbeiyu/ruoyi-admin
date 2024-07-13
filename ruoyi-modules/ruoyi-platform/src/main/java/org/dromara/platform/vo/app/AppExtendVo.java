package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppExtend;
import org.dromara.platform.core.AppBaseVo;

import java.util.Date;


/**
 * 应用扩展信息视图对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppExtend.class)
public class AppExtendVo extends AppBaseVo {

    /**
     * 组件id
     */
    @ExcelProperty(value = "组件id")
    private Long extendId;

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
