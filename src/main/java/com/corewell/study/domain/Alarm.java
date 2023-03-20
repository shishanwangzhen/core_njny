package com.corewell.study.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("触发器")
public class Alarm {

    /**
     * 触发器Id
     */
    @ApiModelProperty(value = "触发器Id", required = false, example = "")
    private Long id;

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = false, example = "")
    private Long deviceId;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", required = false, example = "")
    private String deviceName;

    /**
     * 传感器名称
     */
    @ApiModelProperty(value = "传感器名称", required = false, example = "")
    private String sensorName;

    /**
     * 转发设备Id
     */
    @ApiModelProperty(value = "转发设备Id", required = false, example = "")
    private String forwardDeviceId;

    /**
     * 转发的设备名称
     */
    @ApiModelProperty(value = "转发的设备名称", required = false, example = "")
    private String forwardDeviceName;

    /**
     * 触发器是否开启标识 0 否 1是
     */
    @ApiModelProperty(value = "触发器是否开启标识 0 否 1是", required = false, example = "")
    private Long active;
    /**
     * 触发器类型 取值范围 1-15 分别表示,1数值高于X、2数值低于Y、3数值高于X低于Y、4数值高于X低于Y超过M分钟、5数值高于X报警低于Y恢复、6数值低于X报警高于Y恢复、7数值在X和Y之间、8数值超过M分钟高于X、9数值超过M分钟低于Y、10传感器未连接、11超过M分钟未连接、12开关ON、13开关OFF、14数值等于X、15数值不等于X 必选参数
     */
    @ApiModelProperty(value = "触发器类型 取值范围 1-15 分别表示,1数值高于X、2数值低于Y、3数值高于X低于Y、4数值高于X低于Y超过M分钟、5数值高于X报警低于Y恢复、6数值低于X报警高于Y恢复、7数值在X和Y之间、8数值超过M分钟高于X、9数值超过M分钟低于Y、10传感器未连接、11超过M分钟未连接、12开关ON、13开关OFF、14数值等于X、15数值不等于X 必选参数", required = false, example = "")
    private String alarmType;

    /**
     * 触发器是否报警状态 0 否 1是
     */
    @ApiModelProperty(value = "触发器是否报警状态 0 否 1是", required = false, example = "")
    private Long isAlarm;

    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = false, example = "")
    private Long sensorId;

    /**
     * 转发数据类型
     */
    @ApiModelProperty(value = "转发数据类型", required = false, example = "")
    private String forwardDataType;


    /**
     * 转发设备协议类型
     */
    @ApiModelProperty(value = "转发设备协议类型", required = false, example = "")
    private String forwardLinkType;

    /**
     * 转发传感器Id
     */
    @ApiModelProperty(value = "转发传感器Id", required = false, example = "")
    private String forwardSensorId;

    /**
     * 转发的传感器名称
     */
    @ApiModelProperty(value = "转发的传感器名称", required = false, example = "")
    private String forwardSensorName;

    /**
     * 转发数值
     */
    @ApiModelProperty(value = "转发数值", required = false, example = "")
    private String forwardValue;

    /**
     * 警戒值 高值
     */
    @ApiModelProperty(value = "警戒值 高值", required = false, example = "")
    private String heightValue;

    /**
     * 警戒值 低值
     */
    @ApiModelProperty(value = "警戒值 低值", required = false, example = "")
    private String belowValue;

    /**
     * 是否转发
     */
    @ApiModelProperty(value = "是否转发", required = false, example = "")
    private String isForward;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id", required = false, example = "")
    private Long userId;
    /**
     * 接收报警消息联系人
     */
    @ApiModelProperty(value = "接收报警消息联系人", required = false, example = "")
    private String contacts;
    /**
     * 时间临界点单位分
     */
    @ApiModelProperty(value = "时间临界点单位分", required = false, example = "")
    private String duration;

    /**
     * 触发器添加的时间
     */
    @ApiModelProperty(value = "触发器添加的时间", required = false, example = "")
    private String addDate;

    /**
     * 报警消息类型
     */
    @ApiModelProperty(value = "报警时间", required = false, example = "")
    private String alarmDate;

    /**
     * 报警消息类型
     */
    @ApiModelProperty(value = "报警消息类型", required = false, example = "")
    private String targetModel;


}
