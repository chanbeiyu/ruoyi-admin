package org.dromara.basis.app.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.basis.app.entity.AppBackup;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseBo;

import java.util.List;

/**
 * 备份信息业务对象 app_backup
 *
 * @author chanbeiyu
 * @date 2024-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppBackup.class, reverseConvertGenerate = false)
public class AppBackupBo extends BaseBo {

    /**
     * 备份id
     */
    @NotNull(message = "备份id不能为空", groups = {EditGroup.class})
    private Long backupId;

    /**
     * 应用ID
     */
    @NotNull(message = "应用ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long appId;
    private List<Long> appIds;

    /**
     * 成员ID
     */
    @NotNull(message = "成员ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberId;

    /**
     * 备份标题
     */
    @NotBlank(message = "备份标题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String title;

    /**
     * 备份内容
     */
    @NotBlank(message = "备份内容不能为空", groups = {AddGroup.class, EditGroup.class})
    private String content;

    /**
     * 备注信息
     */
    private String description;

}
