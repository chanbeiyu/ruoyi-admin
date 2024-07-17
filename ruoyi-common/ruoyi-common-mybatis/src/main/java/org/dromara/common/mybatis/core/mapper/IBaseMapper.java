package org.dromara.common.mybatis.core.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.dromara.common.core.utils.MapstructUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 自定义 Mapper 接口, 实现 自定义扩展
 *
 * @param <T> table 泛型
 * @author Lion Li
 * @since 2021-05-13
 */
@SuppressWarnings("unchecked")
public interface IBaseMapper<T> extends BaseMapper<T> {

    Log log = LogFactory.getLog(IBaseMapper.class);

    /**
     * 批量插入
     */
    default boolean insertBatch(Collection<T> entityList) {
        return Db.saveBatch(entityList);
    }

    /**
     * 批量更新
     */
    default boolean updateBatchById(Collection<T> entityList) {
        return Db.updateBatchById(entityList);
    }

    /**
     * 批量插入或更新
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList) {
        return Db.saveOrUpdateBatch(entityList);
    }

    /**
     * 批量插入(包含限制条数)
     */
    default boolean insertBatch(Collection<T> entityList, int batchSize) {
        return Db.saveBatch(entityList, batchSize);
    }

    /**
     * 批量更新(包含限制条数)
     */
    default boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return Db.updateBatchById(entityList, batchSize);
    }

    /**
     * 批量插入或更新(包含限制条数)
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return Db.saveOrUpdateBatch(entityList, batchSize);
    }

    /**
     * 插入或更新(包含限制条数)
     */
    default boolean insertOrUpdate(T entity) {
        return Db.saveOrUpdate(entity);
    }

    /**
     * 根据 ID 查询
     */
    default <R> R selectById(Serializable id, Class<R> rClass) {
        T obj = this.selectById(id);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return MapstructUtils.convert(obj, rClass);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    default <R> R selectById(Serializable id, Function<T, R> mapper) {
        T obj = this.selectById(id);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return mapper.apply(obj);
    }

    /**
     * 查询（根据ID 批量查询）
     */
    default <R> List<R> selectBatchIds(Collection<? extends Serializable> idList, Class<R> rClass) {
        List<T> list = this.selectBatchIds(idList);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return MapstructUtils.convert(list, rClass);
    }

    /**
     * 查询（根据ID 批量查询）
     */
    default <R> List<R> selectBatchIds(Collection<? extends Serializable> idList, Function<T, R> mapper) {
        List<T> list = this.selectBatchIds(idList);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return list.stream().map(mapper).toList();
    }

    /**
     * 查询（根据 columnMap 条件）
     */
    default <R> List<R> selectByMap(Map<String, Object> map, Class<R> rClass) {
        List<T> list = this.selectByMap(map);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return MapstructUtils.convert(list, rClass);
    }

    /**
     * 查询（根据 columnMap 条件）
     */
    default <R> List<R> selectByMap(Map<String, Object> map, Function<T, R> mapper) {
        List<T> list = this.selectByMap(map);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return list.stream().map(mapper).toList();
    }

    /**
     * 根据 entity 条件，查询一条记录
     */
    default <R> R selectOne(Wrapper<T> wrapper, Class<R> rClass) {
        T obj = this.selectOne(wrapper);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return MapstructUtils.convert(obj, rClass);
    }

    /**
     * 根据 entity 条件，查询一条记录
     */
    default <R> R selectOne(Wrapper<T> wrapper, Function<T, R> mapper) {
        T obj = this.selectOne(wrapper);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return mapper.apply(obj);
    }

    default List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }

    /**
     * 根据 entity 条件，查询全部记录
     */
    default <R> List<R> selectList(Class<R> rClass) {
        List<T> list = this.selectList(new QueryWrapper<>());
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return MapstructUtils.convert(list, rClass);
    }

    /**
     * 根据 entity 条件，查询全部记录
     */
    default <R> List<R> selectList(Function<T, R> mapper) {
        List<T> list = this.selectList(new QueryWrapper<>());
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
         return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据 entity 条件，查询全部记录
     */
    default <R> List<R> selectList(Wrapper<T> wrapper, Class<R> rClass) {
        List<T> list = this.selectList(wrapper);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return MapstructUtils.convert(list, rClass);
    }

    default <R> List<R> selectList(Wrapper<T> wrapper, Function<T, R> mapper) {
        List<T> list = this.selectList(wrapper);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据 Wrapper 条件，查询全部记录，只返回第一个字段的值
     */
    default List<Integer> selectInts(Wrapper<T> wrapper) {
        return this.selectObjs(wrapper).stream().filter(Objects::nonNull).map(o -> (Integer) o).collect(Collectors.toList());
    }

    /**
     * 根据 Wrapper 条件，查询全部记录，只返回第一个字段的值
     */
    default List<Long> selectLongs(Wrapper<T> wrapper) {
        return this.selectObjs(wrapper).stream().filter(Objects::nonNull).map(o -> (Long) o).collect(Collectors.toList());
    }

    /**
     * 根据 Wrapper 条件，查询全部记录，只返回第一个字段的值
     */
    default List<String> selectStrs(Wrapper<T> wrapper) {
        return this.selectObjs(wrapper).stream().filter(Objects::nonNull).map(o -> (String) o).collect(Collectors.toList());
    }

    /**
     * 根据 Wrapper 条件，查询全部记录，只返回第一个字段的值
     */
    default <R> List<R> selectObjs(Wrapper<T> wrapper, Function<? super Object, R> mapper) {
        return this.selectObjs(wrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page    分页查询条件（可以为 RowBounds.DEFAULT）
     * @param wrapper 实体对象封装操作类（可以为 null）
     */
    default <R, P extends IPage<R>> P selectPage(IPage<T> page, Wrapper<T> wrapper, Class<R> rClass) {
        IPage<T> pageData = this.selectPage(page, wrapper);
        IPage<R> voPage = new Page<>(pageData.getCurrent(), pageData.getSize(), pageData.getTotal());
        if (CollUtil.isEmpty(pageData.getRecords())) {
            return (P) voPage;
        }
        voPage.setRecords(MapstructUtils.convert(pageData.getRecords(), rClass));
        return (P) voPage;
    }

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page    分页查询条件（可以为 RowBounds.DEFAULT）
     * @param wrapper 实体对象封装操作类（可以为 null）
     */
    default <R, P extends IPage<R>> P selectPage(IPage<T> page, Wrapper<T> wrapper, Function<T, R> mapper) {
        IPage<T> pageData = this.selectPage(page, wrapper);
        IPage<R> voPage = new Page<>(pageData.getCurrent(), pageData.getSize(), pageData.getTotal());
        if (CollUtil.isEmpty(pageData.getRecords())) {
            return (P) voPage;
        }
        List<R> rList = pageData.getRecords().stream().map(mapper).toList();
        voPage.setRecords(rList);
        return (P) voPage;
    }

}
