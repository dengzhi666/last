package com.baizhi.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解使用的时机
@Retention(RetentionPolicy.RUNTIME)
//注解使用的位置
@Target(ElementType.METHOD)
public @interface AopAnnotation {
}
