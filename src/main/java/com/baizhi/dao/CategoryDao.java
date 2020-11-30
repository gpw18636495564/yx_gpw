package com.baizhi.dao;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有一级类别的方法
     * @return
     */
    List<Category> selectOne(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * 根据一级类别查询二级类别
     * @param id
     * @return
     */
    List<Category> selectTwo(@Param("id") String id,@Param("begin") Integer begin, @Param("size") Integer size);
    List<Category> selectTwoall(String id);
    /**
     * 添加一级类别的的方法
     * @param category
     */
    void insertOne(Category category);
    /**
     * 添加二级类别的的方法
     * @param category
     */
    void insertTwo(Category category);

    /**
     * 修改类别的方法
     * @param category
     */
    void update(Category category);

    /**
     * 删除的方法
     * @param id
     */
    void delete(String id);

    /**
     * 查一个的方法
     * @param id
     * @return
     */
    Category select1(String id);
    Integer count1();
    Integer count2(String id);
    /**
     *
     */
    List<com.baizhi.po.Category> pageAll();
}
