package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.bo.MemberTypeRelatedBo;
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
import org.dromara.platform.domain.vo.member.MemberTypeRelatedVo;
import org.dromara.platform.service.member.MemberTypeRelatedService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员类型关联信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/typeRelated")
public class MemberTypeRelatedController extends BaseController {

    private final MemberTypeRelatedService memberTypeRelatedService;

    /**
     * 查询会员类型关联信息列表
     */
    @SaCheckPermission("member:typeRelated:list")
    @GetMapping("/list")
    public TableDataInfo<MemberTypeRelatedVo> list(MemberTypeRelatedBo bo, PageQuery pageQuery) {
        return memberTypeRelatedService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员类型关联信息列表
     */
    @SaCheckPermission("member:typeRelated:export")
    @Log(title = "会员类型关联信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberTypeRelatedBo bo, HttpServletResponse response) {
        List<MemberTypeRelatedVo> list = memberTypeRelatedService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员类型关联信息", MemberTypeRelatedVo.class, response);
    }

    /**
     * 获取会员类型关联信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("member:typeRelated:query")
    @GetMapping("/{id}")
    public R<MemberTypeRelatedVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(memberTypeRelatedService.queryById(id));
    }

    /**
     * 新增会员类型关联信息
     */
    @SaCheckPermission("member:typeRelated:add")
    @Log(title = "会员类型关联信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberTypeRelatedBo bo) {
        return toAjax(memberTypeRelatedService.insertByBo(bo));
    }

    /**
     * 修改会员类型关联信息
     */
    @SaCheckPermission("member:typeRelated:edit")
    @Log(title = "会员类型关联信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberTypeRelatedBo bo) {
        return toAjax(memberTypeRelatedService.updateByBo(bo));
    }

    /**
     * 删除会员类型关联信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("member:typeRelated:remove")
    @Log(title = "会员类型关联信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(memberTypeRelatedService.deleteByIds(List.of(ids)));
    }
}
