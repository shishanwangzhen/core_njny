package com.corewell.study.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.corewell.study.domain.request.PushDataParam;
import com.corewell.study.domain.request.SensorsDates;
import com.corewell.study.utils.InfluxDbUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/20/17:15
 * @Description:
 */
@Slf4j
@Component
@RabbitListener(queues = "core-njny-queue")  //指定队列
public class RabbitReceiver {
    @Autowired
    private InfluxDbUtils influxDbUtils;

    @RabbitHandler
    public void process(String msg) {
        log.info("Receiver  : 消费成功：" + msg);
        PushDataParam pushData = JSON.parseObject(msg, PushDataParam.class);
        List<SensorsDates> sensorsDates = pushData.getSensorsDates();
        String deviceId = pushData.getDeviceId().toString();
        if ("1".equals(pushData.getType()) && sensorsDates.size() > 0) {
            for (SensorsDates sensorDates : sensorsDates) {
                log.info("sensorDates:  :" + sensorDates);
                InfluxDB influxDB = influxDbUtils.getInfluxDB();
                //CORE_NJNY为表名
                influxDB.write("test", "", Point.measurement("CORE_NJNY")
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("deviceId", deviceId)
                        .tag("sensorsId", sensorDates.getSensorsId().toString())
                        .addField("isAlarm", sensorDates.getIsAlarm())
                        .tag("sensorsTypeId", sensorDates.getSensorsTypeId().toString())
                        .addField("isLine", sensorDates.getIsLine())
                        .addField("reVal", sensorDates.getReVal())
                        .addField("value", sensorDates.getValue())
                        .addField("isAbnormal", sensorDates.getIsAbnormal())
                        .build());
            }
        }
        // TODO 测试数据库存储量保存控制器数据，生产环境部署时去掉
        else if (StringUtils.isAllBlank(pushData.getType()) && sensorsDates.size() > 0) {
            for (SensorsDates sensorDates : sensorsDates) {
                log.info("sensorDates:  :" + sensorDates);
                InfluxDB influxDB = influxDbUtils.getInfluxDB();
                if (sensorDates.getSensorsTypeId() == 1) {
                    influxDB.write("test", "", Point.measurement("CORE_NJNY")
                            .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                            .tag("deviceId", deviceId)
                            .tag("sensorsId", sensorDates.getSensorsId().toString())
                            .addField("isAlarm", sensorDates.getIsAlarm())
                            .tag("sensorsTypeId", sensorDates.getSensorsTypeId().toString())
                            .addField("isLine", sensorDates.getIsLine())
                            .addField("reVal", sensorDates.getReVal())
                            .addField("value", sensorDates.getValue())
                            .build());
                } else {
                    influxDB.write("test", "", Point.measurement("CORE_NJNY")
                            .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                            .tag("deviceId", deviceId)
                            .tag("sensorsId", sensorDates.getSensorsId().toString())
                            .addField("isAlarm", sensorDates.getIsAlarm())
                            .tag("sensorsTypeId", sensorDates.getSensorsTypeId().toString())
                            .addField("switcher", sensorDates.getSwitcher())
                            .addField("isHeartbeat", sensorDates.getIsHeartbeat())
                            .build());
                }
            }
        }
    }
}