package org.dromara.apptw.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.apptw.vo.ApptwLikeVo;
import org.dromara.basis.apptw.bo.ApptwLikeBo;
import org.dromara.basis.apptw.service.ApptwLikeService;
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
 * 喜好品类
 *
 * @author chanbeiyu
 * @date 2024-07-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/apptw/like")
public class ApptwLikeController extends BaseController {

    private final ApptwLikeService apptwLikeService;

    /**
     * 查询喜好品类列表
     */
    @SaCheckPermission("apptw:like:list")
    @GetMapping("/list")
    public TableDataInfo<ApptwLikeVo> list(ApptwLikeBo bo, PageQuery pageQuery) {
        return apptwLikeService.queryPageList(bo, pageQuery, ApptwLikeVo.class);
    }

    /**
     * 导出喜好品类列表
     */
    @SaCheckPermission("apptw:like:export")
    @Log(title = "喜好品类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ApptwLikeBo bo, HttpServletResponse response) {
        List<ApptwLikeVo> list = apptwLikeService.queryList(bo, ApptwLikeVo.class);
        ExcelUtil.exportExcel(list, "喜好品类", ApptwLikeVo.class, response);
    }

    /**
     * 获取喜好品类详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("apptw:like:query")
    @GetMapping("/{id}")
    public R<ApptwLikeVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long id) {
        return R.ok(apptwLikeService.queryById(id, ApptwLikeVo.class));
    }

    /**
     * 新增喜好品类
     */
    @SaCheckPermission("apptw:like:add")
    @Log(title = "喜好品类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ApptwLikeBo bo) {
        return toAjax(apptwLikeService.insertByBo(bo));
    }

    /**
     * 修改喜好品类
     */
    @SaCheckPermission("apptw:like:edit")
    @Log(title = "喜好品类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ApptwLikeBo bo) {
        return toAjax(apptwLikeService.updateByBo(bo));
    }

    /**
     * 删除喜好品类
     *
     * @param ids 主键串
     */
    @SaCheckPermission("apptw:like:remove")
    @Log(title = "喜好品类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(apptwLikeService.deleteByIds(List.of(ids)));
    }
}
