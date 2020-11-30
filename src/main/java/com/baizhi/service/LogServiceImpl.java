package com.baizhi.service;

import com.baizhi.dao.LogDao;
import com.baizhi.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao dao;
    /**
     * 分页查询的方法
     *
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Log> selectPage(Integer page, Integer pagesize) {
        Integer begin=(page-1)*pagesize;
        return dao.selectPage(begin,pagesize);
    }

    /**
     * 查询总数的方法
     *
     * @return
     */
    @Override
    public Long count() {
        return dao.count();
    }
}
