package com.corewell.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/13:27
 * @Description:
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.corewell.study.dao")
//@EnableSwagger2Doc //开启swagger文档
@EnableScheduling  //开启定时任务
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }
}
