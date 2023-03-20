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
@ApiModel("订阅传感器数据")
public class SensorsDates {

    /**
     * 数据发送时间
     */
    @ApiModelProperty(value = "数据发送时间", required = false, example = "")
    private String times;
    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = false, example = "")
    private Long sensorsId;

    /**
     * 是否报警（0.否，1.是）
     */
    @ApiModelProperty(value = "是否报警（0.否，1.是）", required = false, example = "")
    private String isAlarm;
    /**
     * 是否异常（0.否，1.是）
     */
    @ApiModelProperty(value = "是否异常（0.否，1.是）", required = false, example = "")
    private Long isAbnormal;

    /**
     * 传感器类型
     */
    @ApiModelProperty(value = "传感器类型", required = false, example = "")
    private Long sensorsTypeId;

    /**
     * 是否在线
     */
    @ApiModelProperty(value = "是否在线", required = false, example = "1")
    private Long isLine;

    /**
     * 数值
     */
    @ApiModelProperty(value = "数值", required = false, example = "10.0000")
    private String reVal;

    /**
     * 映射后数值
     */
    @ApiModelProperty(value = "映射后数值", required = false, example = "10.0")
    private String value;

    /**
     * 心跳
     */
    @ApiModelProperty(value = "心跳", required = false, example = "0")
    private Long isHeartbeat;

    /**
     * 开关量数值
     */
    @ApiModelProperty(value = "开关量数值", required = false, example = "1")
    private String switcher;


}
