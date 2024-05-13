package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.social.bo.SocialFollowBo;
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
import org.dromara.platform.domain.social.vo.SocialFollowVo;
import org.dromara.platform.service.social.SocialFollowService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关注信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/follow")
public class SocialFollowController extends BaseController {

    private final SocialFollowService socialFollowService;

    /**
     * 查询关注信息列表
     */
    @SaCheckPermission("social:follow:list")
    @GetMapping("/list")
    public TableDataInfo<SocialFollowVo> list(SocialFollowBo bo, PageQuery pageQuery) {
        return socialFollowService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出关注信息列表
     */
    @SaCheckPermission("social:follow:export")
    @Log(title = "关注信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialFollowBo bo, HttpServletResponse response) {
        List<SocialFollowVo> list = socialFollowService.queryList(bo);
        ExcelUtil.exportExcel(list, "关注信息", SocialFollowVo.class, response);
    }

    /**
     * 获取关注信息详细信息
     *
     * @param followId 主键
     */
    @SaCheckPermission("social:follow:query")
    @GetMapping("/{followId}")
    public R<SocialFollowVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long followId) {
        return R.ok(socialFollowService.queryById(followId));
    }

    /**
     * 新增关注信息
     */
    @SaCheckPermission("social:follow:add")
    @Log(title = "关注信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialFollowBo bo) {
        return toAjax(socialFollowService.insertByBo(bo));
    }

    /**
     * 修改关注信息
     */
    @SaCheckPermission("social:follow:edit")
    @Log(title = "关注信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialFollowBo bo) {
        return toAjax(socialFollowService.updateByBo(bo));
    }

    /**
     * 删除关注信息
     *
     * @param followIds 主键串
     */
    @SaCheckPermission("social:follow:remove")
    @Log(title = "关注信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{followIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] followIds) {
        return toAjax(socialFollowService.deleteByIds(List.of(followIds)));
    }
}
