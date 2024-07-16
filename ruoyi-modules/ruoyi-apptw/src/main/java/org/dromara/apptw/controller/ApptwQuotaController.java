package org.dromara.apptw.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwQuotaBo;
import org.dromara.basis.apptw.service.ApptwQuotaService;
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
import org.dromara.platform.vo.ApptwQuotaVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 目标限额
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/apptw/quota")
public class ApptwQuotaController extends BaseController {

    private final ApptwQuotaService apptwQuotaService;

    /**
     * 查询目标限额列表
     */
    @SaCheckPermission("apptw:quota:list")
    @GetMapping("/list")
    public TableDataInfo<ApptwQuotaVo> list(ApptwQuotaBo bo, PageQuery pageQuery) {
        return apptwQuotaService.selectTableList(bo, pageQuery, ApptwQuotaVo.class);
    }

    /**
     * 导出目标限额列表
     */
    @SaCheckPermission("apptw:quota:export")
    @Log(title = "目标限额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApptwQuotaBo bo, HttpServletResponse response) {
        List<ApptwQuotaVo> list = apptwQuotaService.selectList(bo, ApptwQuotaVo.class);
        ExcelUtil.exportExcel(list, "目标限额", ApptwQuotaVo.class, response);
    }

    /**
     * 获取目标限额详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("apptw:quota:query")
    @GetMapping("/{id}")
    public R<ApptwQuotaVo> getInfo(@NotNull(message = "主键不能为空")
                                   @PathVariable Long id) {
        return R.ok(apptwQuotaService.selectById(id, ApptwQuotaVo.class));
    }

    /**
     * 新增目标限额
     */
    @SaCheckPermission("apptw:quota:add")
    @Log(title = "目标限额", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApptwQuotaBo bo) {
        return toAjax(apptwQuotaService.insert(bo));
    }

    /**
     * 修改目标限额
     */
    @SaCheckPermission("apptw:quota:edit")
    @Log(title = "目标限额", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApptwQuotaBo bo) {
        return toAjax(apptwQuotaService.update(bo));
    }

    /**
     * 删除目标限额
     *
     * @param ids 主键串
     */
    @SaCheckPermission("apptw:quota:remove")
    @Log(title = "目标限额", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(apptwQuotaService.deleteByIds(List.of(ids)));
    }
}
