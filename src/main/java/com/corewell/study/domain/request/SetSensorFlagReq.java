package com.corewell.study.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置mqtt,tp500,coap协议读写标识入参")
public class SetSensorFlagReq {
    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = true, example = "")
    private Long deviceId;
    /**
     * 协议类型 取值范围 mqtt,tp500,coap
     */
    @ApiModelProperty(value = "协议类型 取值范围 mqtt,tp500,coap", required = true, example = "mqtt")
    private String linktype;

    /**
     * 需要设置的读写指令集合
     */
    @ApiModelProperty(value = "需要设置的读写指令集合", required = true, example = "")
    private List<FlagSensors> sensorList;

}
