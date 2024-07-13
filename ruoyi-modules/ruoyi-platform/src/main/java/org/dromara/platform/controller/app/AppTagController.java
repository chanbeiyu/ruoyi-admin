package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppTagBo;
import org.dromara.basis.app.service.AppTagService;
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
import org.dromara.platform.vo.app.AppTagVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/tag")
public class AppTagController extends BaseController {

    private final AppTagService appTagService;

    /**
     * 查询标签信息列表
     */
    @SaCheckPermission("app:tag:list")
    @GetMapping("/list")
    public TableDataInfo<AppTagVo> list(AppTagBo bo, PageQuery pageQuery) {
        return appTagService.queryPageList(bo, pageQuery, AppTagVo.class);
    }

    /**
     * 导出标签信息列表
     */
    @SaCheckPermission("app:tag:export")
    @Log(title = "标签信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppTagBo bo, HttpServletResponse response) {
        List<AppTagVo> list = appTagService.queryList(bo, AppTagVo.class);
        ExcelUtil.exportExcel(list, "标签信息", AppTagVo.class, response);
    }

    /**
     * 获取标签信息详细信息
     *
     * @param tagId 主键
     */
    @SaCheckPermission("app:tag:query")
    @GetMapping("/{tagId}")
    public R<AppTagVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long tagId) {
        return R.ok(appTagService.queryById(tagId, AppTagVo.class));
    }

    /**
     * 新增标签信息
     */
    @SaCheckPermission("app:tag:add")
    @Log(title = "标签信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppTagBo bo) {
        return toAjax(appTagService.insertByBo(bo));
    }

    /**
     * 修改标签信息
     */
    @SaCheckPermission("app:tag:edit")
    @Log(title = "标签信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppTagBo bo) {
        return toAjax(appTagService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("app:subject:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody AppTagBo bo) {
        return toAjax(appTagService.updateStatus(bo.getTagId(), bo.getStatus()));
    }

    /**
     * 删除标签信息
     *
     * @param tagIds 主键串
     */
    @SaCheckPermission("app:tag:remove")
    @Log(title = "标签信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] tagIds) {
        return toAjax(appTagService.deleteByIds(List.of(tagIds)));
    }
}
