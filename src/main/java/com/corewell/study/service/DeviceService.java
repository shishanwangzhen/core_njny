package com.corewell.study.service;

import com.corewell.study.domain.Device;
import com.corewell.study.domain.request.*;
import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface DeviceService {
    /**
     * 查询设备
     *
     * @param deviceReq
     * @return
     */
    ResultMsg findDevice(DeviceReq deviceReq);
    /**
     * 学生端查询设备添加到项目组
     *
     * @param deviceByProjectIdAndTypeReq
     * @return
     */
    ResultMsg findDeviceByProjectId(DeviceByProjectIdAndTypeReq deviceByProjectIdAndTypeReq);

    /**
     * 查询设备和状态
     *
     * @param deviceReq
     * @return
     */
    ResultMsg findDeviceAndIsLine(DeviceReq deviceReq);

    /**
     * 定时任务查询设备状态
     *
     * @param deviceId
     * @return
     */
    ResultMsg findDeviceIsLine(Long deviceId);

    /**
     * 查询采集控制设备
     *
     * @param controllerAndCollectionDeviceReq
     * @return
     */
    ResultMsg findControllerAndCollectionDevice(ControllerAndCollectionDeviceReq controllerAndCollectionDeviceReq);

    /**
     * 查询设备绑定项目组
     *
     * @param projectId
     * @return
     */
    ResultMsg findDeviceBindGroup(Long projectId);


    /**
     * 根据deviceId查询设备详情
     *
     * @param deviceId
     * @return
     */
    ResultMsg findDeviceByDeviceId(Long deviceId);

    /**
     * 新增设备
     *
     * @param deviceInsertParam
     * @return
     */
    ResultMsg insertDevice(DeviceInsertParam deviceInsertParam);

    /**
     * 新增被控设备
     *
     * @param device
     * @return
     */
    ResultMsg insertVideoDevice(Device device);

    /**
     * 修改被控设备
     *
     * @param device
     * @return
     */
    ResultMsg updateVideoDevice(Device device);

    /**
     * 删除被控设备
     *
     * @param id
     * @return
     */
    ResultMsg deleteVideoDevice(Long id);


    /**
     * 修改设备
     *
     * @param deviceUpdateParam
     * @return
     */
    ResultMsg updateDevice(DeviceUpdateParam deviceUpdateParam);

    /**
     * 删除设备
     *
     * @param deviceId
     * @return
     */
    ResultMsg deleteDevice(Long deviceId);

    /**
     * 设备绑定项目
     *
     * @param deviceBindingReq
     * @return
     */
    ResultMsg updateDeviceBinding(DeviceBindingReq deviceBindingReq);

    /**
     * 项目解绑设备
     *
     * @param id
     * @return
     */
    ResultMsg updateDeviceBindingById(Long id);

    /**
     * 项目组绑定设备
     *
     * @param deviceBindingGroupReq
     * @return
     */
    ResultMsg updateDeviceBindingGroup(DeviceBindingGroupReq deviceBindingGroupReq);


    /**
     * 设备开关下行控制
     *
     * @param deviceSwitcherParam
     * @return
     */
    ResultMsg switcherController(DeviceSwitcherParam deviceSwitcherParam);

    /**
     * 设备数据下行
     *
     * @param deviceWriteParam
     * @return
     */
    ResultMsg deviceWrite(DeviceWriteParam deviceWriteParam);

    /**
     * 传感器数据上报
     *
     * @param sendDataPointParam
     * @return
     */
    ResultMsg sendDataPoint(SendDataPointParam sendDataPointParam);


    /**
     * 获取设备传感器历史数据
     *
     * @param sensorHistoryParam
     * @return
     */
    ResultMsg getSensorHistroy(SensorHistoryParam sensorHistoryParam);

    /**
     * 获取设备参数
     *
     * @param deviceId
     * @return
     */
    ResultMsg getParams(Long deviceId);

    /**
     * 设置参数
     *
     * @param setParamsReq
     * @return
     */
    ResultMsg setParams(SetParamsReq setParamsReq);

    /**
     * modbus 协议读写指令设置
     *
     * @param modbusReq
     * @return
     */
    ResultMsg setModbus(ModbusReq modbusReq);


    /**
     * 获取modbus读写指令
     *
     * @param modbusGetReq
     * @return
     */
    ResultMsg getModbus(ModbusGetReq modbusGetReq);

    /**
     * modbus读写指令修改
     *
     * @param modbusReq
     * @return
     */
    ResultMsg updateModbus(ModbusReq modbusReq);

    /**
     * 获取tcp/udp协议标签
     *
     * @param deviceId
     * @return
     */
    ResultMsg getProtocolLabel(Long deviceId);

    /**
     * tcp/udp协议标签设置
     *
     * @param protocolLabelReq
     * @return
     */
    ResultMsg setProtocolLabel(ProtocolLabelReq protocolLabelReq);


    /**
     * 获取mqtt/tp500/coap协议读写标识
     *
     * @param getSensorFlagReq
     * @return
     */
    ResultMsg getFlag(GetSensorFlagReq getSensorFlagReq);

    /**
     * 设置mqtt/tp500/coap协议读写标识
     *
     * @param setFlagReq
     * @return
     */
    ResultMsg setFlag(SetSensorFlagReq setFlagReq);

    /**
     * 获取单个传感器数据
     *
     * @param sensorId
     * @return
     */
    ResultMsg getSingleSensorDatas(Long sensorId);

    /**
     * 设备移除项目组
     *
     * @param id
     * @return
     */
    ResultMsg updateBindingGroupById(Long id);


}
