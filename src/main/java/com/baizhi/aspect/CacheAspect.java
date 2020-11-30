package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Set;

@Configuration
@Aspect
public class CacheAspect {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    //添加缓存
    //@Around("execution(* com.baizhi.serviceImpl.*.query*(..))")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("==环绕通知:");

        //Redis(Key,Value)
        /*
        * key:类名+方法名+实参
        * value:缓存数据
        * */

        //序列化解决乱
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        StringBuilder sb = new StringBuilder();

        //获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        sb.append(className);

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        sb.append(methodName);

        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg);
        }

        String key = sb.toString();

        //判断key是否存在
        Boolean aBoolean = redisTemplate.hasKey(key);

        ValueOperations string = redisTemplate.opsForValue();

        Object result =null;

        if(aBoolean){
            //key存在
            result = string.get(key);
            //从redis中取出缓存数据
        }else{
            //key不存在
            //没有缓存数据
            try {
                //放行方法(返回数据)
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            //加入缓存  放入redis
            string.set(key,result);
        }
        return result;
    }

    //清楚缓存
    //com.baizhi.serviceImpl.UserServiceImpl.queryByPage110
    //com.baizhi.serviceImpl.UserServiceImpl.queryByPage15
    //增删改执行成功之后清楚缓存

    //@After("@annotation(com.baizhi.annotcation.DelCahe)")
    public void delCache(JoinPoint joinPoint){

        System.out.println("后置通知:");
        //com.baizhi.serviceImpl.UserServiceImpl

        //获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();

        Set<String> keys = stringRedisTemplate.keys("*");

        //遍历key
        for (String key : keys) {

            System.out.println("key: "+key);

            if(key.startsWith(className)){
                //删除键值对
                redisTemplate.delete(key);
            }
        }

        //String,String
        // String  hash<key,value>
        /*
        * KEY,key,value
        * com.baizhi.serviceImpl.UserServiceImpl
        *       queryByPage15  数据
        *       queryByPage110 数据
        *
        * */

    }



}
