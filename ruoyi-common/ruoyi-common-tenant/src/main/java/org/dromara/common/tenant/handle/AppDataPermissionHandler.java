package org.dromara.common.tenant.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import jodd.util.StringPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import org.dromara.common.tenant.helper.AppHelper;
import org.dromara.common.tenant.properties.TenantProperties;

import java.util.List;

/**
 * 自定义应用处理器
 *
 * @author chanbeiyu
 */
@Slf4j
@AllArgsConstructor
public class AppDataPermissionHandler implements MultiDataPermissionHandler {

    private final TenantProperties tenantProperties;

    @Override
    public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {

        if (!includeTable(table.getName())) {
            return null;
        }

        Long appId = AppHelper.getAppId();
        if (appId == null) {
            log.error("无法获取有效的AppId -> Null");
            return null;
        }
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(getAliasColumn(table));
        equalsTo.setRightExpression(new LongValue(appId));
        return equalsTo;
    }

    public boolean includeTable(String tableName) {
        List<String> includes = tenantProperties.getAppIncludes();
        return includes.contains(tableName);
    }

    /**
     * 租户字段别名设置
     *
     * @param table 表对象
     * @return 字段
     */
    protected Column getAliasColumn(Table table) {
        StringBuilder column = new StringBuilder();
        // todo 该起别名就要起别名,禁止修改此处逻辑
        if (table.getAlias() != null) {
            column.append(table.getAlias().getName()).append(StringPool.DOT);
        }
        column.append("app_id");
        return new Column(column.toString());
    }

}
