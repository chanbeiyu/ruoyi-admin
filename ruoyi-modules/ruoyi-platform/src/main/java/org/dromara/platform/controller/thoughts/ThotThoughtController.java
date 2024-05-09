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
import org.dromara.platform.constant.DataStatus;
import org.dromara.platform.domain.bo.ThotThoughtBo;
import org.dromara.platform.domain.vo.thoughts.ThotThoughtVo;
import org.dromara.platform.service.thoughts.ThotThoughtService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 思绪信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/thoughts/thought")
public class ThotThoughtController extends BaseController {

    private final ThotThoughtService thotThoughtService;

    /**
     * 查询思绪信息列表
     */
    @SaCheckPermission("thoughts:thought:list")
    @GetMapping("/list")
    public TableDataInfo<ThotThoughtVo> list(ThotThoughtBo bo, PageQuery pageQuery) {
        return thotThoughtService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出思绪信息列表
     */
    @SaCheckPermission("thoughts:thought:export")
    @Log(title = "思绪信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotThoughtBo bo, HttpServletResponse response) {
        List<ThotThoughtVo> list = thotThoughtService.queryList(bo);
        ExcelUtil.exportExcel(list, "思绪信息", ThotThoughtVo.class, response);
    }

    /**
     * 获取思绪信息详细信息
     *
     * @param thoughtId 主键
     */
    @SaCheckPermission("thoughts:thought:query")
    @GetMapping(value = "/content/{thoughtId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getContent(@NotNull(message = "主键不能为空") @PathVariable Long thoughtId) {
        return thotThoughtService.queryById(thoughtId).getContent();
    }

    /**
     * 获取思绪信息详细信息
     *
     * @param thoughtId 主键
     */
    @SaCheckPermission("thoughts:thought:query")
    @GetMapping("/{thoughtId}")
    public R<ThotThoughtVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long thoughtId) {
        return R.ok(thotThoughtService.queryById(thoughtId));
    }

    /**
     * 新增思绪信息
     */
    @SaCheckPermission("thoughts:thought:add")
    @Log(title = "思绪信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotThoughtBo bo) {
        return toAjax(thotThoughtService.insertByBo(bo));
    }

    /**
     * 修改思绪信息
     */
    @SaCheckPermission("thoughts:thought:edit")
    @Log(title = "思绪信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotThoughtBo bo) {
        return toAjax(thotThoughtService.updateByBo(bo));
    }

    /**
     * 发布思绪信息
     *
     * @param thoughtIds 主键串
     */
    @SaCheckPermission("thoughts:thought:publish")
    @Log(title = "话题信息", businessType = BusinessType.PUBLISH)
    @PatchMapping("/publish/{thoughtIds}")
    public R<Void> publish(@NotEmpty(message = "主键不能为空") @PathVariable Long[] thoughtIds) {
        return toAjax(thotThoughtService.updateStatus(List.of(thoughtIds), DataStatus.PUBLISH));
    }

    /**
     * 锁定思绪信息
     *
     * @param thoughtIds 主键串
     */
    @SaCheckPermission("thoughts:thought:lock")
    @Log(title = "思绪信息", businessType = BusinessType.LOCK)
    @PatchMapping("/lock/{thoughtIds}")
    public R<Void> lock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] thoughtIds) {
        return toAjax(thotThoughtService.updateStatus(List.of(thoughtIds), DataStatus.LOCK));
    }

    /**
     * 解锁思绪信息
     *
     * @param thoughtIds 主键串
     */
    @SaCheckPermission("thoughts:thought:unlock")
    @Log(title = "思绪信息", businessType = BusinessType.UNLOCK)
    @PatchMapping("/unlock/{thoughtIds}")
    public R<Void> unlock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] thoughtIds) {
        return toAjax(thotThoughtService.updateStatus(List.of(thoughtIds), DataStatus.UNLOCK));
    }

    /**
     * 删除思绪信息
     *
     * @param thoughtIds 主键串
     */
    @SaCheckPermission("thoughts:thought:remove")
    @Log(title = "思绪信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{thoughtIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] thoughtIds) {
        return toAjax(thotThoughtService.deleteByIds(List.of(thoughtIds)));
    }
}
