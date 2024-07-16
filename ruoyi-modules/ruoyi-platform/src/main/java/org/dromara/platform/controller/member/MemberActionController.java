package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberActionBo;
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
import org.dromara.platform.vo.member.MemberActionVo;
import org.dromara.basis.member.service.MemberActionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员积分
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/action")
public class MemberActionController extends BaseController {

    private final MemberActionService memberActionService;

    /**
     * 查询会员积分列表
     */
    @SaCheckPermission("member:action:list")
    @GetMapping("/list")
    public TableDataInfo<MemberActionVo> list(MemberActionBo bo, PageQuery pageQuery) {
        return memberActionService.selectTableList(bo, pageQuery, MemberActionVo.class);
    }

    /**
     * 导出会员积分列表
     */
    @SaCheckPermission("member:action:export")
    @Log(title = "会员积分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberActionBo bo, HttpServletResponse response) {
        List<MemberActionVo> list = memberActionService.selectList(bo, MemberActionVo.class);
        ExcelUtil.exportExcel(list, "会员积分", MemberActionVo.class, response);
    }

    /**
     * 获取会员积分详细信息
     *
     * @param actionId 主键
     */
    @SaCheckPermission("member:action:query")
    @GetMapping("/{actionId}")
    public R<MemberActionVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long actionId) {
        return R.ok(memberActionService.selectById(actionId, MemberActionVo.class));
    }

    /**
     * 新增会员积分
     */
    @SaCheckPermission("member:action:add")
    @Log(title = "会员积分", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberActionBo bo) {
        return toAjax(memberActionService.insert(bo));
    }

    /**
     * 修改会员积分
     */
    @SaCheckPermission("member:action:edit")
    @Log(title = "会员积分", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberActionBo bo) {
        return toAjax(memberActionService.update(bo));
    }

    /**
     * 删除会员积分
     *
     * @param actionIds 主键串
     */
    @SaCheckPermission("member:action:remove")
    @Log(title = "会员积分", businessType = BusinessType.DELETE)
    @DeleteMapping("/{actionIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] actionIds) {
        return toAjax(memberActionService.deleteByIds(List.of(actionIds)));
    }
}
