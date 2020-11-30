package com.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class Download {
    private static OSS ossClient = null;
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
    private static String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
    private static String bucketName = "qixue-app";  //存储空间名  yingx-2005

    public static void download(String file, String name,String file1){
        String objectName = file;
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(file1 + "/" + name));
    }
    public static String add(){
        //2.判断上传文件夹是否存在
//        String realPath = request.getSession().getServletContext().getRealPath("/upload/user");
        String realPath="C:\\Users\\hasee\\Desktop\\应学用户表\\img";
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();//创建文件夹
        }
        return realPath;
    }
    public static void close(){
        ossClient.shutdown();
    }
}
