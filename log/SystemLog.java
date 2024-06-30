package com.lc.lcserve.log;

import com.lc.lcserve.model.InterfaceType;

import java.lang.annotation.*;

/**
 * 系统日志自定义注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
 
        /**
         * 日志名称
         * @return
         */
        String description() default "";
 
        /**
         * 日志类型
         * @return
         */
        InterfaceType type() default InterfaceType.SEARCH;
}
 