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
@ApiModel("设备数据下行入参")
public class DeviceWriteParam {

    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号", required = true, example = "")
    private String deviceNo;


    /**
     * 发送的数据 必选
     */
    @ApiModelProperty(value = "发送的数据 必选", required = true, example = "0000")
    private String value;

    /**
     * 对应的传感器Id
     */
    @ApiModelProperty(value = "对应的传感器Id", required = true, example = "")
    private Long sensorId;

}
