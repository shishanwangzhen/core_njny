package com.corewell.study.domain.response;


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
@ApiModel("传感器还参")
public class SensorHistoryDTO extends ResultDTO {

    /**
     * 传感器单位
     */
    @ApiModelProperty(value = "传感器单位", required = false, example = "")
    private String unit;

    /**
     * 传感器名称
     */
    @ApiModelProperty(value = "传感器名称", required = false, example = "")
    private String sensorName;

    /**
     * 下一页参数 null 表示已经到了尾页
     */
    @ApiModelProperty(value = "下一页参数 null 表示已经到了尾页", required = false, example = "")
    private String nextPage;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", required = false, example = "")
    private String deviceName;

    /**
     * 传感器类型
     */
    @ApiModelProperty(value = "传感器类型", required = false, example = "")
    private Long sensorTypeId;

    /**
     * 传感器id
     */
    @ApiModelProperty(value = "传感器id", required = false, example = "")
    private Long sensorId;

    /**
     * 传感器历史数据
     */
    @ApiModelProperty(value = "传感器历史数据", required = false, example = "")
    private List<SensorDataDTO> dataList;


}
