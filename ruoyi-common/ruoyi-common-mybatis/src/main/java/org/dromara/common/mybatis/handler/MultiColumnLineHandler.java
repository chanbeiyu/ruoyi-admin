//package org.dromara.common.mybatis.handler;
//
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.schema.Column;
//
//import java.util.List;
//
///**
// * 字段处理器（ 行级 ）
// *
// * @author hubin
// * @since 3.4.0
// */
//public interface MultiColumnLineHandler {
//
//    /**
//     * 获取值表达式，只支多个
//     * <p>
//     *
//     * @return 字段值表达式
//     */
//    Expression getColumnValues();
//
//    /**
//     * 获取字段名
//     *
//     * @return 字段名
//     */
//    String getColumnName();
//
//    /**
//     * 根据表名判断是否忽略拼接条件
//     * <p>
//     * 默认都要进行解析并拼接多租户条件
//     *
//     * @param tableName 表名
//     * @return 是否忽略, true:表示忽略，false:需要解析并拼接多租户条件
//     */
//    default boolean ignoreTable(String tableName) {
//        return false;
//    }
//
//    /**
//     * 忽略插入字段逻辑
//     *
//     * @param columns    插入字段
//     * @param columnName 字段名
//     */
//    default boolean ignoreInsert(List<Column> columns, String columnName) {
//        return columns.stream().map(Column::getColumnName).anyMatch(i -> i.equalsIgnoreCase(columnName));
//    }
//
//}
