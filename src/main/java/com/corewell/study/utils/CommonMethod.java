package com.corewell.study.utils;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 系统常用方法
 */
public class CommonMethod {

    static Logger logger = Logger.getLogger(CommonMethod.class); // 记录日志

    /**
     * @return
     * @methodDesc: 获取当前系统时间 java8
     * @creater: lmt
     * @creationDate:2020年1月4日 下午6:55:18
     */
    public static String getTimeNow() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public static void main(String[] args) {
    }
}
