package com.corewell.study.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设备还参")
public class DeviceDTO {
    /**
     * 设备创建时间
     */
    @ApiModelProperty(value = "设备创建时间", required = false, example = "")
    private Date createDate;
    /**
     * 掉线延时
     */
    @ApiModelProperty(value = "掉线延时", required = false, example = "")
    private String defaultTimescale;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", required = false, example = "")
    private String deviceName;
    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号", required = false, example = "")
    private String deviceNo;
    /**
     * 设备id（deviceId）
     */
    @ApiModelProperty(value = "设备id（deviceId）", required = false, example = "")
    private Long id;
    /**
     * 设备图标
     */
    @ApiModelProperty(value = "设备图标", required = false, example = "")
    private String iocUrl;
    /**
     * 是否报警 0 未报警 1报警
     */
    @ApiModelProperty(value = "是否报警 0 未报警 1报警", required = false, example = "")
    private String isAlarms;
    /**
     * 是否删除 0 否 1 是 2禁用
     */
    @ApiModelProperty(value = "是否删除 0 否 1 是 2禁用", required = false, example = "")
    private Long isDelete;
    /**
     * 是否在线 0 否 1 是
     */
    @ApiModelProperty(value = "是否在线 0 否 1 是", required = false, example = "")
    private Long isLine;

    private Long groupId;

    private String iotDeviceId;


    private String lat;
    /**
     * 协议类型
     */
    @ApiModelProperty(value = "协议类型", required = false, example = "")
    private String linktype;

    private String lng;

    private Long parentUser;

    private String productId;

    private String productType;

    private String protocolLabel;
    private String remark;
    /**
     * 传感器集合
     */
    @ApiModelProperty(value = "传感器集合", required = false, example = "")
    private List<SensorDTO> sensorsList;
    private String timeZone;

    private String userId;

    private String userName;
}
