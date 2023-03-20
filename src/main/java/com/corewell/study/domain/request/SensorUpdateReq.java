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
@ApiModel("传感器批量更新量程参数")
public class SensorUpdateReq {

    /**
     * 传感器批量更新量程
     */
    @ApiModelProperty(value = "传感器批量更新量程", required = false, example = "")
    private List<SensorUpdateParam> sensorUpdateParams;

}
