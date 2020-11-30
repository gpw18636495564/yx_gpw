import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.baizhi.Yx_Application;
import org.apache.tomcat.jni.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class AliyunOSSTest {
    @Test
    public void t1() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName = "qixue-app";  //存储空间名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

        // 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
        // 此处以设置存储空间的存储类型为标准存储为例。
        // createBucketRequest.setStorageClass(StorageClass.Standard);
        // 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
        // createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)

        // 创建存储空间。
        ossClient.createBucket(createBucketRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void t2() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
//        ObjectListing objectListing = ossClient.listObjects(bucketName);
        // objectListing.getObjectSummaries获取所有文件的描述信息。
        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void T3() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName = "qixue-app";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除存储空间。
        ossClient.deleteBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test//上传网络流
    public void t4() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005
        String objectName="img/草原.jpg";  //保存的文件名   1.MP4  aaa.mp4
        //String localFile="C:\\Users\\Administrator\\Desktop\\video\\草原.mp4";   //本地文件位置
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605778905901&di=956684ceb3537184b563fc7c7f55fee8&imgtype=0&src=http%3A%2F%2Fpic8.nipic.com%2F20100703%2F145234_114912056232_2.jpg").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public void adds(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005
        String objectName="华晨宇山海.mp4";  //保存的文件名   1.MP4  aaa.mp4
        String localFile="C:\\Users\\hasee\\Desktop\\华晨宇山海.mp4";   //本地文件位置

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(localFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test//上传文件流
    public void t6() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\hasee\\Desktop\\华晨宇山海.mp4");
        ossClient.putObject(bucketName, "华晨宇山海.mp4", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public void addFile(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005
        String objectName="huohua.jpg";  //保存的文件名   1.MP4  aaa.mp4
        String localFile="";   //本地文件位置

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
      //  PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFile));

        // 上传文件。
       // ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
