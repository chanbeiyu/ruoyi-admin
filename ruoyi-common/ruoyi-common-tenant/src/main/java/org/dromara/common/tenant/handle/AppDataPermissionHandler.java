package org.dromara.common.tenant.handle;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import jodd.util.StringPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
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

        List<Long> appIds = AppHelper.getAppId();
        if (CollUtil.isEmpty(appIds)) {
            log.error("无法获取有效的AppId -> Null");
            return null;
        }
        ExpressionList expressionList = new ExpressionList();
        appIds.forEach(o -> {
            expressionList.addExpressions(new LongValue(o));
        });
        InExpression inExpression = new InExpression();
        inExpression.setNot(false);
        inExpression.setLeftExpression(getAliasColumn(table));
        inExpression.setRightItemsList(expressionList);
        return inExpression;
    }

    public boolean includeTable(String tableName) {
        List<Long> appIds = AppHelper.getAppId();
        if (CollUtil.isNotEmpty(appIds)) {
            List<String> includes = tenantProperties.getAppIncludes();
            return includes.contains(tableName);
        }
        return false;
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
