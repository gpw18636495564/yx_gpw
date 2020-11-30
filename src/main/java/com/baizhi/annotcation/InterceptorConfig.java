package com.baizhi.annotcation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;

@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> list = new ArrayList<>();
        list.add("/admain/login");
        list.add("/admain/code");
        //添加那个拦截器
        registry.addInterceptor(new MyInterceptor1())
                .addPathPatterns("")//拦截所有控制器请求
                .excludePathPatterns(list);//排除指定的请求;
    }
}