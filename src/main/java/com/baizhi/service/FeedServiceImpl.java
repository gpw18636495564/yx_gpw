package com.baizhi.service;

import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.FeedbackDao;
import com.baizhi.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FeedServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao dao;
    /**
     * 查询所有反馈的
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Feedback> selectAll(Integer page,Integer pagesize) {
        Integer begin=(page-1)*pagesize;
        return dao.selectAll(begin,pagesize);
    }

    /**
     * 添加反馈的方法
     *
     * @param feedback
     */
    @Override
    @DelCahe
    public void insert(Feedback feedback) {
        feedback.setId(UUID.randomUUID().toString());
        dao.insert(feedback);
    }

    /**
     * 删除反馈的方法
     *
     * @param id
     */
    @Override
    @DelCahe
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public Long count() {
        return dao.count();
    }
}
