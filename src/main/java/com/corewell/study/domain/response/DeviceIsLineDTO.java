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
@ApiModel("设备在线查询还参")
public class DeviceIsLineDTO {

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = false, example = "")
    private Long deviceId;

    /**
     * 是否在线 0 否 1 是
     */
    @ApiModelProperty(value = "是否在线 0 否 1 是", required = false, example = "")
    private Long isLine;

}
