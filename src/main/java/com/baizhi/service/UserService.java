package com.baizhi.service;

import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll(Integer page,Integer pagesize);

    /**
     * 修改用户状态
     * @param id
     */
    void u_state(String id);

    /**
     * 注册用户
     * @param user
     */
    String insert(User user);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);
    Integer count();
    void delete(String id);
    List<User>findAllSearch(Integer pages, Integer rows,String searchField,
                            String searchOper,String searchString
    );
    //搜索分页总数
    Integer findSearchTotalCounts(String searchField,String searchOper,String searchString);

    void headUpload(MultipartFile picImg, String id);
    void headUploadAliyun(MultipartFile picImg, String id);
    List<User> select();
    //查询所有每月女的方法
    List<Month> sexnv();
    //查询所有每月男的方法
    List<Month> sexnan();
    //查询
    List<City> month(String sex);
}
