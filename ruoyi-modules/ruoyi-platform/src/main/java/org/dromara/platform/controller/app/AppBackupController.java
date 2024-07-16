package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppBackupBo;
import org.dromara.basis.app.service.AppBackupService;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.vo.app.AppBackupVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 备份信息
 *
 * @author chanbeiyu
 * @date 2024-07-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/backup")
public class AppBackupController extends BaseController {

    private final AppBackupService appBackupService;

    /**
     * 查询备份信息列表
     */
    @SaCheckPermission("app:backup:list")
    @GetMapping("/list")
    public TableDataInfo<AppBackupVo> list(AppBackupBo bo, PageQuery pageQuery) {
        return appBackupService.selectTableList(bo, pageQuery, AppBackupVo.class);
    }

    /**
     * 导出备份信息列表
     */
    @SaCheckPermission("app:backup:export")
    @Log(title = "备份信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppBackupBo bo, HttpServletResponse response) {
        List<AppBackupVo> list = appBackupService.selectList(bo, AppBackupVo.class);
        ExcelUtil.exportExcel(list, "备份信息", AppBackupVo.class, response);
    }

    /**
     * 获取备份信息详细信息
     *
     * @param backupId 主键
     */
    @SaCheckPermission("app:backup:query")
    @GetMapping("/{backupId}")
    public R<AppBackupVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long backupId) {
        return R.ok(appBackupService.selectById(backupId, AppBackupVo.class));
    }

    /**
     * 新增备份信息
     */
    @SaCheckPermission("app:backup:add")
    @Log(title = "备份信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppBackupBo bo) {
        return toAjax(appBackupService.insert(bo));
    }

    /**
     * 修改备份信息
     */
    @SaCheckPermission("app:backup:edit")
    @Log(title = "备份信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppBackupBo bo) {
        return toAjax(appBackupService.update(bo));
    }

    /**
     * 删除备份信息
     *
     * @param backupIds 主键串
     */
    @SaCheckPermission("app:backup:remove")
    @Log(title = "备份信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{backupIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] backupIds) {
        return toAjax(appBackupService.deleteByIds(backupIds));
    }
}
