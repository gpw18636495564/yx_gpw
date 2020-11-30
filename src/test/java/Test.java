import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import java.io.File;
import java.io.IOException;
public class Test {
    public static void download(String objectName,String name) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("F:/yx_img/"+name));

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
