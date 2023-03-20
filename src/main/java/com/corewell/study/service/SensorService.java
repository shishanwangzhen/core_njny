package com.corewell.study.service;

import com.corewell.study.domain.Sensor;
import com.corewell.study.domain.request.SensorReq;
import com.corewell.study.domain.request.SensorUpdateReq;
import com.corewell.study.domain.result.ResultMsg;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface SensorService {
    /**
     * 查询设备
     *
     * @param sensorReq
     * @return
     */
    ResultMsg findSensor(SensorReq sensorReq);

    /**
     * 传感器设置量程
     *
     * @param sensorUpdateReq
     * @return
     */
    ResultMsg updateSensorRange(SensorUpdateReq sensorUpdateReq);

    /**
     * 新增设备
     *
     * @param sensor
     * @return
     */
    ResultMsg insertSensor(Sensor sensor);

    /**
     * 修改设备
     *
     * @param sensor
     * @return
     */
    ResultMsg updateSensor(Sensor sensor);

    /**
     * 删除设备
     *
     * @param id
     * @return
     */
    ResultMsg updateSensorStatus(@Param("id") Long id);


}
