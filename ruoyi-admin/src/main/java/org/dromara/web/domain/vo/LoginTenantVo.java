package org.dromara.web.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 登录租户对象
 *
 * @author Michelle.Chung
 */
@Data
public class LoginTenantVo {

    /**
     * 租户开关
     */
    private Boolean tenantEnabled;

    /**
     * 当前值
     */
    private String tenantId;
    private List<Long> appIds;

    /**
     * 租户对象列表
     */
    private List<TenantListVo> voList;

    /**
     * 应用对象列表
     */
    private List<AppListVo> appList;

}
