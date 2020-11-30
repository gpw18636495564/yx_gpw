package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Admain;
import com.baizhi.entity.Log;
import com.baizhi.entity.User;
import com.baizhi.service.AdmianService;
import com.baizhi.service.LogService;
import com.baizhi.util.ImageCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admain")
public class AdmainController {
    private static final Logger log = LoggerFactory.getLogger(AdmainController.class);
    @Autowired
    private AdmianService service;
    @Autowired
    private LogService logService;
    @RequestMapping("/login")
    @ResponseBody
    public HashMap<String,Object> login(Admain admain,String code){
      return service.login(admain,code);
    }
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("login");
        return "redirect:/login/login.jsp";
    }
    @RequestMapping("code")
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取随机字符
        String imafeCode = ImageCodeUtil.getSecurityCode();
        //保存session
        request.getSession().setAttribute("code",imafeCode);
        //根据随机字符生成验证码图片
        BufferedImage image = ImageCodeUtil.createImage(imafeCode);
        ImageIO.write(image,"png",response.getOutputStream());
    }
    @RequestMapping("/log")
    @ResponseBody
    public Map<String, Object> log(Integer page, Integer rows){
        List<Log> logs = logService.selectPage(page, rows);
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<String, Object> result = new HashMap<>();
        Long count = logService.count();
        //总页数
        Long totalPage = count%rows==0?count/rows:count/rows+1;
        result.put("page",page);
        result.put("total",totalPage);
        result.put("records",count);
        result.put("rows",logs);
        return result;
    }
}
