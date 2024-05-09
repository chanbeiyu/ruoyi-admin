package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.platform.domain.bo.MemberInfoBo;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.domain.vo.member.MemberInfoVo;
import org.dromara.platform.service.member.MemberInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 成员信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/info")
public class MemberInfoController extends BaseController {

    private final MemberInfoService memberInfoService;

    /**
     * 查询成员信息列表
     */
    @SaCheckPermission("member:info:list")
    @GetMapping("/list")
    public TableDataInfo<MemberInfoVo> list(MemberInfoBo bo, PageQuery pageQuery) {
        return memberInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出成员信息列表
     */
    @SaCheckPermission("member:info:export")
    @Log(title = "成员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberInfoBo bo, HttpServletResponse response) {
        List<MemberInfoVo> list = memberInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "成员信息", MemberInfoVo.class, response);
    }

    /**
     * 获取成员信息详细信息
     *
     * @param memberId 主键
     */
    @SaCheckPermission("member:info:query")
    @GetMapping("/{memberId}")
    public R<MemberInfoVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long memberId) {
        return R.ok(memberInfoService.queryById(memberId));
    }

    /**
     * 新增成员信息
     */
    @SaCheckPermission("member:info:add")
    @Log(title = "成员信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberInfoBo bo) {
        return toAjax(memberInfoService.insertByBo(bo));
    }

    /**
     * 修改成员信息
     */
    @SaCheckPermission("member:info:edit")
    @Log(title = "成员信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberInfoBo bo) {
        return toAjax(memberInfoService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("member:info:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody MemberInfoBo bo) {
        return toAjax(memberInfoService.updateStatus(bo.getMemberId(), bo.getStatus()));
    }

    /**
     * 删除成员信息
     *
     * @param memberIds 主键串
     */
    @SaCheckPermission("member:info:remove")
    @Log(title = "成员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{memberIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] memberIds) {
        return toAjax(memberInfoService.deleteByIds(List.of(memberIds)));
    }
}
