package com.corewell.study.dao;

import com.corewell.study.domain.Sensor;
import com.corewell.study.domain.request.SensorReq;
import com.corewell.study.domain.request.SensorUpdateParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
@Mapper
public interface SensorDao {
    /**
     * 查询传感器
     *
     * @param sensorReq
     * @return
     */
    List<Sensor> findSensor(SensorReq sensorReq);

    /**
     * 通过sensorId查询传感器
     *
     * @param sensorId
     * @return
     */
    Sensor findSensorBySensorId(Long sensorId);

    /**
     * 通过id查询传感器
     *
     * @param id
     * @return
     */
    Sensor findSensorById(Long id);

    /**
     * 新增传感器
     *
     * @param sensor
     * @return
     */
    int insertSensor(Sensor sensor);

    /**
     * 修改传感器
     *
     * @param sensor
     * @return
     */
    int updateSensor(Sensor sensor);

    /**
     * 修改传感器量程
     *
     * @param sensorUpdateParam
     * @return
     */
    int updateSensorRange(SensorUpdateParam sensorUpdateParam);


    /**
     * 通过sensorId删除传感器
     *
     * @param sensorId
     * @return
     */
    int deleteSensorBySensorId(@Param("sensorId") Long sensorId);

    /**
     * 批量删除传感器
     *
     * @param sensorId
     * @return
     */
    int deleteSensorById(@Param("sensorId") Long sensorId);

    /**
     * 通过deviceId删除传感器
     *
     * @param deviceId
     * @return
     */
    int deleteSensorByDeviceId(@Param("deviceId") Long deviceId);

    /**
     * 删除传感器
     *
     * @param id
     * @return
     */
    int updateSensorStatus(@Param("id") Long id);


}
