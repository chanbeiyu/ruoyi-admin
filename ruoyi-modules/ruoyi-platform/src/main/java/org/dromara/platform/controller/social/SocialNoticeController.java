package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.social.bo.SocialNoticeBo;
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
import org.dromara.platform.domain.social.vo.SocialNoticeVo;
import org.dromara.platform.service.social.SocialNoticeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 信息通知
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/notice")
public class SocialNoticeController extends BaseController {

    private final SocialNoticeService socialNoticeService;

    /**
     * 查询信息通知列表
     */
    @SaCheckPermission("social:notice:list")
    @GetMapping("/list")
    public TableDataInfo<SocialNoticeVo> list(SocialNoticeBo bo, PageQuery pageQuery) {
        return socialNoticeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出信息通知列表
     */
    @SaCheckPermission("social:notice:export")
    @Log(title = "信息通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialNoticeBo bo, HttpServletResponse response) {
        List<SocialNoticeVo> list = socialNoticeService.queryList(bo);
        ExcelUtil.exportExcel(list, "信息通知", SocialNoticeVo.class, response);
    }

    /**
     * 获取信息通知详细信息
     *
     * @param noticeId 主键
     */
    @SaCheckPermission("social:notice:query")
    @GetMapping("/{noticeId}")
    public R<SocialNoticeVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long noticeId) {
        return R.ok(socialNoticeService.queryById(noticeId));
    }

    /**
     * 新增信息通知
     */
    @SaCheckPermission("social:notice:add")
    @Log(title = "信息通知", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialNoticeBo bo) {
        return toAjax(socialNoticeService.insertByBo(bo));
    }

    /**
     * 修改信息通知
     */
    @SaCheckPermission("social:notice:edit")
    @Log(title = "信息通知", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialNoticeBo bo) {
        return toAjax(socialNoticeService.updateByBo(bo));
    }

    /**
     * 删除信息通知
     *
     * @param noticeIds 主键串
     */
    @SaCheckPermission("social:notice:remove")
    @Log(title = "信息通知", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] noticeIds) {
        return toAjax(socialNoticeService.deleteByIds(List.of(noticeIds)));
    }
}
