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
@ApiModel("传感器数据")
public class SensorDataDTO {

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = false, example = "")
    private String addTime;

    /**
     * 定位纬度
     */
    @ApiModelProperty(value = "定位纬度", required = false, example = "")
    private String lat;
    /**
     * 定位经度
     */
    @ApiModelProperty(value = "定位经度", required = false, example = "")
    private String lng;

    /**
     * 开关
     */
    @ApiModelProperty(value = "开关", required = false, example = "")
    private String switcher;

    /**
     * 数值
     */
    @ApiModelProperty(value = "数值", required = false, example = "")
    private String val;

}
