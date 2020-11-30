package com.baizhi.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;
import com.baizhi.po.VideoPO;
import com.baizhi.util.AliyunOSSUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao dao;
    @Autowired
    private HttpServletRequest request;
    /**
     * 分页查询视屏的方法
     *
     * @param
     * @param size
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Video> selectpage(Integer page, Integer size) {
        Integer begin=(page-1)*size;
        return dao.selectpage(begin,size);
    }

    /**
     * 添加视屏的方法
     *
     * @param video
     */
    @Override
    @AddLog("上传视屏")
    @DelCahe
    public String insert(Video video) {
        video.setId(UUID.randomUUID().toString());
        video.setUpload_time(new Date());
        video.setLike_count(0);
        video.setPlay_count(0);
        video.setState("正常");
        if (video.getCategory_id()==null||video.getCategory_id()==""){
            video.setCategory_id("5d019d37-2485-4db0-b364-976a76914fa1");
        }
        if(video.getUser_id()==null||video.getUser_id()==""){
            video.setUser_id("1");
        }
        System.out.println(video);
        dao.insert(video);
        return video.getId();
    }
    /**
     * 查询总数的方法
     *
     * @return
     */
    @Override
    public Integer count() {
        return dao.count();
    }

    /**
     * 上传到阿里云
     *
     * @param videoPath
     * @param id
     * @param request
     */
    @Override
    @DelCahe
    public void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request) {
        //1.视频上传至阿里云   字节数组
        //获取文件名
        String filename = videoPath.getOriginalFilename();
        //拼接时间戳    1606185263426-草原.mp4
        String newName=new Date().getTime()+"-"+filename;
        //拼接视频文件夹
        String videoName="video/"+newName;
        /*
         * 上传视频至阿里云
         * 参数:
         *   videoPath: MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadFileByte(videoPath,"qixue-app",videoName);


        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";

        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名
         *   coverName:封面名
         * */

        //4.修改视频的信息
        AliyunOSSUtil.interceptVideoCover("qixue-app", videoName,coverName);
        Video video = new Video();
        video.setId(id);
        video.setVideo_path("https://qixue-app.oss-cn-beijing.aliyuncs.com/video/"+newName);
        video.setCover_path("https://qixue-app.oss-cn-beijing.aliyuncs.com/"+coverName);
        dao.updateImg(video);


    }

    @Override
    @DelCahe
    public void uploadVdieos(MultipartFile videoPath, String id) {

        //1.获取文件上传的路径  根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/video");

        //2.判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();  //创建文件夹
        }
        //获取文件名
        String filename = videoPath.getOriginalFilename();

        //创建一个新的名字    原名称-时间戳  10.jpg-1590390153130
        String newName = new Date().getTime() + "-" + filename;

        //3.文件上传
        try {
            videoPath.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.修改图片路径
        //修改的条件
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(id);

        Video video = new Video();
        video.setCover_path("aaaa"); //设置封面
        video.setVideo_path("aaaaaa"); //设置视频地址

        //修改
        dao.updateImg(video);
    }

    /**
     * 删除视频的方法
     *
     * @param id
     */
    @Override
    @AddLog("删除视频")
    @DelCahe
    public void delete(String id) {
        //根据id查询数据
        Video video = dao.selectOne(id);
        System.out.println(video);
        String videoPath = video.getVideo_path().replace("https://qixue-app.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = video.getCover_path().replace("https://qixue-app.oss-cn-beijing.aliyuncs.com/", "");
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName = "qixue-app";  //存储空间名
//        String objectName = "2020宣传视频.mp4";  //文件名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, videoPath);
        ossClient.deleteObject(bucketName,coverPath);
        // 关闭OSSClient。
        ossClient.shutdown();
        //1.删除数据
       dao.delete(id);
    }

    @Override
    @AddLog("修改视频")
    @DelCahe
    public String update(Video video1) {
        //根据id查询数据
        Video video = dao.selectOne(video1.getId());
        System.out.println(video);
        String videoPath = video.getVideo_path().replace("https://qixue-app.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = video.getCover_path().replace("https://qixue-app.oss-cn-beijing.aliyuncs.com/", "");
        video1.setCover_path("1");
        video1.setVideo_path("1");
        video1.setUpload_time(new Date());
        video1.setLike_count(0);
        video1.setPlay_count(0);
        video1.setState("正常");
        System.out.println("video1 = " + video1);
        dao.update(video1);
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName = "qixue-app";  //存储空间名
//        String objectName = "2020宣传视频.mp4";  //文件名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, videoPath);
        ossClient.deleteObject(bucketName,coverPath);
        // 关闭OSSClient。
        ossClient.shutdown();
        return video.getId();
    }

    /**
     * 根据上传时间进行排序
     *
     * @return
     */
    @Override
    public List<VideoPO> queryByReleaseTime() {
        return dao.queryByReleaseTime();
    }

    /**
     * 根据名字模糊查询
     *
     * @param content
     * @return
     */
    @Override
    public List<VideoPO> queryByLikeVideoName(String content) {
        return dao.queryByLikeVideoName(content);
    }
}
