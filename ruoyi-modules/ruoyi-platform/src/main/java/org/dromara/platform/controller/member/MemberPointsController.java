package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.bo.MemberPointsBo;
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
import org.dromara.platform.domain.vo.member.MemberPointsVo;
import org.dromara.platform.service.member.MemberPointsService;
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
@RequestMapping("/member/points")
public class MemberPointsController extends BaseController {

    private final MemberPointsService memberPointsService;

    /**
     * 查询会员积分列表
     */
    @SaCheckPermission("member:points:list")
    @GetMapping("/list")
    public TableDataInfo<MemberPointsVo> list(MemberPointsBo bo, PageQuery pageQuery) {
        return memberPointsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员积分列表
     */
    @SaCheckPermission("member:points:export")
    @Log(title = "会员积分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberPointsBo bo, HttpServletResponse response) {
        List<MemberPointsVo> list = memberPointsService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员积分", MemberPointsVo.class, response);
    }

    /**
     * 获取会员积分详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("member:points:query")
    @GetMapping("/{id}")
    public R<MemberPointsVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(memberPointsService.queryById(id));
    }

    /**
     * 新增会员积分
     */
    @SaCheckPermission("member:points:add")
    @Log(title = "会员积分", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberPointsBo bo) {
        return toAjax(memberPointsService.insertByBo(bo));
    }

    /**
     * 修改会员积分
     */
    @SaCheckPermission("member:points:edit")
    @Log(title = "会员积分", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberPointsBo bo) {
        return toAjax(memberPointsService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("member:level:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody MemberPointsBo bo) {
        return toAjax(memberPointsService.updateStatus(bo.getId(), bo.getStatus()));
    }

    /**
     * 删除会员积分
     *
     * @param ids 主键串
     */
    @SaCheckPermission("member:points:remove")
    @Log(title = "会员积分", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(memberPointsService.deleteByIds(List.of(ids)));
    }
}
