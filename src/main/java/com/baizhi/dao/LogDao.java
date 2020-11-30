package com.baizhi.dao;

import com.baizhi.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {
    /**
     * 添加日志的方法
     * @param log
     */
    void insert(Log log);

    /**
     * 分页查询的方法
     * @param begin
     * @param pagesize
     * @return
     */
    List<Log> selectPage(@Param("begin") Integer begin, @Param("pagesize") Integer pagesize);

    /**
     * 查询总数的方法
     * @return
     */
    Long count();
}
