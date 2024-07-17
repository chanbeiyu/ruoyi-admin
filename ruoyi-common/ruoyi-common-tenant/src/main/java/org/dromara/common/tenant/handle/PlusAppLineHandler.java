package org.dromara.common.tenant.handle;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.tenant.helper.AppHelper;
import org.dromara.common.tenant.helper.TenantHelper;
import org.dromara.common.tenant.properties.TenantProperties;

import java.util.List;

/**
 * 自定义租户处理器
 *
 * @author Lion Li
 */
@Slf4j
@AllArgsConstructor
public class PlusAppLineHandler implements AppLineHandler {

    private final TenantProperties tenantProperties;

    @Override
    public Expression getAppId() {
        Long appId = AppHelper.getAppId();
        if (appId == null || appId <= 0) {
            log.error("无法获取有效的租户id -> Null");
            return new NullValue();
        }
        // 返回固定租户
        return new LongValue(appId);
    }

    @Override
    public boolean ignoreTable(String tableName) {
        Long appId = AppHelper.getAppId();
        // 判断是否有 AppId
        if (appId != null && appId > 0) {
            List<String> includes = tenantProperties.getAppIncludes();
            return !includes.contains(tableName);
        }
        return true;
    }

}
