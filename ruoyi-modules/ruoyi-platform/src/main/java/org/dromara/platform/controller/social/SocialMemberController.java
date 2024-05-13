package org.dromara.platform.controller.social;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.social.bo.SocialMemberBo;
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
import org.dromara.platform.domain.social.vo.SocialMemberVo;
import org.dromara.platform.service.social.SocialMemberService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 成员信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/member")
public class SocialMemberController extends BaseController {

    private final SocialMemberService socialMemberService;

    /**
     * 查询成员信息列表
     */
    @SaCheckPermission("social:member:list")
    @GetMapping("/list")
    public TableDataInfo<SocialMemberVo> list(SocialMemberBo bo, PageQuery pageQuery) {
        return socialMemberService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出成员信息列表
     */
    @SaCheckPermission("social:member:export")
    @Log(title = "成员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialMemberBo bo, HttpServletResponse response) {
        List<SocialMemberVo> list = socialMemberService.queryList(bo);
        ExcelUtil.exportExcel(list, "成员信息", SocialMemberVo.class, response);
    }

    /**
     * 获取成员信息详细信息
     *
     * @param memberId 主键
     */
    @SaCheckPermission("social:member:query")
    @GetMapping("/{memberId}")
    public R<SocialMemberVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long memberId) {
        return R.ok(socialMemberService.queryById(memberId));
    }

    /**
     * 新增成员信息
     */
    @SaCheckPermission("social:member:add")
    @Log(title = "成员信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialMemberBo bo) {
        return toAjax(socialMemberService.insertByBo(bo));
    }

    /**
     * 修改成员信息
     */
    @SaCheckPermission("social:member:edit")
    @Log(title = "成员信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialMemberBo bo) {
        return toAjax(socialMemberService.updateByBo(bo));
    }

    /**
     * 删除成员信息
     *
     * @param memberIds 主键串
     */
    @SaCheckPermission("social:member:remove")
    @Log(title = "成员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{memberIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] memberIds) {
        return toAjax(socialMemberService.deleteByIds(List.of(memberIds)));
    }
}
