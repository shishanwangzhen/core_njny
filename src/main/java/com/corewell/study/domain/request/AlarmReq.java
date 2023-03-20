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
@ApiModel("查询触发器入参")
public class AlarmReq {

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = true, example = "")
    private Long deviceId;
    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = false, example = "")
    private Long sensorId;

}
