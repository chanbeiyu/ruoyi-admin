package org.dromara.common.mybatis.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.result.ModifyResult;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface IBaseService<T, Q> {
//public interface IBaseService<T, V, Q> {

    IBaseMapper<T> mapper();

    LambdaQueryWrapper<T> buildQueryWrapper(Q q);

    default Class<T> modelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseService.class, 0);
    }

    default Class<Q> queryClass() {
        return (Class<Q>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseService.class, 1);
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    default T selectById(Serializable id) {
        return mapper().selectById(id);
    }

    default List<T> selectByIds(Serializable... ids) {
        return mapper().selectBatchIds(Arrays.asList(ids));
    }

    default <V> V selectById(Serializable id, Class<V> classz) {
        return mapper().selectById(id, classz);
    }

    default <V> List<V> selectByIds(Serializable[] ids, Class<V> classz) {
        return mapper().selectBatchIds(Arrays.asList(ids), classz);
    }

    default <V> List<V> selectByIds(Collection<Serializable> ids, Class<V> classz) {
        return mapper().selectBatchIds(ids, classz);
    }

    default List<T> selectByMap(Map<String, Object> columnMap) {
        QueryWrapper<T> qw = Wrappers.query();
        return mapper().selectList(qw.allEq(columnMap));
    }

    default <V> List<V> selectByMap(Map<String, Object> columnMap, Class<V> classz) {
        QueryWrapper<T> qw = Wrappers.query();
        return mapper().selectList(qw.allEq(columnMap), classz);
    }

    default List<T> selectList() {
        return mapper().selectList();
    }

    default <V> List<V> selectList(Class<V> classz) {
        return mapper().selectList(classz);
    }

    default <V> List<V> selectList(Function<T, V> mapper) {
        return mapper().selectList(mapper);
    }

    default List<T> selectList(Q query) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        return mapper().selectList(lqw);
    }

    default <V> List<V> selectList(Q query, Class<V> classz) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        return mapper().selectList(lqw, classz);
    }

    default <V> List<V> selectList(Q query, Function<T, V> mapper) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        return mapper().selectList(lqw, mapper);
    }

    default <V> List<V> selectList(Q query, Class<V> classz, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectList(lqw, classz);
    }

    default <V> List<V> selectList(Q query, Function<T, V> mapper, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectList(lqw, mapper);
    }

    default <V> List<V> selectList(Q query, Class<V> classz, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectList(lqw, classz);
    }

     default <V> List<V> selectList(Q query, Function<T, V> mapper, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectList(lqw, mapper);
    }

    default <V> List<V> selectList(Q query, Class<V> classz, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        return mapper().selectList(lqw, classz);
    }

    default <V> List<V> selectList(Q query, Function<T, V> mapper, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        return mapper().selectList(lqw, mapper);
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    default Page<T> selectPageList(Q query, PageQuery pageQuery) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        return mapper().selectPage(pageQuery.build(), lqw);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Class<V> classz) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        return mapper().selectPage(pageQuery.build(), lqw, classz);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Function<T, V> mapper) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        return mapper().selectPage(pageQuery.build(), lqw, mapper);
    }

    default Page<T> selectPageList(Q query, PageQuery pageQuery, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectPage(pageQuery.build(), lqw);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Class<V> classz, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectPage(pageQuery.build(), lqw, classz);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Function<T, V> mapper, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectPage(pageQuery.build(), lqw, mapper);
    }

    default Page<T> selectPageList(Q query, PageQuery pageQuery, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectPage(pageQuery.build(), lqw);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Class<V> classz, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectPage(pageQuery.build(), lqw, classz);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Function<T, V> mapper, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectPage(pageQuery.build(), lqw, mapper);
    }

    default Page<T> selectPageList(Q query, PageQuery pageQuery, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        return mapper().selectPage(pageQuery.build(), lqw);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Class<V> classz, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        return mapper().selectPage(pageQuery.build(), lqw, classz);
    }

    default <V> Page<V> selectPageList(Q query, PageQuery pageQuery, Function<T, V> mapper, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        return mapper().selectPage(pageQuery.build(), lqw, mapper);
    }

     default Page<T> selectPageByMap(Map<String, Object> columnMap, PageQuery pageQuery) {
        QueryWrapper<T> qw = Wrappers.query();
        return mapper().selectPage(pageQuery.build(), qw.allEq(columnMap));
    }

    default <V> Page<V> selectByMap(Map<String, Object> columnMap, PageQuery pageQuery, Class<V> classz) {
        QueryWrapper<T> qw = Wrappers.query();
        return mapper().selectPage(pageQuery.build(), qw.allEq(columnMap), classz);
    }

     default <V> Page<V> selectByMap(Map<String, Object> columnMap, PageQuery pageQuery, Function<T, V> mapper) {
        QueryWrapper<T> qw = Wrappers.query();
        return mapper().selectPage(pageQuery.build(), qw.allEq(columnMap), mapper);
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    default TableDataInfo<T> selectTableList(Q query, PageQuery pageQuery) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        Page<T> result = mapper().selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Class<V> classz) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Function<T, V> mapper) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, mapper);
        return TableDataInfo.build(result);
    }

    default TableDataInfo<T> selectTableList(Q query, PageQuery pageQuery, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        Page<T> result = mapper().selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Class<V> classz, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Function<T, V> mapper, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, mapper);
        return TableDataInfo.build(result);
    }

    default TableDataInfo<T> selectTableList(Q query, PageQuery pageQuery, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        Page<T> result = mapper().selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Class<V> classz, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Function<T, V> mapper, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, mapper);
        return TableDataInfo.build(result);
    }

    default TableDataInfo<T> selectTableList(Q query, PageQuery pageQuery, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        Page<T> result = mapper().selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Class<V> classz, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> selectTableList(Q query, PageQuery pageQuery, Function<T, V> mapper, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(query);
        lqw.select(predicate);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, mapper);
        return TableDataInfo.build(result);
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    default ModifyResult<T> insert(Q query) {
        T entity = MapstructUtils.convert(query, modelClass());
        return insertEntity(entity);
    }

    default ModifyResult<List<T>> insert(Collection<Q> querys) {
        List<T> entities = querys.stream()
            .map(query -> MapstructUtils.convert(query, modelClass()))
            .toList();
        return insertEntity(entities);
    }

    default ModifyResult<List<T>> insert(Collection<Q> querys, int batchSize) {
        List<T> entities = querys.stream()
            .map(query -> MapstructUtils.convert(query, modelClass()))
            .toList();
        return insertEntity(entities);
    }

    default ModifyResult<T> insertEntity(T entity) {
        int count = mapper().insert(entity);
        return new ModifyResult<>(count, entity);
    }

    default ModifyResult<List<T>> insertEntity(List<T> entities) {
        boolean bool = mapper().insertBatch(entities);
        return new ModifyResult<>(bool, entities);
    }

    default ModifyResult<List<T>> insertEntity(List<T> entities, int batchSize) {
        boolean bool = mapper().insertBatch(entities, batchSize);
        return new ModifyResult<>(bool, entities);
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    default ModifyResult<T> update(Q query) {
        T entity = MapstructUtils.convert(query, modelClass());
        return updateEntity(entity);
    }

    default ModifyResult<List<T>> update(List<Q> querys) {
        List<T> entities = querys.stream()
            .map(query -> MapstructUtils.convert(query, modelClass()))
            .toList();
        return updateEntity(entities);
    }

    default ModifyResult<List<T>> update(List<Q> querys, int batchSize) {
        List<T> entities = querys.stream()
            .map(query -> MapstructUtils.convert(query, modelClass()))
            .toList();
        return updateEntity(entities, batchSize);
    }

    default ModifyResult<T> updateEntity(T entity) {
        int count = mapper().updateById(entity);
        return new ModifyResult<>(count, entity);
    }

    default ModifyResult<List<T>> updateEntity(List<T> entities) {
        boolean bool = mapper().updateBatchById(entities);
        return new ModifyResult<>(bool, entities);
    }

    default ModifyResult<List<T>> updateEntity(List<T> entities, int batchSize) {
        boolean bool = mapper().updateBatchById(entities, batchSize);
        return new ModifyResult<>(bool, entities);
    }

    // ---------------------------------------------------------------------------------------------------------------------------

    default int deleteById(Serializable id) {
        return mapper().deleteById(id);
    }

    default int deleteByIds(Serializable... ids) {
        return mapper().deleteBatchIds(Arrays.asList(ids));
    }

    default int deleteByIds(Serializable[] ids, Supplier<Boolean> preValidate) {
        return deleteByIds(Arrays.asList(ids), preValidate);
    }

    default int deleteByIds(Collection<Serializable> ids) {
        return mapper().deleteBatchIds(ids);
    }

    default int deleteByIds(Collection<Serializable> ids, Supplier<Boolean> preValidate) {
        if (preValidate.get()) {
            return mapper().deleteBatchIds(ids);
        }
        return 0;
    }

    default int deleteByMap(Map<String, Object> columnMap) {
        QueryWrapper<T> qw = Wrappers.query();
        return mapper().delete(qw.allEq(columnMap));
    }

}
