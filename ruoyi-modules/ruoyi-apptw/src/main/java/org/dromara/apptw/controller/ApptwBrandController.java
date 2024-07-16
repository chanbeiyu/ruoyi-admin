package org.dromara.apptw.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.apptw.vo.ApptwBrandVo;
import org.dromara.basis.apptw.bo.ApptwBrandBo;
import org.dromara.basis.apptw.service.ApptwBrandService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 香烟品牌
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/apptw/brand")
public class ApptwBrandController extends BaseController {

    private final ApptwBrandService apptwBrandService;

    /**
     * 查询香烟品牌列表
     */
    @SaCheckPermission("apptw:brand:list")
    @GetMapping("/list")
    public TableDataInfo<ApptwBrandVo> list(ApptwBrandBo bo, PageQuery pageQuery) {
        return apptwBrandService.selectTableList(bo, pageQuery, ApptwBrandVo.class);
    }

    /**
     * 导出香烟品牌列表
     */
    @SaCheckPermission("apptw:brand:export")
    @Log(title = "香烟品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApptwBrandBo bo, HttpServletResponse response) {
        List<ApptwBrandVo> list = apptwBrandService.selectList(bo, ApptwBrandVo.class);
        ExcelUtil.exportExcel(list, "香烟品牌", ApptwBrandVo.class, response);
    }

    /**
     * 获取香烟品牌详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("apptw:brand:query")
    @GetMapping("/{id}")
    public R<ApptwBrandVo> getInfo(@NotNull(message = "主键不能为空")
                                   @PathVariable Long id) {
        return R.ok(apptwBrandService.selectById(id, ApptwBrandVo.class));
    }

    /**
     * 新增香烟品牌
     */
    @SaCheckPermission("apptw:brand:add")
    @Log(title = "香烟品牌", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApptwBrandBo bo) {
        return toAjax(apptwBrandService.insert(bo));
    }

    /**
     * 修改香烟品牌
     */
    @SaCheckPermission("apptw:brand:edit")
    @Log(title = "香烟品牌", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApptwBrandBo bo) {
        return toAjax(apptwBrandService.update(bo));
    }

    /**
     * 删除香烟品牌
     *
     * @param ids 主键串
     */
    @SaCheckPermission("apptw:brand:remove")
    @Log(title = "香烟品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(apptwBrandService.deleteByIds(List.of(ids)));
    }
}
