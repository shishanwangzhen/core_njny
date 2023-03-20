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
@ApiModel("设置参数入参")
public class SetParamsReq {
    /**
     * 设备Id
     */
    @ApiModelProperty(value = "设备Id", required = true, example = "")
    private Long deviceId;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值", required = true, example = "\"{\\\"TS\\\":\\\"30.0\\\",\\\"TP\\\":\\\"45.0\\\",\\\"HS\\\":\\\"45.0\\\",\\\"HP\\\":\\\"55.0\\\"}\"")
    private String params;

    /**
     * 是否写入设备，true 是 false 否
     */
    @ApiModelProperty(value = "是否写入设备，true 是 false 否", required = false, example = "false")
    private String isWrite;
}
