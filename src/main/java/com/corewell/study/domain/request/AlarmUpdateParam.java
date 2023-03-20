package com.corewell.study.domain.request;

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
@ApiModel("修改触发器入参")
public class AlarmUpdateParam {

    /**
     * 触发器Id
     */
    @ApiModelProperty(value = "触发器Id", required = true, example = "")
    private Long id;

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = false, example = "")
    private Long deviceId;
    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = false, example = "")
    private Long sensorId;

    /**
     * 触发器类型 取值范围 1-15 分别表示,1数值高于X、2数值低于Y、3数值高于X低于Y、4数值高于X低于Y超过M分钟、5数值高于X报警低于Y恢复、6数值低于X报警高于Y恢复、7数值在X和Y之间、8数值超过M分钟高于X、9数值超过M分钟低于Y、10传感器未连接、11超过M分钟未连接、12开关ON、13开关OFF、14数值等于X、15数值不等于X 必选参数
     */
    @ApiModelProperty(value = "触发器类型 取值范围 1-15 分别表示,1数值高于X、2数值低于Y、3数值高于X低于Y、4数值高于X低于Y超过M分钟、5数值高于X报警低于Y恢复、6数值低于X报警高于Y恢复、7数值在X和Y之间、8数值超过M分钟高于X、9数值超过M分钟低于Y、10传感器未连接、11超过M分钟未连接、12开关ON、13开关OFF、14数值等于X、15数值不等于X 必选参数", required = false, example = "")
    private Long alarmType;

    /**
     * 警戒值高值（X值） 可选参数，根据alarmType触发器类型填写
     */
    @ApiModelProperty(value = "警戒值高值（X值） 可选参数，根据alarmType触发器类型填写", required = false, example = "")
    private String heightValue;

    /**
     * 警戒值低值（Y值）可选参数，根据alarmType触发器类型填写
     */
    @ApiModelProperty(value = "警戒值低值（Y值）可选参数，根据alarmType触发器类型填写", required = false, example = "")
    private String belowValue;

    /**
     * 时间临界点（M值）单位分钟可选参数，根据alarmType触发器类型填写
     */
    @ApiModelProperty(value = "时间临界点（M值）单位分钟可选参数，根据alarmType触发器类型填写", required = false, example = "")
    private String duration;

    /**
     * 是否转发 取值范围 0-1 0表示不转发，1表示转发 必选参数
     */
    @ApiModelProperty(value = "是否转发 取值范围 0-1 0表示不转发，1表示转发 必选参数", required = false, example = "")
    private String isForward;

    /**
     * 转发时接收数据的设备Id 可选参数，根据isForward是否转发填写
     */
    @ApiModelProperty(value = "转发时接收数据的设备Id 可选参数，根据isForward是否转发填写", required = false, example = "")
    private String forwardDeviceId;


    /**
     * 转发接收数据的传感器Id 可选参数，根据isForward是否转发填写
     */
    @ApiModelProperty(value = "转发接收数据的传感器Id 可选参数，根据isForward是否转发填写\n", required = false, example = "")
    private String forwardSensorId;

    /**
     * 转发的数据类型 取值范围 1-2 1表示字符串 2表示十六进制，可选参数，根据isForward是否转发填写
     */
    @ApiModelProperty(value = "转发的数据类型 取值范围 1-2 1表示字符串 2表示十六进制，可选参数，根据isForward是否转发填写", required = false, example = "")
    private String forwardDataType;

    /**
     * 转发接收数据的设备连接协议，可选参数，根据isForward是否转发填写
     */
    @ApiModelProperty(value = "转发接收数据的设备连接协议，可选参数，根据isForward是否转发填写", required = false, example = "")
    private String forwardLinkType;

    /**
     * 转发数值
     */
    @ApiModelProperty(value = "转发数值", required = false, example = "")
    private String forwardValue;

}
