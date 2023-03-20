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
@ApiModel("传感器数据上报参数")
public class SendDataPointParam {

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = false, example = "")
    private String addTime;

    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号", required = true, example = "")
    private String deviceNo;

    /**
     * 上报的数据集合
     */
    @ApiModelProperty(value = "上报的数据集合", required = true, example = "")
    private List<SensorDatas> sensorDatas;

}
