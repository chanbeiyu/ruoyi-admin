package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.tenant.helper.AppHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.domain.app.bo.AppInfoBo;
import org.dromara.platform.domain.app.vo.AppInfoVo;
import org.dromara.platform.service.app.AppInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/info")
public class AppInfoController extends BaseController {

    private final AppInfoService appInfoService;

    /**
     * 查询应用信息列表
     */
    @SaCheckPermission("app:info:list")
    @GetMapping("/list")
    public TableDataInfo<AppInfoVo> list(AppInfoBo bo, PageQuery pageQuery) {
        return appInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出应用信息列表
     */
    @SaCheckPermission("app:info:export")
    @Log(title = "应用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppInfoBo bo, HttpServletResponse response) {
        List<AppInfoVo> list = appInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "应用信息", AppInfoVo.class, response);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param appId 主键
     */
    @SaCheckPermission("app:info:query")
    @GetMapping("/{appId}")
    public R<AppInfoVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long appId) {
        return R.ok(appInfoService.queryById(appId));
    }

    /**
     * 新增应用信息
     */
    @SaCheckPermission("app:info:add")
    @Log(title = "应用信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppInfoBo bo) {
        return toAjax(appInfoService.insertByBo(bo));
    }

    /**
     * 修改应用信息
     */
    @SaCheckPermission("app:info:edit")
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppInfoBo bo) {
        return toAjax(appInfoService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("app:info:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody AppInfoBo bo) {
        return toAjax(appInfoService.updateStatus(bo.getAppId(), bo.getStatus()));
    }


    /**
     * 删除应用信息
     *
     * @param appIds 主键串
     */
    @SaCheckPermission("app:info:remove")
    @Log(title = "应用信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] appIds) {
        return toAjax(appInfoService.deleteByIds(List.of(appIds)));
    }


    /**
     * 动态切换应用
     *
     * @param appIds 应用ID
     */
    //@SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @GetMapping("/dynamic/{appIds}")
    public R<Void> dynamicTenant(@NotBlank(message = "AppId不能为空") @PathVariable String appIds) {
        AppHelper.setDynamic(appIds);
        return R.ok();
    }

    /**
     * 清除动态应用
     */
    //@SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @GetMapping("/dynamic/clear")
    public R<Void> dynamicClear() {
        AppHelper.clearDynamic();
        return R.ok();
    }


}
