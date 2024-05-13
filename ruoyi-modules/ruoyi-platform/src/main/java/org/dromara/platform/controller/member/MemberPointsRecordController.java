package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.member.bo.MemberPointsRecordBo;
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
import org.dromara.platform.domain.member.vo.MemberPointsRecordVo;
import org.dromara.platform.service.member.MemberPointsRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员积分记录
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/points/record")
public class MemberPointsRecordController extends BaseController {

    private final MemberPointsRecordService memberPointsRecordService;

    /**
     * 查询会员积分记录列表
     */
    @SaCheckPermission("member:points:record:list")
    @GetMapping("/list")
    public TableDataInfo<MemberPointsRecordVo> list(MemberPointsRecordBo bo, PageQuery pageQuery) {
        return memberPointsRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员积分记录列表
     */
    @SaCheckPermission("member:points:record:export")
    @Log(title = "会员积分记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberPointsRecordBo bo, HttpServletResponse response) {
        List<MemberPointsRecordVo> list = memberPointsRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员积分记录", MemberPointsRecordVo.class, response);
    }

    /**
     * 获取会员积分记录详细信息
     *
     * @param recordId 主键
     */
    @SaCheckPermission("member:points:record:query")
    @GetMapping("/{recordId}")
    public R<MemberPointsRecordVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long recordId) {
        return R.ok(memberPointsRecordService.queryById(recordId));
    }

    /**
     * 新增会员积分记录
     */
    @SaCheckPermission("member:points:record:add")
    @Log(title = "会员积分记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberPointsRecordBo bo) {
        return toAjax(memberPointsRecordService.insertByBo(bo));
    }

    /**
     * 修改会员积分记录
     */
    @SaCheckPermission("member:points:record:edit")
    @Log(title = "会员积分记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberPointsRecordBo bo) {
        return toAjax(memberPointsRecordService.updateByBo(bo));
    }

    /**
     * 删除会员积分记录
     *
     * @param recordIds 主键串
     */
    @SaCheckPermission("member:points:record:remove")
    @Log(title = "会员积分记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] recordIds) {
        return toAjax(memberPointsRecordService.deleteByIds(List.of(recordIds)));
    }
}
