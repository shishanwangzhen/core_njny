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
@ApiModel("设备开关下行控制入参")
public class DeviceSwitcherParam {

    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号", required = true, example = "")
    private String deviceNo;


    /**
     * 发送的开关值。0表示关 1表示开
     */
    @ApiModelProperty(value = "发送的开关值。0表示关 1表示开", required = true, example = "")
    private String switcher;

    /**
     * 对应的传感器Id
     */
    @ApiModelProperty(value = "对应的传感器Id", required = true, example = "")
    private Long sensorId;

}
