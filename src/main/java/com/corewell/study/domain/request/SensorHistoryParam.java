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
@ApiModel("获取设备传感器历史数据参数")
public class SensorHistoryParam {

    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = true, example = "")
    private Long sensorId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false, example = "2020-05-08 00:00:00")
    private String startDate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false, example = "")
    private String endDate;

}
