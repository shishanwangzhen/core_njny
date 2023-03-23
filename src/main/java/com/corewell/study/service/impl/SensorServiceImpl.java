package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.corewell.study.constants.BaseRedisKeyConstants;
import com.corewell.study.dao.SensorDao;
import com.corewell.study.domain.Sensor;
import com.corewell.study.domain.request.SensorReq;
import com.corewell.study.domain.request.SensorUpdateParam;
import com.corewell.study.domain.request.SensorUpdateReq;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/17/13:31
 * @Description:
 */
@Service("SensorService")
@Slf4j
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorDao sensorDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultMsg findSensor(SensorReq sensorReq) {
        log.info("findSensor:  sensorReq:  " + JSON.toJSONString(sensorReq));
        List<Sensor> sensorList = sensorDao.findSensor(sensorReq);
        return ResultMsg.success(sensorList);
    }

    @Override
    public ResultMsg updateSensorRange(SensorUpdateReq sensorUpdateReq) {
        log.info("updateSensorRange:  sensorUpdateReq:  " + JSON.toJSONString(sensorUpdateReq));
        try {
            List<SensorUpdateParam> sensorUpdateParams = sensorUpdateReq.getSensorUpdateParams();
            for (SensorUpdateParam sensorUpdateParam : sensorUpdateParams) {
                sensorDao.updateSensorRange(sensorUpdateParam);
                Sensor sensor = sensorDao.findSensorById(sensorUpdateParam.getId());
                stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.SENSOR_KEY + sensor.getDeviceId() + ":" + sensor.getSensorId(), JSON.toJSONString(sensor), 7*24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
            }
            return ResultMsg.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg insertSensor(Sensor sensor) {
        sensor.setCreateTime(new Date());
        int result = sensorDao.insertSensor(sensor);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    public ResultMsg updateSensor(Sensor sensor) {
        sensor.setUpdateTime(new Date());

        int result = sensorDao.updateSensor(sensor);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.UPDATE_FAILED);
    }

    @Override
    public ResultMsg updateSensorStatus(Long id) {
        int result = sensorDao.updateSensorStatus(id);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.DELETE_FAILED);
    }
}
