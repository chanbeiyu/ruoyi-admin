package org.dromara.apptw.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.apptw.bo.ApptwSmokeBo;
import org.dromara.basis.apptw.service.ApptwSmokeService;
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
import org.dromara.platform.vo.ApptwSmokeVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 抽烟记录
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/apptw/smoke")
public class ApptwSmokeController extends BaseController {

    private final ApptwSmokeService apptwSmokeService;

    /**
     * 查询抽烟记录列表
     */
    @SaCheckPermission("apptw:smoke:list")
    @GetMapping("/list")
    public TableDataInfo<ApptwSmokeVo> list(ApptwSmokeBo bo, PageQuery pageQuery) {
        return apptwSmokeService.queryPageList(bo, pageQuery, ApptwSmokeVo.class);
    }

    /**
     * 导出抽烟记录列表
     */
    @SaCheckPermission("apptw:smoke:export")
    @Log(title = "抽烟记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApptwSmokeBo bo, HttpServletResponse response) {
        List<ApptwSmokeVo> list = apptwSmokeService.queryList(bo, ApptwSmokeVo.class);
        ExcelUtil.exportExcel(list, "抽烟记录", ApptwSmokeVo.class, response);
    }

    /**
     * 获取抽烟记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("apptw:smoke:query")
    @GetMapping("/{id}")
    public R<ApptwSmokeVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(apptwSmokeService.queryById(id, ApptwSmokeVo.class));
    }

    /**
     * 新增抽烟记录
     */
    @SaCheckPermission("apptw:smoke:add")
    @Log(title = "抽烟记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApptwSmokeBo bo) {
        return toAjax(apptwSmokeService.insertByBo(bo));
    }

    /**
     * 修改抽烟记录
     */
    @SaCheckPermission("apptw:smoke:edit")
    @Log(title = "抽烟记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApptwSmokeBo bo) {
        return toAjax(apptwSmokeService.updateByBo(bo));
    }

    /**
     * 删除抽烟记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("apptw:smoke:remove")
    @Log(title = "抽烟记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(apptwSmokeService.deleteByIds(List.of(ids)));
    }
}
