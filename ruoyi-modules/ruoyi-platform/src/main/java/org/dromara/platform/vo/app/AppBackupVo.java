package org.dromara.platform.vo.app;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppBackup;
import org.dromara.basis.constant.TranslationConst;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.core.AppBaseVo;

import java.io.Serial;
import java.util.Date;


/**
 * 备份信息视图对象 app_backup
 *
 * @author chanbeiyu
 * @date 2024-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = AppBackup.class)
public class AppBackupVo extends AppBaseVo {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 备份id
     */
    @ExcelProperty(value = "备份id")
    private Long backupId;

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
     * 备份标题
     */
    @ExcelProperty(value = "备份标题")
    private String title;

    /**
     * 备份内容
     */
    @ExcelProperty(value = "备份内容")
    private String content;

    /**
     * 备份时间
     */
    @ExcelProperty(value = "备份时间")
    private Date time;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注信息")
    private String description;

}
