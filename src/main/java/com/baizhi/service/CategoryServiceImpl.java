package com.baizhi.service;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryDao dao;
    /**
     * 查询所有一级类别的方法
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> selectOne(Integer page, Integer size) {
        Integer begin=(page-1)*size;
        return dao.selectOne(begin,size);
    }

    /**
     * 根据一级类别查询二级类别
     *
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> selectTwo(String id, Integer page, Integer size) {
        Integer begin=(page-1)*size;
        return dao.selectTwo(id,begin,size);
    }

    /**
     * 添加一级类别的的方法
     *
     * @param category
     */
    @Override
    @AddLog("添加一级类别")
    @DelCahe
    public void insertOne(Category category) {
        category.setId(UUID.randomUUID().toString());
        category.setLevels(1);
        dao.insertOne(category);
    }

    /**
     * 添加二级类别的的方法
     *
     * @param category
     */
    @Override
    @AddLog("添加二级类别")
    @DelCahe
    public void insertTwo(Category category) {
        category.setId(UUID.randomUUID().toString());
        category.setLevels(2);
        log.debug("----------------: {}",category);
        dao.insertTwo(category);
    }

    /**
     * 修改类别的方法
     *
     * @param category
     */
    @Override
    @AddLog("修改类别")
    @DelCahe
    public void update(Category category) {
        dao.update(category);
    }

    /**
     * 删除的方法
     *
     * @param id
     */
    @AddLog("删除类别")
    @Override
    @DelCahe
    public void delete(String id) {
        Category category = dao.select1(id);
        if(category.getLevels()!=1) {
            dao.delete(id);
        }else{
            List<Category> list = dao.selectTwoall(id);
            if(list.size()==0)dao.delete(id);
            if(list.size()!=0)throw new RuntimeException("请先删除当前类别想下的二级类别");
        }
    }

    @Override
    public Integer count1() {
        return dao.count1();
    }

    @Override
    public Integer count2(String id) {
        return dao.count2(id);
    }

    /**
     *
     */
    @Override
    public List<com.baizhi.po.Category> pageAll() {
        return dao.pageAll();
    }
}
