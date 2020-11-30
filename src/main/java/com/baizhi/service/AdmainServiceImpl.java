package com.baizhi.service;

import com.baizhi.dao.AdmainDao;
import com.baizhi.entity.Admain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service("admainservice")
@Transactional
public class AdmainServiceImpl implements AdmianService {
    @Autowired
    private AdmainDao dao;
    @Resource
    private HttpSession session;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public HashMap<String,Object> login(Admain admain,String code) {
        HashMap<String,Object> map = new HashMap<>();
        //获取验证码
        String  code1 = (String) session.getAttribute("code");
        Admain login = dao.login(admain);
        if(code.equals(code1)) {
            if (login != null) {
                if (admain.getPassword().equals(login.getPassword())) {
                    map.put("message","登录成功");
                    map.put("status", "200");
                    session.setAttribute("login",login);
                } else {
                    map.put("message","密码错误，请重新输入");
                    map.put("status","201");
                }
            }else{
                map.put("message","用户不存在");
                map.put("status","201");
            }
        }else{
            map.put("message","验证码错误");
            map.put("status","201");
        }
        return map;
    }
}
