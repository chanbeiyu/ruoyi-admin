package org.dromara.web.controller;

import cn.hutool.core.collection.CollUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StreamUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.tenant.helper.AppHelper;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.platform.domain.app.bo.AppInfoBo;
import org.dromara.platform.domain.app.vo.AppInfoVo;
import org.dromara.platform.service.app.AppInfoService;
import org.dromara.system.domain.bo.SysTenantBo;
import org.dromara.system.domain.vo.SysTenantVo;
import org.dromara.system.service.ISysTenantService;
import org.dromara.web.domain.vo.AppListVo;
import org.dromara.web.domain.vo.LoginTenantVo;
import org.dromara.web.domain.vo.TenantListVo;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<SysTenantVo> tenantList = tenantService.queryList(new SysTenantBo());
        List<AppInfoVo> appVoList = appInfoService.queryList(new AppInfoBo());
        List<TenantListVo> voList = MapstructUtils.convert(tenantList, TenantListVo.class);
        List<AppListVo> appListVos = MapstructUtils.convert(appVoList, AppListVo.class);

        // 获取域名
        String host;
        String referer = request.getHeader("referer");
        if (StringUtils.isNotBlank(referer)) {
            // 这里从referer中取值是为了本地使用hosts添加虚拟域名，方便本地环境调试
            host = referer.split("//")[1].split("/")[0];
        } else {
            host = new URL(request.getRequestURL().toString()).getHost();
        }
        // 根据域名进行筛选
        List<TenantListVo> list = StreamUtils.filter(voList, vo ->
            StringUtils.equals(vo.getDomain(), host));

        // 返回对象
        LoginTenantVo vo = new LoginTenantVo();
        vo.setVoList(CollUtil.isNotEmpty(list) ? list : voList);
        vo.setAppList(appListVos);
        vo.setTenantEnabled(TenantHelper.isEnable());
        vo.setTenantId(TenantHelper.getTenantId());
        vo.setAppIds(AppHelper.getAppId());
        return R.ok(vo);
    }

}
