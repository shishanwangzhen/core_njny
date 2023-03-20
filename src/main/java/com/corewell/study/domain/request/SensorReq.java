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
@ApiModel("传感器查询条件")
public class SensorReq {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;
    /**
     * 传感器sensorId
     */
    @ApiModelProperty(value = "传感器id", required = false, example = "")
    private Long sensorId;


    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = false, example = "")
    private Long deviceId;

}
