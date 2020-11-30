package com.baizhi.service;

import com.baizhi.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有一级类别的方法
     * @return
     */
    List<Category> selectOne(Integer page,Integer size);

    /**
     * 根据一级类别查询二级类别
     * @param id
     * @return
     */
    List<Category> selectTwo(String id,Integer page,Integer size);

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
    Integer count1();
    Integer count2(String id);
    /**
     *
     */
    List<com.baizhi.po.Category> pageAll();
}
