package com.baizhi.aspect;

import com.baizhi.annotcation.AddLog;
import com.baizhi.dao.LogDao;
import com.baizhi.entity.Admain;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAspect {
    @Resource
    HttpServletRequest request;
    @Resource
    private LogDao dao;
    //@Around("execution(* com.baizhi.serviceImpl.*.*(..)) && !execution(* com.baizhi.serviceImpl.*.query*(..))")
    @Around("@annotation(com.baizhi.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){


        //谁  时间  操作  成功

        //获取用户数据
        Admain admin = (Admain) request.getSession().getAttribute("login");


        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解对应的属性值
        String value = addLog.value();

        String mess=null;
        Object result =null;
        String message1=null;
        //放行方法
        try {
            result = proceedingJoinPoint.proceed();
            String s = result.toString();
            mess="操作成功";
        } catch (Throwable throwable) {
            message1 = throwable.getMessage();
            if(message1==null){
                mess="操作成功";
            }else{
                mess="操作失败";
            }
        }
        Log log = new Log(UUID.randomUUID().toString(), admin.getUsername(),new Date(),methodName+" ("+value+")",mess);
        System.out.println("数据入库"+log);
        dao.insert(log);
        if (mess.equals("操作失败"))throw new RuntimeException(message1);
        return result;
    }
}
