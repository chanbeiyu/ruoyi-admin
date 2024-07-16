package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.social.bo.SocialTagBo;
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
import org.dromara.platform.vo.social.SocialTagVo;
import org.dromara.basis.social.service.SocialTagService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/tag")
public class SocialTagController extends BaseController {

    private final SocialTagService socialTagService;

    /**
     * 查询标签信息列表
     */
    @SaCheckPermission("social:tag:list")
    @GetMapping("/list")
    public TableDataInfo<SocialTagVo> list(SocialTagBo bo, PageQuery pageQuery) {
        return socialTagService.selectTableList(bo, pageQuery, SocialTagVo.class);
    }

    /**
     * 导出标签信息列表
     */
    @SaCheckPermission("social:tag:export")
    @Log(title = "标签信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialTagBo bo, HttpServletResponse response) {
        List<SocialTagVo> list = socialTagService.selectList(bo, SocialTagVo.class);
        ExcelUtil.exportExcel(list, "标签信息", SocialTagVo.class, response);
    }

    /**
     * 获取标签信息详细信息
     *
     * @param tagId 主键
     */
    @SaCheckPermission("social:tag:query")
    @GetMapping("/{tagId}")
    public R<SocialTagVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long tagId) {
        return R.ok(socialTagService.selectById(tagId, SocialTagVo.class));
    }

    /**
     * 新增标签信息
     */
    @SaCheckPermission("social:tag:add")
    @Log(title = "标签信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialTagBo bo) {
        return toAjax(socialTagService.insert(bo));
    }

    /**
     * 修改标签信息
     */
    @SaCheckPermission("social:tag:edit")
    @Log(title = "标签信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialTagBo bo) {
        return toAjax(socialTagService.update(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("social:subject:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody SocialTagBo bo) {
        return toAjax(socialTagService.updateStatus(bo.getTagId(), bo.getStatus()));
    }

    /**
     * 删除标签信息
     *
     * @param tagIds 主键串
     */
    @SaCheckPermission("social:tag:remove")
    @Log(title = "标签信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] tagIds) {
        return toAjax(socialTagService.deleteByIds(List.of(tagIds)));
    }
}
