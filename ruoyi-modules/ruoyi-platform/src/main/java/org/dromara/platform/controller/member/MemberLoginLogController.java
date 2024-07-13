package org.dromara.platform.controller.member;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.basis.member.bo.MemberLoginLogBo;
import org.dromara.basis.member.service.MemberLoginLogService;
import org.dromara.common.core.constant.GlobalConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.vo.member.MemberLoginLogVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统访问记录
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/loginlog")
public class MemberLoginLogController extends BaseController {

    private final MemberLoginLogService memberLoginLogService;

    /**
     * 获取系统访问记录列表
     */
    @SaCheckPermission("member:loginLog:list")
    @GetMapping("/list")
    public TableDataInfo<MemberLoginLogVo> list(MemberLoginLogBo logininfor, PageQuery pageQuery) {
        return memberLoginLogService.queryPageList(logininfor, pageQuery, MemberLoginLogVo.class);
    }

    /**
     * 导出系统访问记录列表
     */
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("member:loginLog:export")
    @PostMapping("/export")
    public void export(MemberLoginLogBo logininfor, HttpServletResponse response) {
        List<MemberLoginLogVo> list = memberLoginLogService.queryList(logininfor, MemberLoginLogVo.class);
        ExcelUtil.exportExcel(list, "登录日志", MemberLoginLogVo.class, response);
    }

    /**
     * 批量删除登录日志
     *
     * @param infoIds 日志ids
     */
    @SaCheckPermission("member:loginLog:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public R<Void> remove(@PathVariable Long[] infoIds) {
        return toAjax(memberLoginLogService.deleteByIds(infoIds));
    }

    /**
     * 清理系统访问记录
     */
    @SaCheckPermission("member:loginLog:remove")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public R<Void> clean() {
        memberLoginLogService.cleanLogininfor();
        return R.ok();
    }

    @SaCheckPermission("member:loginLog:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public R<Void> unlock(@PathVariable("userName") String userName) {
        String loginName = GlobalConstants.PWD_ERR_CNT_KEY + userName;
        if (RedisUtils.hasKey(loginName)) {
            RedisUtils.deleteObject(loginName);
        }
        return R.ok();
    }

}
