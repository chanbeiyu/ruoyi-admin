package org.dromara.web.domain.vo;

import lombok.Data;
import org.dromara.basis.constant.SearchVo;

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
    private Long appId;

    /**
     * 应用对象列表
     */
    private List<SearchVo> tenants;

}
