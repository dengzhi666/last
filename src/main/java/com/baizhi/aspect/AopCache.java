package com.baizhi.aspect;

import com.baizhi.annotation.AopAnnotation;
import com.baizhi.util.SerializeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@Aspect
public class AopCache {
        @Autowired
        private StringRedisTemplate stringRedisTemplate;

        @Around("execution(* com.baizhi.service.*.query*(..))")
        public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
                //判断是否有注解 有注解就切  没有则放行
                MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
                AopAnnotation annotation = methodSignature.getMethod().getAnnotation(AopAnnotation.class);
                if(annotation != null) {
                        System.out.println("有注解 进入缓存机制");
                        //获得namespace
                        String id = proceedingJoinPoint.getTarget().getClass().getName();
                        //获得 key /方法名
                        String method = proceedingJoinPoint.getSignature().getName();
                        //获得参数
                        Object[] args = proceedingJoinPoint.getArgs();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(method);
                        for (Object arg : args) {
                                stringBuilder.append(arg);
                        }
                        String key = stringBuilder.toString();
                        String value = (String) stringRedisTemplate.opsForHash().get(id, key);
                        if (value == null) {
                                System.out.println("缓存中没有数据");
                                Object o = proceedingJoinPoint.proceed();
                                System.out.println("获取数据 存入缓存中");
                                stringRedisTemplate.opsForHash().put(id, key, SerializeUtils.serialize(o));
                                return o;
                        } else {
                                System.out.println("缓存中有数据 直接使用");
                                return SerializeUtils.serializeToObject(value);
                        }
                }else{
                        System.out.println("没有注解 放行");
                        return proceedingJoinPoint.proceed();
                }
        }
        @AfterReturning("execution(* com.baizhi.service.*.*(..)) && !execution(* com.baizhi.service.*.query*(..))")
        public void after(JoinPoint joinPoint){
                System.out.println("清除当前namespace下所有的缓存");
                String id = joinPoint.getTarget().getClass().getName();
                stringRedisTemplate.delete(id);
        }




}
