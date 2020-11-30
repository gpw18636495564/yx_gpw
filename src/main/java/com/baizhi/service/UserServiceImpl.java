package com.baizhi.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao dao;
    @Autowired
    private HttpServletRequest request;
    /**
     * 分页所有用户
     *
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectAll(Integer page, Integer pagesize) {
        Integer begin=(page-1)*pagesize;
        return dao.selectAll(begin,pagesize);
    }

    /**
     * 修改用户状态
     *
     * @param id
     */
    @Override
    @AddLog("修改用户状态")
    @DelCahe
    public void u_state(String id) {
        User user = dao.selectOne(id);
        if(user.getState().equals("正常")){
            user.setState("冻结");
            dao.u_state(user);
        }else{
            user.setState("正常");
            dao.u_state(user);
        }
    }

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    @AddLog("添加用户")
    @DelCahe
    public String insert(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreate_date(new Date());
        user.setScore(0.0);
        user.setState("正常");
        if (user.getBrief()==null||user.getBrief()=="") {
            user.setBrief("该用户暂时没有简介！哭哭");
        }
        dao.insert(user);
        return user.getId();
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Override
    @AddLog("修改用户")
    @DelCahe
    public void update(User user) {
        dao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count() {
        return dao.count();
    }

    @Override
    @AddLog("删除用户")
    @DelCahe
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public List<User> findAllSearch(Integer pages, Integer rows, String searchField, String searchOper, String searchString) {
        Integer begin=(pages-1)*rows;
        return dao.findAllSearch(begin,rows,searchField,searchOper,searchString);
    }

    @Override
    public Integer findSearchTotalCounts(String searchField, String searchOper, String searchString) {
        return dao.findSearchTotalCounts(searchField,searchOper,searchString);
    }
    @Override
    public void headUpload(MultipartFile picImg, String id) {

        //文件上传
        //1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/user");

        //2.判断上传文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();//创建文件夹
        }

        //3.获取文件名
        String filename = picImg.getOriginalFilename();
        //给文件拼接时间戳
        String newName = new Date().getTime() + "-" + filename;

        //4.文件上传
        try {
            picImg.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //5.数据修改
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setPic_img(newName);
        user.setId(id);
        dao.updateimg(user);
    }

    @Override
    public void headUploadAliyun(MultipartFile picImg, String id) {
        byte[] bytes =null;
        try {
            bytes = picImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取文件名
        String filename = picImg.getOriginalFilename();
        String newName=new Date().getTime()+"-"+filename;
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005
        String objectName="user/"+newName;  //保存的文件名   1.MP4  aaa.mp4

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 上传Byte数组。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();

        //5.数据修改
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);

        User user = new User();
        user.setId(id);
        user.setPic_img("https://qixue-app.oss-cn-beijing.aliyuncs.com/"+objectName);

        dao.updateimg(user);

    }

    @Override
    public List<User> select() {
        return dao.select();
    }

    @Override
    public List<Month> sexnv() {
        return dao.sexnv();
    }

    @Override
    public List<Month> sexnan() {
        return dao.sexnan();
    }

    @Override
    public List<City> month(String sex) {
        return dao.month(sex);
    }
}
