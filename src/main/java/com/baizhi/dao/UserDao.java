package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll(@Param("begin") Integer begin,@Param("pagesize") Integer pagesize);

    /**
     * 修改用户状态
     * @param user
     */
    void u_state(User user);

    /**
     * 注册用户
     * @param user
     */
    void insert(User user);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    User selectOne(String id);

    /**
     * 查询总条数
     * @return
     */
    Integer count();

    /**
     * 注销用户的方法
     * @param id
     */
    void delete(String id);

    /**
     * 分页条件查询的方法
     * @param begin
     * @param rows
     * @param searchOper
     * @param searchString
     * @return
     */
    List<User>findAllSearch(@Param("begin")Integer begin,@Param("rows")Integer rows,@Param("searchField")String searchField,
    @Param("searchOper") String searchOper,@Param("searchString") String searchString
    );
    //搜索分页总数
    Integer findSearchTotalCounts(@Param("searchField")String searchField, @Param("searchOper")String searchOper, @Param("searchString")String searchString);

    void updateimg(User user);
    List<User> select();
    //查询所有每月女的方法
    List<Month> sexnv();
    //查询所有每月男的方法
    List<Month> sexnan();
    //查询
    List<City> month(String sex);
}
