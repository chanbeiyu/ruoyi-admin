package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.bo.SocialNoticeTypeBo;
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
import org.dromara.platform.domain.vo.social.SocialNoticeTypeVo;
import org.dromara.platform.service.social.SocialNoticeTypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 信息通知类型
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/noticeType")
public class SocialNoticeTypeController extends BaseController {

    private final SocialNoticeTypeService socialNoticeTypeService;

    /**
     * 查询信息通知类型列表
     */
    @SaCheckPermission("social:noticeType:list")
    @GetMapping("/list")
    public TableDataInfo<SocialNoticeTypeVo> list(SocialNoticeTypeBo bo, PageQuery pageQuery) {
        return socialNoticeTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出信息通知类型列表
     */
    @SaCheckPermission("social:noticeType:export")
    @Log(title = "信息通知类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialNoticeTypeBo bo, HttpServletResponse response) {
        List<SocialNoticeTypeVo> list = socialNoticeTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "信息通知类型", SocialNoticeTypeVo.class, response);
    }

    /**
     * 获取信息通知类型详细信息
     *
     * @param noticeTypeId 主键
     */
    @SaCheckPermission("social:noticeType:query")
    @GetMapping("/{noticeTypeId}")
    public R<SocialNoticeTypeVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long noticeTypeId) {
        return R.ok(socialNoticeTypeService.queryById(noticeTypeId));
    }

    /**
     * 新增信息通知类型
     */
    @SaCheckPermission("social:noticeType:add")
    @Log(title = "信息通知类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialNoticeTypeBo bo) {
        return toAjax(socialNoticeTypeService.insertByBo(bo));
    }

    /**
     * 修改信息通知类型
     */
    @SaCheckPermission("social:noticeType:edit")
    @Log(title = "信息通知类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialNoticeTypeBo bo) {
        return toAjax(socialNoticeTypeService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("social:noticeType:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody SocialNoticeTypeBo bo) {
        return toAjax(socialNoticeTypeService.updateStatus(bo.getNoticeTypeId(), bo.getStatus()));
    }

    /**
     * 删除信息通知类型
     *
     * @param noticeTypeIds 主键串
     */
    @SaCheckPermission("social:noticeType:remove")
    @Log(title = "信息通知类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeTypeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] noticeTypeIds) {
        return toAjax(socialNoticeTypeService.deleteByIds(List.of(noticeTypeIds)));
    }
}
