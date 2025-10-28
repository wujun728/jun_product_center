package com.ruoyi.common.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.ruoyi.common.mybatis.pojo.PageParam;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.mybatis.util.MyBatisUtils;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多的查询、分页、批量操作等能力。
 */
public interface BaseMapperX<T> extends BaseMapper<T> {

    /**
     * 分页查询数据，并返回自定义的 PageResult 对象。
     *
     * @param pageParam   分页参数
     * @param queryWrapper 查询条件
     * @return 包含分页数据和总记录数的 PageResult 对象
     */
    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        // 构建分页对象
        IPage<T> mpPage = MyBatisUtils.buildPage(pageParam);
        // 执行分页查询
        selectPage(mpPage, queryWrapper);
        // 将结果转换为 PageResult 返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    /**
     * 根据字段名和值查询单条记录。
     *
     * @param field 字段名
     * @param value 字段值
     * @return 单条记录或 null
     */
    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 使用 Lambda 表达式根据字段和值查询单条记录。
     *
     * @param field 字段（Lambda 表达式）
     * @param value 字段值
     * @return 单条记录或 null
     */
    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 根据两个字段和对应的值查询单条记录。
     *
     * @param field1 第一个字段名
     * @param value1 第一个字段值
     * @param field2 第二个字段名
     * @param value2 第二个字段值
     * @return 单条记录或 null
     */
    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    /**
     * 使用 Lambda 表达式根据两个字段和对应的值查询单条记录。
     *
     * @param field1 第一个字段（Lambda 表达式）
     * @param value1 第一个字段值
     * @param field2 第二个字段（Lambda 表达式）
     * @param value2 第二个字段值
     * @return 单条记录或 null
     */
    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    /**
     * 查询所有记录的总数。
     *
     * @return 记录总数
     */
    default Long selectCount() {
        return selectCount(new QueryWrapper<T>());
    }

    /**
     * 根据字段名和值查询记录总数。
     *
     * @param field 字段名
     * @param value 字段值
     * @return 符合条件的记录总数
     */
    default Long selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 使用 Lambda 表达式根据字段和值查询记录总数。
     *
     * @param field 字段（Lambda 表达式）
     * @param value 字段值
     * @return 符合条件的记录总数
     */
    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 查询所有记录。
     *
     * @return 所有记录列表
     */
    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    /**
     * 根据字段名和值查询符合条件的记录列表。
     *
     * @param field 字段名
     * @param value 字段值
     * @return 符合条件的记录列表
     */
    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 使用 Lambda 表达式根据字段和值查询符合条件的记录列表。
     *
     * @param field 字段（Lambda 表达式）
     * @param value 字段值
     * @return 符合条件的记录列表
     */
    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 根据字段名和多个值查询符合条件的记录列表。
     *
     * @param field 字段名
     * @param values 字段值集合
     * @return 符合条件的记录列表
     */
    default List<T> selectList(String field, Collection<?> values) {
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    /**
     * 使用 Lambda 表达式根据字段和多个值查询符合条件的记录列表。
     *
     * @param field 字段（Lambda 表达式）
     * @param values 字段值集合
     * @return 符合条件的记录列表
     */
    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    /**
     * 逐条插入多条记录，适合少量数据插入或对性能要求不高的场景。
     * 如果需要插入大量数据，请使用 {@link com.baomidou.mybatisplus.extension.service.impl.ServiceImpl#saveBatch(Collection)} 方法。
     *
     * @param entities 实体集合
     */
    default void insertBatch(Collection<T> entities) {
        entities.forEach(this::insert);
    }

    /**
     * 更新多条记录，默认更新所有字段。
     *
     * @param update 更新实体
     */
    default void updateBatch(T update) {
        update(update, new QueryWrapper<>());
    }
}
