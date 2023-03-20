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
@ApiModel("传感器量程入参")
public class SensorUpdateParam {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true, example = "")
    private Long id;

    /**
     * 最大值
     */
    @ApiModelProperty(value = "最大值", required = true, example = "")
    private Double maximum;
    /**
     * 最小值
     */
    @ApiModelProperty(value = "最小值", required = true, example = "")
    private Double minimum;


}
