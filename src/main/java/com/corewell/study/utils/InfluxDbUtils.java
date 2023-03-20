package com.corewell.study.utils;

import lombok.Data;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/19/17:08
 * @Description:
 */


@Data
public class InfluxDbUtils {
    private String user;
    private String password;
    private String url;
    public String database;
    private String retentionPolicy;
    // InfluxDB实例
    private InfluxDB influxDB;

    Logger logger = LoggerFactory.getLogger(InfluxDbUtils.class);

    // 数据保存策略
    public static String policyNamePix = "logmonitor";

    public InfluxDbUtils(String user, String password, String url, String database,
                         String retentionPolicy) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.database = database;
        this.retentionPolicy = retentionPolicy == null || "".equals(retentionPolicy) ? "autogen" : retentionPolicy;
        this.influxDB = influxDbBuild();
    }

    /**
     * 连接数据库 ，若不存在则创建
     *
     * @return influxDb实例
     */
    private InfluxDB influxDbBuild() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(url, user, password);
        }
        try {
            createDB(database);
            influxDB.setDatabase(database);
        } catch (Exception e) {
            logger.error("create influx db failed, error: {}", e.getMessage());
        } finally {
            //influxDB.setRetentionPolicy(retentionPolicy);
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }

    /****
     *  创建数据库
     * @param database
     */
    private void createDB(String database) {
        influxDB.query(new Query("CREATE DATABASE " + database));
    }
}

