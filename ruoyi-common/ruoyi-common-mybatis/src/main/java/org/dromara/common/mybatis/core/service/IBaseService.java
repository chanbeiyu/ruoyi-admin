package org.dromara.common.mybatis.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
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
        return (Class<Q>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseService.class, 2);
    }

    default <V> V queryById(Long id, Class<V> classz) {
        return mapper().selectById(id, classz);
    }

    default <V> List<V> queryByIds(List<Long> ids, Class<V> classz) {
        return mapper().selectBatchIds(ids, classz);
    }

    default <V> List<V> queryList(Q bo, Class<V> classz) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        return mapper().selectList(lqw, classz);
    }

    default <V> List<V> queryList(Q bo, Class<V> classz, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectList(lqw, classz);
    }

    default <V> List<V> queryList(Q bo, Class<V> classz, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectList(lqw, classz);
    }

    default <V> List<V> queryList(Q bo, Class<V> classz, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        lqw.select(predicate);
        return mapper().selectList(lqw, classz);
    }

    default <V> TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, Class<V> classz) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, Class<V> classz, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, Class<V> classz, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default <V> TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, Class<V> classz, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        lqw.select(predicate);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, classz);
        return TableDataInfo.build(result);
    }

    default Boolean insertByBo(Q bo) {
        T add = MapstructUtils.convert(bo, modelClass());
        return mapper().insert(add) > 0;
    }

    default Boolean insertByBo(Q bo, Supplier<Boolean> validation) {
        T add = MapstructUtils.convert(bo, modelClass());
        if (validation.get()) {
            return mapper().insert(add) > 0;
        }
        return false;
    }

    default Boolean insertByBo(Q bo, Supplier<Boolean> validation, Consumer<T> result) {
        T add = MapstructUtils.convert(bo, modelClass());
        if (validation.get()) {
            int r = mapper().insert(add);
            result.accept(add);
            return r > 0;
        }
        return false;
    }

    default Boolean updateByBo(Q bo) {
        T update = MapstructUtils.convert(bo, modelClass());
        return mapper().updateById(update) > 0;
    }

    default Boolean updateByBo(Q bo, Supplier<Boolean> validation) {
        T update = MapstructUtils.convert(bo, modelClass());
        if (validation.get()) {
            return mapper().updateById(update) > 0;
        }
        return false;
    }

    default Boolean deleteByIds(Serializable... ids) {
        return mapper().deleteBatchIds(Arrays.asList(ids)) > 0;
    }

    default Boolean deleteByIds(Serializable[] ids, Supplier<Boolean> validation) {
        return deleteByIds(Arrays.asList(ids), validation);
    }

    default Boolean deleteByIds(Collection<Serializable> ids) {
        return mapper().deleteBatchIds(ids) > 0;
    }

    default Boolean deleteByIds(Collection<Serializable> ids, Supplier<Boolean> validation) {
        if (validation.get()) {
            return mapper().deleteBatchIds(ids) > 0;
        }
        return false;
    }

}
