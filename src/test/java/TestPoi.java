import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.baizhi.Yx_Application;
import com.baizhi.entity.Upload;
import com.baizhi.util.Download;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

import static com.baizhi.util.Download.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Yx_Application.class)
public class TestPoi {
    @Autowired
    private UserService service;
    @Autowired
    HttpServletRequest request;
    @Test
    public void t1(){
        //创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表 参数:工作表名称（sheet1,sheet2）
        //如果不指定工作表 默认按照:sheet1,sheet2...命名
        HSSFSheet sheet = workbook.createSheet("1用户信息表");
        HSSFSheet sheet1 = workbook.createSheet("2用户信息表");
        //创建一行 参数:单元格下标（下标从零开始）
        HSSFRow row = sheet.createRow(1);
        //创建单元格 参数：单元格下标（下标从0开始）
        HSSFCell cell = row.createCell(3);
        //给单元格设置内容
        cell.setCellValue("哈哈");
        //导出Excel文档
        try {
            workbook.write(new FileOutputStream(new File("E://aa.xls")));
            //释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void t2() throws IOException {
        List<User> users = service.selectAll(1, 10);
//        users.forEach(a -> System.out.println(a));
        //创建Excel工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表 参数：表名（相当于Excel的sheet1,sheet2）
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        //创建样式对象
        CellStyle cellStyle2 = workbook.createCellStyle();
        //创建日期对象
        DataFormat dataFormat = workbook.createDataFormat();
        //设置日期格式
        cellStyle2.setDataFormat(dataFormat.getFormat("yyy年MM月dd日"));
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);//颜色
        font.setColor(IndexedColors.GREEN.getIndex());//颜色
        font.setFontHeightInPoints((short) 20);
        font.setFontName("宋体");
        font.setItalic(true);    //斜体
        font.setUnderline(FontFormatting.U_SINGLE);  //下划线
        HSSFRow row2 = sheet.createRow(0);
        HSSFCell cell2 = row2.createCell(0);
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(cellAddresses);
        cell2.setCellValue("用户信息");
        //创建标题行
        HSSFRow row = sheet.createRow(1);
        String[] title={"ID","昵称","头像","手机号","用户简介","分数","注册时间","状态"};
        //处理单元格对象
        HSSFCell cell=null;
        for (int i = 0; i <title.length ; i++) {
             cell= row.createCell(i);//单元格下标
             cell.setCellValue(title[i]);//单元格的内容
            sheet.setColumnWidth(i,35*256);
            row.setHeight((short) 400);
        }
        //处理数据行
        for (int i = 0; i <users.size() ; i++) {
            //遍历一次创建行
            HSSFRow row1 = sheet.createRow(i+2);
            //每行放对应的数据
            row1.createCell(0).setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getNick_name());
            row1.createCell(2).setCellValue(users.get(i).getPic_img());
            row1.createCell(3).setCellValue(users.get(i).getPhone());
            row1.createCell(4).setCellValue(users.get(i).getBrief());
            row1.createCell(5).setCellValue(users.get(i).getScore());
            row1.createCell(6).setCellValue(users.get(i).getCreate_date());
            row1.createCell(7).setCellValue(users.get(i).getState());
            HSSFCell cell1 = row1.createCell(6);
            cell1.setCellStyle(cellStyle2);
            cell1.setCellValue(users.get(i).getCreate_date());
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }

        //创建输出流，从内存写入到本地磁盘
        workbook.write(new FileOutputStream(new File("E:/aa.xls")));
        //关闭资源
        workbook.close();
    }
    @Test//POI导入
    public void t3(){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:/aa.xls")));
            //获取工作簿
            HSSFSheet sheet = workbook.getSheet("用户信息表");
            for (int i = 2; i <sheet.getLastRowNum()+1 ; i++) {
                User user = new User();
                //获取行
                HSSFRow row = sheet.getRow(i);
                //获取ID
                user.setId(row.getCell(0).getStringCellValue());
                user.setNick_name(row.getCell(1).getStringCellValue());
                user.setPic_img(row.getCell(2).getStringCellValue());
                user.setPhone(row.getCell(3).getStringCellValue());
                user.setBrief(row.getCell(4).getStringCellValue());
                user.setScore(row.getCell(5).getNumericCellValue());
                user.setCreate_date(row.getCell(6).getDateCellValue());
                user.setState(row.getCell(7).getStringCellValue());
                System.out.println(user);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test//注解导出
    public void t5() throws IOException {
        List<User> users = service.select();
        for (User s:users) {
            String name=s.getPic_img();
            String replace = name.replace("https://qixue-app.oss-cn-beijing.aliyuncs.com/", "");
            String jps=replace.replace("user/","");
            Download.download(replace,jps,add());
            String realPath = request.getSession().getServletContext().getRealPath("/upload/user");
            s.setPic_img(realPath+"/"+jps);
            System.out.println(s);
        }
        String realPath = request.getSession().getServletContext().getRealPath("/upload/excel");

        //2.判断上传文件夹是否存在
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();//创建文件夹
        }
        //参数：(一级标题，二级标题，表名)，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("应学用户注册表","用户表","用户"),User.class,users);
        workbook.write(new FileOutputStream(new File(file1+"/琪学用户表.xls")));
        //释放资源
        workbook.close();
    }
    @Test
    public void t6(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G9f7zursAWZMwgBnZLn";
        String accessKeySecret = "oigvyzYZlsjhAUT8OaB1ioX3KMDcoV";
        String bucketName="qixue-app";  //存储空间名  yingx-2005
        String objectName="user/1606181073234-288344.jpg";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println(objectName);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。


        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
