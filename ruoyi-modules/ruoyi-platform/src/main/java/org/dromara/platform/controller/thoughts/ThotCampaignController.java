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
import org.dromara.platform.domain.thoughts.bo.ThotCampaignBo;
import org.dromara.platform.domain.thoughts.vo.ThotCampaignVo;
import org.dromara.platform.service.thoughts.ThotCampaignService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/thoughts/campaign")
public class ThotCampaignController extends BaseController {

    private final ThotCampaignService thotCampaignService;

    /**
     * 查询活动信息列表
     */
    @SaCheckPermission("thoughts:campaign:list")
    @GetMapping("/list")
    public TableDataInfo<ThotCampaignVo> list(ThotCampaignBo bo, PageQuery pageQuery) {
        return thotCampaignService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动信息列表
     */
    @SaCheckPermission("thoughts:campaign:export")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotCampaignBo bo, HttpServletResponse response) {
        List<ThotCampaignVo> list = thotCampaignService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动信息", ThotCampaignVo.class, response);
    }

    /**
     * 获取活动信息详细信息
     *
     * @param campaignId 主键
     */
    @SaCheckPermission("thoughts:campaign:query")
    @GetMapping(value = "/content/{campaignId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getContent(@NotNull(message = "主键不能为空") @PathVariable Long campaignId) {
        return thotCampaignService.queryById(campaignId).getCampaignContent();
    }

    /**
     * 获取活动信息详细信息
     *
     * @param campaignId 主键
     */
    @SaCheckPermission("thoughts:campaign:query")
    @GetMapping("/{campaignId}")
    public R<ThotCampaignVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long campaignId) {
        return R.ok(thotCampaignService.queryById(campaignId));
    }

    /**
     * 新增活动信息
     */
    @SaCheckPermission("thoughts:campaign:add")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotCampaignBo bo) {
        return toAjax(thotCampaignService.insertByBo(bo));
    }

    /**
     * 修改活动信息
     */
    @SaCheckPermission("thoughts:campaign:edit")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotCampaignBo bo) {
        return toAjax(thotCampaignService.updateByBo(bo));
    }

    /**
     * 发布活动信息
     *
     * @param campaignIds 主键串
     */
    @SaCheckPermission("thoughts:topic:publish")
    @Log(title = "活动信息", businessType = BusinessType.PUBLISH)
    @PatchMapping("/publish/{campaignIds}")
    public R<Void> publish(@NotEmpty(message = "主键不能为空") @PathVariable Long[] campaignIds) {
        return toAjax(thotCampaignService.updateStatus(List.of(campaignIds), DataStatus.PUBLISH));
    }

    /**
     * 锁定活动信息
     *
     * @param campaignIds 主键串
     */
    @SaCheckPermission("thoughts:topic:lock")
    @Log(title = "活动信息", businessType = BusinessType.LOCK)
    @PatchMapping("/lock/{campaignIds}")
    public R<Void> lock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] campaignIds) {
        return toAjax(thotCampaignService.updateStatus(List.of(campaignIds), DataStatus.LOCK));
    }

    /**
     * 解锁活动信息
     *
     * @param campaignIds 主键串
     */
    @SaCheckPermission("thoughts:topic:unlock")
    @Log(title = "活动信息", businessType = BusinessType.UNLOCK)
    @PatchMapping("/unlock/{campaignIds}")
    public R<Void> unlock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] campaignIds) {
        return toAjax(thotCampaignService.updateStatus(List.of(campaignIds), DataStatus.UNLOCK));
    }

    /**
     * 删除活动信息
     *
     * @param campaignIds 主键串
     */
    @SaCheckPermission("thoughts:campaign:remove")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{campaignIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] campaignIds) {
        return toAjax(thotCampaignService.deleteByIds(List.of(campaignIds)));
    }
}
