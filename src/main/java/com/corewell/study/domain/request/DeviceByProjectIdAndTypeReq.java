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
@ApiModel("学生端查询设备设备查询条件")
public class DeviceByProjectIdAndTypeReq {


    /**
     * 设备类型（1.采集设备，2.控制设备，4.视频设备，5.虚拟设备）
     */
    @ApiModelProperty(value = "设备类型（1.采集设备，2.控制设备，4.视频设备，5.虚拟设备）", required = true, example = "")
    private String type;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", required = true, example = "")
    private Long projectId;

}
