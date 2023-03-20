package com.corewell.study.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2023/02/25/11:24
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddLog {
    String interfaceType() default "";

    String interfaceInfo() default "";

    String interfaceName() default "";

    String dataId() default "";
}
