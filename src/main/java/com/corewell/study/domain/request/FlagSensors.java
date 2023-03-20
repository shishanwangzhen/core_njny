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
@ApiModel("对应的传感器Id的flag")
public class FlagSensors {
    /**
     * 对应的传感器Id
     */
    @ApiModelProperty(value = "对应的传感器Id", required = true, example = "")
    private Long sensorId;
    /**
     * 读写标识符
     */
    @ApiModelProperty(value = "读写标识符", required = true, example = "data1")
    private String flag;


}
