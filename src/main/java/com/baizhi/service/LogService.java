package com.baizhi.service;

import com.baizhi.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogService {

    /**
     * 分页查询的方法
     * @param page
     * @param pagesize
     * @return
     */
    List<Log> selectPage(Integer page,Integer pagesize);
    /**
     * 查询总数的方法
     * @return
     */
    Long count();
}
