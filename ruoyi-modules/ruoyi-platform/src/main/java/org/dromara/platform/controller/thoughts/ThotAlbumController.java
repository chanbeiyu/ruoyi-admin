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
import org.dromara.platform.domain.thoughts.bo.ThotAlbumBo;
import org.dromara.platform.domain.thoughts.vo.ThotAlbumThoughtVo;
import org.dromara.platform.domain.thoughts.vo.ThotAlbumVo;
import org.dromara.platform.service.thoughts.ThotAlbumService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 思集信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/thoughts/album")
public class ThotAlbumController extends BaseController {

    private final ThotAlbumService thotAlbumService;

    /**
     * 查询思集信息列表
     */
    @SaCheckPermission("thoughts:album:list")
    @GetMapping("/list")
    public TableDataInfo<ThotAlbumVo> list(ThotAlbumBo bo, PageQuery pageQuery) {
        return thotAlbumService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出思集信息列表
     */
    @SaCheckPermission("thoughts:album:export")
    @Log(title = "思集信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotAlbumBo bo, HttpServletResponse response) {
        List<ThotAlbumVo> list = thotAlbumService.queryList(bo);
        ExcelUtil.exportExcel(list, "思集信息", ThotAlbumVo.class, response);
    }

    /**
     * 获取思集信息详细信息
     *
     * @param albumId 主键
     */
    @SaCheckPermission("thoughts:album:query")
    @GetMapping("/{albumId}")
    public R<ThotAlbumVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long albumId) {
        return R.ok(thotAlbumService.queryById(albumId));
    }

    /**
     * 获取思集信息详细信息
     *
     * @param thoughtIds 思绪主键
     */
    @SaCheckPermission("thoughts:album:edit")
    @GetMapping("/album-thought")
    public R<List<ThotAlbumThoughtVo>> getAlbumThought(@NotNull(message = "主键不能为空") @RequestParam List<Long> thoughtIds) {
        return R.ok(thotAlbumService.queryAlbumThoughtByIds(thoughtIds));
    }

    /**
     * 新增思集信息
     */
    @SaCheckPermission("thoughts:album:add")
    @Log(title = "思集信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotAlbumBo bo) {
        return toAjax(thotAlbumService.insertByBo(bo));
    }

    /**
     * 修改思集信息
     */
    @SaCheckPermission("thoughts:album:edit")
    @Log(title = "思集信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotAlbumBo bo) {
        return toAjax(thotAlbumService.updateByBo(bo));
    }

    /**
     * 发布思集信息
     *
     * @param albumIds 主键串
     */
    @SaCheckPermission("thoughts:topic:publish")
    @Log(title = "思集信息", businessType = BusinessType.PUBLISH)
    @PatchMapping("/publish/{albumIds}")
    public R<Void> publish(@NotEmpty(message = "主键不能为空") @PathVariable Long[] albumIds) {
        return toAjax(thotAlbumService.updateStatus(List.of(albumIds), DataStatus.PUBLISH));
    }

    /**
     * 锁定思集信息
     *
     * @param albumIds 主键串
     */
    @SaCheckPermission("thoughts:topic:lock")
    @Log(title = "思集信息", businessType = BusinessType.LOCK)
    @PatchMapping("/lock/{albumIds}")
    public R<Void> lock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] albumIds) {
        return toAjax(thotAlbumService.updateStatus(List.of(albumIds), DataStatus.LOCK));
    }

    /**
     * 解锁思集信息
     *
     * @param albumIds 主键串
     */
    @SaCheckPermission("thoughts:topic:unlock")
    @Log(title = "思集信息", businessType = BusinessType.UNLOCK)
    @PatchMapping("/unlock/{albumIds}")
    public R<Void> unlock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] albumIds) {
        return toAjax(thotAlbumService.updateStatus(List.of(albumIds), DataStatus.UNLOCK));
    }

    /**
     * 删除思集信息
     *
     * @param albumIds 主键串
     */
    @SaCheckPermission("thoughts:album:remove")
    @Log(title = "思集信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{albumIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] albumIds) {
        return toAjax(thotAlbumService.deleteByIds(List.of(albumIds)));
    }

    /**
     * 思集封面状态修改
     */
    @SaCheckPermission("thoughts:album:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/cover/status")
    public R<Void> changeStatus(@RequestBody ThotAlbumThoughtVo bo) {
        return toAjax(thotAlbumService.updateCoverStatus(bo.getId(), bo.getIsCover()));
    }

}
