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

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public interface IBaseService<T, V, Q> {

    IBaseMapper<T> mapper();

    LambdaQueryWrapper<T> buildQueryWrapper(Q q);

    default Class<T> modelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseService.class, 0);
    }

    default Class<V> voClass() {
        return (Class<V>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseService.class, 1);
    }

    default Class<Q> queryClass() {
        return (Class<Q>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseService.class, 2);
    }

    default V queryById(Long id) {
        return mapper().selectById(id, voClass());
    }

    default List<V> queryByIds(List<Long> ids) {
        return mapper().selectBatchIds(ids, voClass());
    }

    default List<V> queryList(Q bo) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        return mapper().selectList(lqw, voClass());
    }

    default List<V> queryList(Q bo, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        return mapper().selectList(lqw, voClass());
    }

    default List<V> queryList(Q bo, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        return mapper().selectList(lqw, voClass());
    }

    default List<V> queryList(Q bo, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        lqw.select(predicate);
        return mapper().selectList(lqw, voClass());
    }

    default TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, voClass());
        return TableDataInfo.build(result);
    }

    default TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, String... filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (ArrayUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !ArrayUtils.contains(filters, entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, voClass());
        return TableDataInfo.build(result);
    }

    default TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, Collection<String> filters) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        if (CollectionUtils.isNotEmpty(filters)) {
            lqw.select(modelClass(), entity -> !filters.contains(entity.getColumn()));
        }
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, voClass());
        return TableDataInfo.build(result);
    }

    default TableDataInfo<V> queryPageList(Q bo, PageQuery pageQuery, Predicate<TableFieldInfo> predicate) {
        LambdaQueryWrapper<T> lqw = buildQueryWrapper(bo);
        lqw.select(predicate);
        Page<V> result = mapper().selectPage(pageQuery.build(), lqw, voClass());
        return TableDataInfo.build(result);
    }

    default Boolean insertByBo(Q bo) {
        T add = MapstructUtils.convert(bo, modelClass());
        return mapper().insert(add) > 0;
    }

    default Boolean insertByBo(Q bo, Supplier<Boolean> validSupplier) {
        T add = MapstructUtils.convert(bo, modelClass());
        if (validSupplier.get()) {
            return mapper().insert(add) > 0;
        }
        return false;
    }

    default Boolean updateByBo(Q bo) {
        T update = MapstructUtils.convert(bo, modelClass());
        return mapper().updateById(update) > 0;
    }

    default Boolean updateByBo(Q bo, Supplier<Boolean> validSupplier) {
        T update = MapstructUtils.convert(bo, modelClass());
        if (validSupplier.get()) {
            return mapper().updateById(update) > 0;
        }
        return false;
    }

    default Boolean deleteByIds(Collection<Long> ids) {
        return mapper().deleteBatchIds(ids) > 0;
    }

    default Boolean deleteByIds(Collection<Long> ids, Supplier<Boolean> validSupplier) {
        if (validSupplier.get()) {
            return mapper().deleteBatchIds(ids) > 0;
        }
        return false;
    }

}
