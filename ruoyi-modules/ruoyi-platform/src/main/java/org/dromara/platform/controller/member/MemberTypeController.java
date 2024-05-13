package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.member.bo.MemberTypeBo;
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
import org.dromara.platform.domain.member.vo.MemberTypeVo;
import org.dromara.platform.service.member.MemberTypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员类型信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/type")
public class MemberTypeController extends BaseController {

    private final MemberTypeService memberTypeService;

    /**
     * 查询会员类型信息列表
     */
    @SaCheckPermission("member:type:list")
    @GetMapping("/list")
    public TableDataInfo<MemberTypeVo> list(MemberTypeBo bo, PageQuery pageQuery) {
        return memberTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员类型信息列表
     */
    @SaCheckPermission("member:type:export")
    @Log(title = "会员类型信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberTypeBo bo, HttpServletResponse response) {
        List<MemberTypeVo> list = memberTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员类型信息", MemberTypeVo.class, response);
    }

    /**
     * 获取会员类型信息详细信息
     *
     * @param typeId 主键
     */
    @SaCheckPermission("member:type:query")
    @GetMapping("/{typeId}")
    public R<MemberTypeVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long typeId) {
        return R.ok(memberTypeService.queryById(typeId));
    }

    /**
     * 新增会员类型信息
     */
    @SaCheckPermission("member:type:add")
    @Log(title = "会员类型信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberTypeBo bo) {
        return toAjax(memberTypeService.insertByBo(bo));
    }

    /**
     * 修改会员类型信息
     */
    @SaCheckPermission("member:type:edit")
    @Log(title = "会员类型信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberTypeBo bo) {
        return toAjax(memberTypeService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("member:type:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody MemberTypeBo bo) {
        return toAjax(memberTypeService.updateStatus(bo.getTypeId(), bo.getStatus()));
    }

    /**
     * 删除会员类型信息
     *
     * @param typeIds 主键串
     */
    @SaCheckPermission("member:type:remove")
    @Log(title = "会员类型信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] typeIds) {
        return toAjax(memberTypeService.deleteByIds(List.of(typeIds)));
    }
}
