package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppVersionBo;
import org.dromara.basis.app.service.AppVersionService;
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
import org.dromara.platform.vo.app.AppVersionVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用版本信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/version")
public class AppVersionController extends BaseController {

    private final AppVersionService appVersionService;

    /**
     * 查询应用版本信息列表
     */
    @SaCheckPermission("app:version:list")
    @GetMapping("/list")
    public TableDataInfo<AppVersionVo> list(AppVersionBo bo, PageQuery pageQuery) {
        return appVersionService.queryPageList(bo, pageQuery, AppVersionVo.class);
    }

    /**
     * 导出应用版本信息列表
     */
    @SaCheckPermission("app:version:export")
    @Log(title = "应用版本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppVersionBo bo, HttpServletResponse response) {
        List<AppVersionVo> list = appVersionService.queryList(bo, AppVersionVo.class);
        ExcelUtil.exportExcel(list, "应用版本信息", AppVersionVo.class, response);
    }

    /**
     * 获取应用版本信息详细信息
     *
     * @param versionId 主键
     */
    @SaCheckPermission("app:version:query")
    @GetMapping("/{versionId}")
    public R<AppVersionVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long versionId) {
        return R.ok(appVersionService.queryById(versionId, AppVersionVo.class));
    }

    /**
     * 新增应用版本信息
     */
    @SaCheckPermission("app:version:add")
    @Log(title = "应用版本信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppVersionBo bo) {
        return toAjax(appVersionService.insertByBo(bo));
    }

    /**
     * 修改应用版本信息
     */
    @SaCheckPermission("app:version:edit")
    @Log(title = "应用版本信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppVersionBo bo) {
        return toAjax(appVersionService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("app:info:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody AppVersionBo bo) {
        return toAjax(appVersionService.updateStatus(bo.getVersionId(), bo.getStatus()));
    }


    /**
     * 删除应用版本信息
     *
     * @param versionIds 主键串
     */
    @SaCheckPermission("app:version:remove")
    @Log(title = "应用版本信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{versionIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] versionIds) {
        return toAjax(appVersionService.deleteByIds(List.of(versionIds)));
    }
}
