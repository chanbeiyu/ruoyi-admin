package org.dromara.platform.controller.app;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.app.bo.AppSubjectBo;
import org.dromara.basis.app.service.AppSubjectService;
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
import org.dromara.platform.vo.app.AppSubjectVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内容主题
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/subject")
public class AppSubjectController extends BaseController {

    private final AppSubjectService appSubjectService;

    /**
     * 查询内容主题列表
     */
    @SaCheckPermission("app:subject:list")
    @GetMapping("/list")
    public TableDataInfo<AppSubjectVo> list(AppSubjectBo bo, PageQuery pageQuery) {
        return appSubjectService.selectTableList(bo, pageQuery, AppSubjectVo.class);
    }

    /**
     * 导出内容主题列表
     */
    @SaCheckPermission("app:subject:export")
    @Log(title = "内容主题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppSubjectBo bo, HttpServletResponse response) {
        List<AppSubjectVo> list = appSubjectService.selectList(bo, AppSubjectVo.class);
        ExcelUtil.exportExcel(list, "内容主题", AppSubjectVo.class, response);
    }

    /**
     * 获取内容主题详细信息
     *
     * @param subjectId 主键
     */
    @SaCheckPermission("app:subject:query")
    @GetMapping("/{subjectId}")
    public R<AppSubjectVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long subjectId) {
        return R.ok(appSubjectService.selectById(subjectId, AppSubjectVo.class));
    }

    /**
     * 新增内容主题
     */
    @SaCheckPermission("app:subject:add")
    @Log(title = "内容主题", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AppSubjectBo bo) {
        return toAjax(appSubjectService.insert(bo));
    }

    /**
     * 修改内容主题
     */
    @SaCheckPermission("app:subject:edit")
    @Log(title = "内容主题", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppSubjectBo bo) {
        return toAjax(appSubjectService.update(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("app:subject:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody AppSubjectBo bo) {
        return toAjax(appSubjectService.updateStatus(bo.getSubjectId(), bo.getStatus()));
    }

    /**
     * 删除内容主题
     *
     * @param subjectIds 主键串
     */
    @SaCheckPermission("app:subject:remove")
    @Log(title = "内容主题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{subjectIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] subjectIds) {
        return toAjax(appSubjectService.deleteByIds(List.of(subjectIds)));
    }
}
