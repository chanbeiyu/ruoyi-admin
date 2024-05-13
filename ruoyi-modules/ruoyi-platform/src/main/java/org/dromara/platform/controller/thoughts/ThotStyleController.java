package org.dromara.platform.controller.thoughts;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
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
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.domain.thoughts.bo.ThotStyleBo;
import org.dromara.platform.domain.thoughts.vo.ThotStyleVo;
import org.dromara.platform.service.thoughts.ThotStyleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 样式信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/thoughts/style")
public class ThotStyleController extends BaseController {

    private final ThotStyleService thotStyleService;

    /**
     * 查询样式信息列表
     */
    @SaCheckPermission("thoughts:style:list")
    @GetMapping("/list")
    public TableDataInfo<ThotStyleVo> list(ThotStyleBo bo, PageQuery pageQuery) {
        return thotStyleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出样式信息列表
     */
    @SaCheckPermission("thoughts:style:export")
    @Log(title = "样式信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotStyleBo bo, HttpServletResponse response) {
        List<ThotStyleVo> list = thotStyleService.queryList(bo);
        ExcelUtil.exportExcel(list, "样式信息", ThotStyleVo.class, response);
    }

    /**
     * 获取样式信息详细信息
     *
     * @param styleId 主键
     */
    @SaCheckPermission("thoughts:style:query")
    @GetMapping("/{styleId}")
    public R<ThotStyleVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long styleId) {
        return R.ok(thotStyleService.queryById(styleId));
    }

    /**
     * 新增样式信息
     */
    @SaCheckPermission("thoughts:style:add")
    @Log(title = "样式信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotStyleBo bo) {
        return toAjax(thotStyleService.insertByBo(bo));
    }

    /**
     * 修改样式信息
     */
    @SaCheckPermission("thoughts:style:edit")
    @Log(title = "样式信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotStyleBo bo) {
        return toAjax(thotStyleService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("thoughts:style:edit")
    @Log(title = "样式状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody ThotStyleBo bo) {
        return toAjax(thotStyleService.updateStatus(bo.getStyleId(), bo.getStatus()));
    }

    /**
     * 删除样式信息
     *
     * @param styleIds 主键串
     */
    @SaCheckPermission("thoughts:style:remove")
    @Log(title = "样式信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{styleIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] styleIds) {
        return toAjax(thotStyleService.deleteByIds(List.of(styleIds)));
    }
}
