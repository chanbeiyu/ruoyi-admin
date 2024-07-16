package org.dromara.apptw.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwTobaccoBo;
import org.dromara.basis.apptw.service.ApptwTobaccoService;
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
import org.dromara.platform.vo.ApptwTobaccoVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 香烟信息
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/apptw/tobacco")
public class ApptwTobaccoController extends BaseController {

    private final ApptwTobaccoService apptwTobaccoService;

    /**
     * 查询香烟信息列表
     */
    @SaCheckPermission("apptw:tobacco:list")
    @GetMapping("/list")
    public TableDataInfo<ApptwTobaccoVo> list(ApptwTobaccoBo bo, PageQuery pageQuery) {
        return apptwTobaccoService.selectTableList(bo, pageQuery, ApptwTobaccoVo.class);
    }

    /**
     * 导出香烟信息列表
     */
    @SaCheckPermission("apptw:tobacco:export")
    @Log(title = "香烟信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApptwTobaccoBo bo, HttpServletResponse response) {
        List<ApptwTobaccoVo> list = apptwTobaccoService.selectList(bo, ApptwTobaccoVo.class);
        ExcelUtil.exportExcel(list, "香烟信息", ApptwTobaccoVo.class, response);
    }

    /**
     * 获取香烟信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("apptw:tobacco:query")
    @GetMapping("/{id}")
    public R<ApptwTobaccoVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(apptwTobaccoService.selectById(id, ApptwTobaccoVo.class));
    }

    /**
     * 新增香烟信息
     */
    @SaCheckPermission("apptw:tobacco:add")
    @Log(title = "香烟信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApptwTobaccoBo bo) {
        return toAjax(apptwTobaccoService.insert(bo));
    }

    /**
     * 修改香烟信息
     */
    @SaCheckPermission("apptw:tobacco:edit")
    @Log(title = "香烟信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApptwTobaccoBo bo) {
        return toAjax(apptwTobaccoService.update(bo));
    }

    /**
     * 删除香烟信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("apptw:tobacco:remove")
    @Log(title = "香烟信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(apptwTobaccoService.deleteByIds(List.of(ids)));
    }
}
