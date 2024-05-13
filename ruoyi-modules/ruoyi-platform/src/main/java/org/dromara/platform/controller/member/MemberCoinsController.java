package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.member.bo.MemberCoinsBo;
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
import org.dromara.platform.domain.member.vo.MemberCoinsVo;
import org.dromara.platform.service.member.MemberCoinsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代币信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/coins")
public class MemberCoinsController extends BaseController {

    private final MemberCoinsService memberCoinsService;

    /**
     * 查询代币信息列表
     */
    @SaCheckPermission("member:coins:list")
    @GetMapping("/list")
    public TableDataInfo<MemberCoinsVo> list(MemberCoinsBo bo, PageQuery pageQuery) {
        return memberCoinsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出代币信息列表
     */
    @SaCheckPermission("member:coins:export")
    @Log(title = "代币信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberCoinsBo bo, HttpServletResponse response) {
        List<MemberCoinsVo> list = memberCoinsService.queryList(bo);
        ExcelUtil.exportExcel(list, "代币信息", MemberCoinsVo.class, response);
    }

    /**
     * 获取代币信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("member:coins:query")
    @GetMapping("/{id}")
    public R<MemberCoinsVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(memberCoinsService.queryById(id));
    }

    /**
     * 新增代币信息
     */
    @SaCheckPermission("member:coins:add")
    @Log(title = "代币信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberCoinsBo bo) {
        return toAjax(memberCoinsService.insertByBo(bo));
    }

    /**
     * 修改代币信息
     */
    @SaCheckPermission("member:coins:edit")
    @Log(title = "代币信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberCoinsBo bo) {
        return toAjax(memberCoinsService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("member:level:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody MemberCoinsBo bo) {
        return toAjax(memberCoinsService.updateStatus(bo.getId(), bo.getStatus()));
    }

    /**
     * 删除代币信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("member:coins:remove")
    @Log(title = "代币信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(memberCoinsService.deleteByIds(List.of(ids)));
    }
}
