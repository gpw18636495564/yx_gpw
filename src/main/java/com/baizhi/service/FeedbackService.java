package com.baizhi.service;

import com.baizhi.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    /**
     * 查询所有反馈的
     * @return
     */
    List<Feedback> selectAll(Integer page,Integer pagesize);

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
