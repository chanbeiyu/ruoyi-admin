package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppExtendBo;
import org.dromara.basis.app.service.AppExtendService;
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
import org.dromara.platform.vo.app.AppExtendVo;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用扩展信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/extend")
public class AppExtendController extends BaseController {

    private final AppExtendService appExtendService;

    /**
     * 查询应用扩展信息列表
     */
    @SaCheckPermission("app:extend:list")
    @GetMapping("/list")
    public TableDataInfo<AppExtendVo> list(AppExtendBo bo, PageQuery pageQuery) {
        return appExtendService.queryPageList(bo, pageQuery, AppExtendVo.class, "value");
    }

    /**
     * 导出应用扩展信息列表
     */
    @SaCheckPermission("app:extend:export")
    @Log(title = "应用扩展信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppExtendBo bo, HttpServletResponse response) {
        List<AppExtendVo> list = appExtendService.queryList(bo, AppExtendVo.class, "value");
        ExcelUtil.exportExcel(list, "应用扩展信息", AppExtendVo.class, response);
    }

    /**
     * 获取应用扩展内容信息
     *
     * @param appId 主键
     */
    @SaCheckPermission("app:extend:query")
    @GetMapping(value = "/content/{appId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getContent(@NotNull(message = "主键不能为空") @PathVariable Long appId) {
        return appExtendService.queryById(appId, AppExtendVo.class).getValue();
    }

    /**
     * 获取应用扩展信息详细信息
     *
     * @param appId 主键
     */
    @SaCheckPermission("app:extend:query")
    @GetMapping("/{appId}")
    public R<AppExtendVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long appId) {
        return R.ok(appExtendService.queryById(appId, AppExtendVo.class));
    }

    /**
     * 新增应用扩展信息
     */
    @SaCheckPermission("app:extend:add")
    @Log(title = "应用扩展信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppExtendBo bo) {
        return toAjax(appExtendService.insertByBo(bo));
    }

    /**
     * 修改应用扩展信息
     */
    @SaCheckPermission("app:extend:edit")
    @Log(title = "应用扩展信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppExtendBo bo) {
        return toAjax(appExtendService.updateByBo(bo));
    }

    /**
     * 删除应用扩展信息
     *
     * @param appIds 主键串
     */
    @SaCheckPermission("app:extend:remove")
    @Log(title = "应用扩展信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] appIds) {
        return toAjax(appExtendService.deleteByIds(List.of(appIds)));
    }
}
