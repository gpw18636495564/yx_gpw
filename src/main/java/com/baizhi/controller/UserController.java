package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.Sc;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.Download;
import com.baizhi.util.ImageCodeUtil;
import io.goeasy.GoEasy;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService service;
    /**
     * 分页查询   String searchField  搜索的字段   searchOper eq  = 执行什么样搜索 String searchString  搜索值
     *           where 字段  <if test="searchOper==eq"> </if>  值
     * @return
     */
    @RequestMapping("/pageAll")
    @ResponseBody
    public Map<String, Object> all(Integer page, Integer rows,String searchField,String searchOper,String searchString){
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<String, Object> result = new HashMap<>();
        List<User> users;
        Integer totalCounts;
        //分页查询的结果
        if(StringUtils.isEmpty(searchField)) {
            users = service.selectAll(page, rows);
            //总条数
            totalCounts = service.count();
        }else{
            users = service.findAllSearch(page, rows, searchField, searchOper, searchString);
            totalCounts = service.findSearchTotalCounts(searchField, searchOper, searchString);
        }
        //总页数
        Integer totalPage = totalCounts%rows==0?totalCounts/rows:totalCounts/rows+1;
        result.put("page",page);
        result.put("total",totalPage);
        result.put("records",totalCounts);
        result.put("rows",users);
        return result;
    }
    @RequestMapping("/insert")
    public String insert(User user,MultipartFile file,String code,HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String code1 = (String) session.getAttribute("code");
        try {
            if (!code1.equals(code)) throw new RuntimeException("验证码错误请核对后再输入");
            //获取原始文件名
            String Filename = file.getOriginalFilename();
            //上传文件新名字
            String neName = FilenameUtils.getExtension(Filename);//原始文件的后缀
            String newname = UUID.randomUUID().toString().replace("-", "") + "." + neName;
            // 保存上传文件 以日期格式上传
            String realPath = session.getServletContext().getRealPath("/upload");
//            String payh = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            File dir = new File(realPath, payh);
//            if (!dir.mkdirs()) dir.mkdirs();
//            //参数代表上传文件目标保存位置
//            file.transferTo(new File(realPath + "\\" + payh, newname));
            file.transferTo(new File(realPath,newname));
            user.setPic_img(newname);
            service.insert(user);
        }catch (Exception e){
            String message = e.getMessage();
            request.setAttribute("message",message);
        }
            return "index";
    }
    //模板
    @RequestMapping("/code")
    @ResponseBody
    public void code(String phone,HttpServletRequest request) {
        System.out.println(phone);
        //获取随机字符
        String imafeCode = ImageCodeUtil.getSecurityCode();
        //保存session
        request.getSession().setAttribute("code",imafeCode);
        System.out.println(imafeCode);
        String message = AliyunUtil.sendPhoneMsg(phone,imafeCode);
        System.out.println(message);
    }
    @RequestMapping("/u_update")
    @ResponseBody
    public  void edit(User user){
        service.u_state(user.getId());
    }
    @ResponseBody
    @RequestMapping("/edit")
    public String edit(User user,String oper){
        System.out.println(user);
        String result =null;
        if(oper.equals("add")){
            result = service.insert(user);
        }
        if(oper.equals("edit")){
            service.update(user);
        }

        if(oper.equals("del")){
            service.delete(user.getId());
        }
        return result;
    }
    @RequestMapping("headUpload")
    public void headUpload(MultipartFile pic_img, String id) {
        service.headUploadAliyun(pic_img, id);
    }
    @RequestMapping("/excel")
    @ResponseBody
    public String excel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String add = Download.add();
        List<User> users = service.select();
        for (User s:users) {
            String name=s.getPic_img();
            String replace = name.replace("https://qixue-app.oss-cn-beijing.aliyuncs.com/", "");
            String jps=replace.replace("user/","");
            Download.download(replace,jps,add);
            String realPath = request.getSession().getServletContext().getRealPath("/upload/user");
            s.setPic_img(realPath+"/"+jps);
        }
        Download.close();
        //参数：(一级标题，二级标题，表名)，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("应学用户注册表","用户表","用户"),User.class,users);
//      workbook.write(new FileOutputStream(new File(file1+"/琪学用户表.xls")));
        workbook.write(new FileOutputStream(new File("C:\\Users\\hasee\\Desktop\\应学用户表\\琪学用户表.xls")));
        //释放资源
        workbook.close();
        return "请前往桌面进行查看";
    }
    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(HttpSession session){
        Object number = session.getAttribute("number");
        List<Month> sexnv = service.sexnv();
        List<Month> sexnan = service.sexnan();
        List<Integer> gol = new ArrayList<>();
        List<Integer> boy = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Integer num = -1;
            for (Month a : sexnv) {
                if (a.getMonth().equals(i)) {
                    num = a.getCount();
                }
                if (num == -1) {
                    num = 0;
                }

            }
            gol.add(num);
        }
        for (int i = 1; i <= 12; i++) {
            Integer num = -1;
            for (Month a : sexnan) {
                if (a.getMonth().equals(i)) {
                    num = a.getCount();
                }
                if (num == -1) {
                    num = 0;
                }

            }
            boy.add(num);
        }
        Integer[] count1 = boy.toArray(new Integer[boy.size()]);

        Integer[] count2 = gol.toArray(new Integer[gol.size()]);
        Map<String, Object> map = new HashMap<>();
        if (number==null){
            session.setAttribute("number",1);
            map.put("months", Arrays.asList("一月份", "二月份", "三月份", "四月份", "五月份", "六月份", "七月份", "八月份", "九月份", "十月份", "十一月份", "十二月份"));
            map.put("nv", Arrays.asList(count2));
            map.put("nan", Arrays.asList(count1));
        }else{
            map.put("months", Arrays.asList("一月份", "二月份", "三月份", "四月份", "五月份", "六月份", "七月份", "八月份", "九月份", "十月份", "十一月份", "十二月份"));
            map.put("nv", Arrays.asList(count2));
            map.put("nan", Arrays.asList(count1));
            String string = JSON.toJSONStringWithDateFormat(map, "yyyy年MM月dd日");
            //创建初始化对象
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-449e58270cd84abb855e9ccce2ac322e");
            //发送消息
            goEasy.publish("qi_xue", string);
        }
        return map;
    }
    @RequestMapping("/city")
    @ResponseBody
    public ArrayList<Sc> city(){
        ArrayList<Sc> list = new ArrayList<>();
        List<City> boys = service.month("男");
        List<City> golys = service.month("女");
        list.add(new Sc("男",boys));
        list.add(new Sc("女",golys));
        System.out.println(list);
        return list;
    }
}
