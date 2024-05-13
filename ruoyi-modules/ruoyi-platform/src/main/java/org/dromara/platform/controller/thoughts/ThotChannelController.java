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
import org.dromara.platform.domain.thoughts.bo.ThotChannelBo;
import org.dromara.platform.domain.thoughts.vo.ThotChannelVo;
import org.dromara.platform.service.thoughts.ThotChannelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 频道信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/thoughts/channel")
public class ThotChannelController extends BaseController {

    private final ThotChannelService thotChannelService;

    /**
     * 查询频道信息列表
     */
    @SaCheckPermission("thoughts:channel:list")
    @GetMapping("/list")
    public TableDataInfo<ThotChannelVo> list(ThotChannelBo bo, PageQuery pageQuery) {
        return thotChannelService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出频道信息列表
     */
    @SaCheckPermission("thoughts:channel:export")
    @Log(title = "频道信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotChannelBo bo, HttpServletResponse response) {
        List<ThotChannelVo> list = thotChannelService.queryList(bo);
        ExcelUtil.exportExcel(list, "频道信息", ThotChannelVo.class, response);
    }

    /**
     * 获取频道信息详细信息
     *
     * @param channelId 主键
     */
    @SaCheckPermission("thoughts:channel:query")
    @GetMapping("/{channelId}")
    public R<ThotChannelVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long channelId) {
        return R.ok(thotChannelService.queryById(channelId));
    }

    /**
     * 新增频道信息
     */
    @SaCheckPermission("thoughts:channel:add")
    @Log(title = "频道信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotChannelBo bo) {
        return toAjax(thotChannelService.insertByBo(bo));
    }

    /**
     * 修改频道信息
     */
    @SaCheckPermission("thoughts:channel:edit")
    @Log(title = "频道信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotChannelBo bo) {
        return toAjax(thotChannelService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("thoughts:channel:edit")
    @Log(title = "频道状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody ThotChannelBo bo) {
        return toAjax(thotChannelService.updateStatus(bo.getChannelId(), bo.getStatus()));
    }

    /**
     * 删除频道信息
     *
     * @param channelIds 主键串
     */
    @SaCheckPermission("thoughts:channel:remove")
    @Log(title = "频道信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{channelIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] channelIds) {
        return toAjax(thotChannelService.deleteByIds(List.of(channelIds)));
    }
}
