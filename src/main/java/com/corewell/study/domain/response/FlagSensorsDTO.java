package com.corewell.study.domain.response;

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
@ApiModel("对应的传感器Id的flag还参")
public class FlagSensorsDTO {
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

    /**
     * 传感器类型
     */
    @ApiModelProperty(value = "传感器类型", required = true, example = "1")
    private Long sensorType;
    /**
     * 传感器名称
     */
    @ApiModelProperty(value = "传感器名称", required = true, example = "传感器-1")
    private String sensorName;


}
