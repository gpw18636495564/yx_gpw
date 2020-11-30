package com.baizhi.dao;

import com.baizhi.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackDao {
    /**
     * 查询所有反馈的
     * @return
     */
    List<Feedback> selectAll(@Param("begin") Integer begin, @Param("pagesize") Integer pagesize);

    /**
     * 添加反馈的方法
     * @param feedback
     */
    void insert(Feedback feedback);

    /**
     * 删除反馈的方法
     * @param id
     */
    void delete(String id);
    Long count();
}
