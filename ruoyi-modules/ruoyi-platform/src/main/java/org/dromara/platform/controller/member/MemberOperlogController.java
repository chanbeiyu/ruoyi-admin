package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberOperLogBo;
import org.dromara.basis.member.service.MemberOperLogService;
import org.dromara.common.core.domain.R;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.vo.member.MemberOperLogVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/operlog")
public class MemberOperlogController extends BaseController {

    private final MemberOperLogService memberOperLogService;

    /**
     * 获取操作日志记录列表
     */
    @SaCheckPermission("member:operlog:list")
    @GetMapping("/list")
    public TableDataInfo<MemberOperLogVo> list(MemberOperLogBo operLog, PageQuery pageQuery) {
        return memberOperLogService.queryPageList(operLog, pageQuery, MemberOperLogVo.class);
    }

    /**
     * 导出操作日志记录列表
     */
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("member:operlog:export")
    @PostMapping("/export")
    public void export(MemberOperLogBo operLog, HttpServletResponse response) {
        List<MemberOperLogVo> list = memberOperLogService.queryList(operLog, MemberOperLogVo.class);
        ExcelUtil.exportExcel(list, "操作日志", MemberOperLogVo.class, response);
    }

    /**
     * 批量删除操作日志记录
     *
     * @param operIds 日志ids
     */
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @SaCheckPermission("member:operlog:remove")
    @DeleteMapping("/{operIds}")
    public R<Void> remove(@PathVariable Long[] operIds) {
        return toAjax(memberOperLogService.deleteByIds(operIds));
    }

    /**
     * 清理操作日志记录
     */
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @SaCheckPermission("member:operlog:remove")
    @DeleteMapping("/clean")
    public R<Void> clean() {
        memberOperLogService.cleanOperLog();
        return R.ok();
    }
}
