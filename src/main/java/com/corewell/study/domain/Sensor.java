package com.corewell.study.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("传感器")
public class Sensor {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;
    /**
     * 传感器sensorId
     */
    @ApiModelProperty(value = "传感器id", required = false, example = "")
    private Long sensorId;
    /**
     * 传感器名称
     */
    @ApiModelProperty(value = "传感器名称", required = true, example = "")
    private String sensorName;
    /**
     * 传感器类型，取值范围 1-8，1数值型，2开关型可操作，5开关型不可操作，3定位型，4图片型，6档位型，7视频，8字符串 必填参数
     */
    @ApiModelProperty(value = "传感器类型，取值范围 1-8，1数值型，2开关型可操作，5开关型不可操作，3定位型，4图片型，6档位型，7视频，8字符串 必填参数", required = false, example = "")
    private Long sensorType;
    /**
     * 单位，传感器类型为1（值类型时必填，其它可选）
     */
    @ApiModelProperty(value = "单位，传感器类型为1（值类型时必填，其它可选）", required = false, example = "")
    private String unit;
    /**
     * 状态（0.删除，1.正常）
     */
    @ApiModelProperty(value = "状态（0.删除，1.正常）", required = false, example = "")
    private Long ordernum;
    /**
     * 小数位传感器类型为1（值类型时必填，其它可选）
     */
    @ApiModelProperty(value = "小数位传感器类型为1（值类型时必填，其它可选）", required = false, example = "")
    private String decimalPlacse;
    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = false, example = "")
    private Long deviceId;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", required = true, example = "")
    private String deviceName;
    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", required = true, example = "")
    private Long creatorId;

    /**
     * 最大值
     */
    @ApiModelProperty(value = "最大值", required = false, example = "")
    private Double maximum;
    /**
     * 最小值
     */
    @ApiModelProperty(value = "最小值", required = false, example = "")
    private Double minimum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false, example = "")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = false, example = "")
    private Date updateTime;


}
