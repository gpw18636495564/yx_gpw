package com.baizhi.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//获取工厂 对象的一个类
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;
    //用来将springboot中创建好一个完整工厂通过参数形式传递给你
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
    //工具方法根据名字获取工厂中的对象
    public static Object getBean(String name){
        return context.getBean(name);
    }
}
