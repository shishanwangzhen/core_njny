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
@ApiModel("采集控制设备查询条件")
public class ControllerAndCollectionDeviceReq {

    /**
     * 设备类型（1.采集设备，2.控制设备）
     */
    @ApiModelProperty(value = "设备类型（1.采集设备，2.控制设备）", required = true, example = "")
    private String type;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", required = false, example = "")
    private Long projectId;

    /**
     * 项目组id
     */
    @ApiModelProperty(value = "项目组id", required = false, example = "")
    private Long groupId;

}
