package com.corewell.study.influxdb;

import com.corewell.study.utils.InfluxDbUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/19/17:02
 * @Description:
 */
@Configuration
public class InfluxDbConfig {

    @Value("${spring.influx.url:http://127.0.0.1:8083}")
    private String url;
    @Value("${spring.influx.user:root}")
    private String user;

    @Value("${spring.influx.password:root}")
    private String password;

    @Value("${spring.influx.database:test}")
    private String database;


    @Bean
    public InfluxDbUtils influxDbUtils() {
        return new InfluxDbUtils(user, password, url, database, "default");
    }
}

