package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corewell.study.component.WebSocketServer;
import com.corewell.study.constants.BaseRedisKeyConstants;
import com.corewell.study.dao.SensorDao;
import com.corewell.study.domain.Sensor;
import com.corewell.study.domain.request.PushDataParam;
import com.corewell.study.domain.request.SensorsDates;
import com.corewell.study.service.PushDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 863586395
 */
@Slf4j
@Service("PushDataService")
public class PushDataServiceImpl implements PushDataService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SensorDao sensorDao;


    @Override
    public void getPushData(PushDataParam pushData) {
        log.info("------start------tlink推送前端并且写入influxdb数据getPushData:" + JSON.toJSONString(pushData));
        if (pushData == null || pushData.getDeviceId() == null) {
            try {
                webSocketServer.sendMessageAllUser("测试连接");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        Long deviceId = pushData.getDeviceId();
        if (stringRedisTemplate.hasKey(BaseRedisKeyConstants.DEVICE_KEY + deviceId)) {
            pushData.setType("1");
            List<SensorsDates> sensorsDatesList = pushData.getSensorsDates();
            Sensor sensor;
            Long sensorId;
            for (SensorsDates sensorsDates : sensorsDatesList) {
                sensorId = sensorsDates.getSensorsId();
                if (stringRedisTemplate.hasKey(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId)) {
                    String sensorStr = stringRedisTemplate.opsForValue().get(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId);
                    sensor = JSONObject.parseObject(sensorStr, Sensor.class);
                } else {
                    sensor = sensorDao.findSensorBySensorId(sensorId);
                    stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId, JSON.toJSONString(sensor), 7 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
                }
                Double reVal = Double.valueOf(sensorsDates.getReVal());
                sensorsDates.setIsAbnormal(0L);
                if (sensor.getMinimum().compareTo(reVal) > 0 || sensor.getMaximum().compareTo(reVal) < 0) {
                    sensorsDates.setIsAbnormal(1L);
                }
            }
        }
        String data = JSON.toJSONString(pushData);
        stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.DEVICE_IS_LINE_KEY + deviceId, data, 9 * 60 * 1000, TimeUnit.MILLISECONDS);
        try {
            webSocketServer.sendMessageAllUser(data);
            rabbitTemplate.convertAndSend("core-njny-queue", data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("tlink推送数据异常：e： "+e.toString());
        }

    }
}
