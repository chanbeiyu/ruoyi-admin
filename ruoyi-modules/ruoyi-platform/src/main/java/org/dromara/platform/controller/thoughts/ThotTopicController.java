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
import org.dromara.platform.domain.thoughts.bo.ThotTopicBo;
import org.dromara.platform.domain.thoughts.vo.ThotTopicVo;
import org.dromara.platform.service.thoughts.ThotTopicService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 话题信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/thoughts/topic")
public class ThotTopicController extends BaseController {

    private final ThotTopicService thotTopicService;

    /**
     * 查询话题信息列表
     */
    @SaCheckPermission("thoughts:topic:list")
    @GetMapping("/list")
    public TableDataInfo<ThotTopicVo> list(ThotTopicBo bo, PageQuery pageQuery) {
        return thotTopicService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出话题信息列表
     */
    @SaCheckPermission("thoughts:topic:export")
    @Log(title = "话题信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotTopicBo bo, HttpServletResponse response) {
        List<ThotTopicVo> list = thotTopicService.queryList(bo);
        ExcelUtil.exportExcel(list, "话题信息", ThotTopicVo.class, response);
    }

    /**
     * 获取话题信息详细信息
     *
     * @param topicId 主键
     */
    @SaCheckPermission("thoughts:topic:query")
    @GetMapping("/content/{topicId}")
    public String getContent(@NotNull(message = "主键不能为空") @PathVariable Long topicId) {
        return thotTopicService.queryById(topicId).getTopicContent();
    }

    /**
     * 获取话题信息详细信息
     *
     * @param topicId 主键
     */
    @SaCheckPermission("thoughts:topic:query")
    @GetMapping("/{topicId}")
    public R<ThotTopicVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long topicId) {
        return R.ok(thotTopicService.queryById(topicId));
    }

    /**
     * 新增话题信息
     */
    @SaCheckPermission("thoughts:topic:add")
    @Log(title = "话题信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotTopicBo bo) {
        return toAjax(thotTopicService.insertByBo(bo));
    }

    /**
     * 修改话题信息
     */
    @SaCheckPermission("thoughts:topic:edit")
    @Log(title = "话题信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotTopicBo bo) {
        return toAjax(thotTopicService.updateByBo(bo));
    }


    /**
     * 发布话题信息
     *
     * @param topicIds 主键串
     */
    @SaCheckPermission("thoughts:topic:publish")
    @Log(title = "话题信息", businessType = BusinessType.PUBLISH)
    @PatchMapping("/publish/{topicIds}")
    public R<Void> publish(@NotEmpty(message = "主键不能为空") @PathVariable Long[] topicIds) {
        return toAjax(thotTopicService.updateStatus(List.of(topicIds), DataStatus.PUBLISH));
    }

    /**
     * 锁定话题信息
     *
     * @param topicIds 主键串
     */
    @SaCheckPermission("thoughts:topic:lock")
    @Log(title = "话题信息", businessType = BusinessType.LOCK)
    @PatchMapping("/lock/{topicIds}")
    public R<Void> lock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] topicIds) {
        return toAjax(thotTopicService.updateStatus(List.of(topicIds), DataStatus.LOCK));
    }

    /**
     * 解锁话题信息
     *
     * @param topicIds 主键串
     */
    @SaCheckPermission("thoughts:topic:unlock")
    @Log(title = "话题信息", businessType = BusinessType.UNLOCK)
    @PatchMapping("/unlock/{topicIds}")
    public R<Void> unlock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] topicIds) {
        return toAjax(thotTopicService.updateStatus(List.of(topicIds), DataStatus.UNLOCK));
    }

    /**
     * 删除话题信息
     *
     * @param topicIds 主键串
     */
    @SaCheckPermission("thoughts:topic:remove")
    @Log(title = "话题信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{topicIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] topicIds) {
        return toAjax(thotTopicService.deleteByIds(List.of(topicIds)));
    }
}
