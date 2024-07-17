package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.basis.app.entity.AppInfo;
import org.dromara.basis.app.service.AppInfoService;
import org.dromara.basis.constant.SearchVo;
import org.dromara.common.core.constant.TenantConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.tenant.helper.AppHelper;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.system.domain.bo.SysTenantBo;
import org.dromara.system.domain.vo.SysTenantVo;
import org.dromara.system.service.ISysTenantService;
import org.dromara.web.domain.vo.LoginTenantVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;

/**
 * 认证
 *
 * @author chanbeiyu
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/navbar")
public class NavbarController {

    private final AppInfoService appInfoService;
    private final ISysTenantService tenantService;

    /**
     * Navbar 下拉框租户和 APP 列表
     */
    @GetMapping("/tenant/list")
    public R<LoginTenantVo> tenantList(HttpServletRequest request) throws Exception {
        TenantHelper.enableIgnore();
        List<AppInfo> appList = appInfoService.selectList();
        TenantHelper.disableIgnore();

        // 获取域名
        String host;
        String referer = request.getHeader("referer");
        if (StringUtils.isNotBlank(referer)) {
            // 这里从referer中取值是为了本地使用hosts添加虚拟域名，方便本地环境调试
            host = referer.split("//")[1].split("/")[0];
        } else {
            host = new URL(request.getRequestURL().toString()).getHost();
        }
        List<SysTenantVo> tenantList = tenantService.queryList(new SysTenantBo());
        tenantList = tenantList.stream()
            .filter(vo -> StringUtils.equals(vo.getDomain(), host) || StringUtils.isBlank(vo.getDomain()))
            .toList();

        List<SearchVo> tenants = tenantList.stream().map(o -> {
            List<SearchVo> children = appList.stream()
                .filter(app -> app.getTenantId().equals(o.getTenantId()))
                .map(app -> SearchVo.builder().label(app.getAppName()).value(app.getAppId() + "").code(app.getAppCode()).parentValue(o.getTenantId()).build())
                .toList();
            return SearchVo.builder().label(o.getCompanyName()).value(o.getTenantId()).code(o.getTenantId()).children(children).build();
        }).toList();

        // 返回对象
        LoginTenantVo vo = new LoginTenantVo();
        vo.setTenants(tenants);
        vo.setTenantEnabled(TenantHelper.isEnable());
        vo.setTenantId(TenantHelper.getTenantId());
        vo.setAppId(AppHelper.getAppId());
        return R.ok(vo);
    }

    /**
     * 动态切换租户
     *
     * @param tenantId 租户ID
     */
    @SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @GetMapping("/tenant/dynamic/{tenantId}/{appId}")
    public R<Void> dynamicTenant(@NotBlank(message = "租户ID不能为空") @PathVariable String tenantId,
                                 @NotNull(message = "应用ID不能为空") @PathVariable Long appId) {
        TenantHelper.setDynamic(tenantId);
        AppHelper.setDynamic(appId);
        return R.ok();
    }

    /**
     * 清除动态租户
     */
    @SaCheckRole(TenantConstants.SUPER_ADMIN_ROLE_KEY)
    @GetMapping("/tenant/dynamic/clear")
    public R<Void> dynamicClear() {
        TenantHelper.clearDynamic();
        AppHelper.clearDynamic();
        return R.ok();
    }

}
