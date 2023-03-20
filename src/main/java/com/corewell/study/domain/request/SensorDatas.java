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
@ApiModel("传感器数据")
public class SensorDatas {

    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = true, example = "")
    private Long sensorId;

    /**
     * 定位型纬度
     */
    @ApiModelProperty(value = "定位型纬度", required = false, example = "")
    private String lat;
    /**
     * 定位型经度
     */
    @ApiModelProperty(value = "定位型经度", required = false, example = "")
    private String lng;

    /**
     * 开关型数值
     */
    @ApiModelProperty(value = "开关型数值", required = false, example = "1")
    private String switcher;

    /**
     * 数值型数值
     */
    @ApiModelProperty(value = "数值型数值", required = false, example = "10.0")
    private String value;

    /**
     * 字符串数据
     */
    @ApiModelProperty(value = "字符串数据", required = false, example = "字符ABCabc")
    private String string;

}
