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
@ApiModel("传感器还参")
public class SensorDTO {
    /**
     * 小数位
     */
    @ApiModelProperty(value = "小数位", required = false, example = "")
    private String decimalPlacse;
    /**
     * 设备Id
     */
    @ApiModelProperty(value = "设备Id", required = false, example = "")
    private Long deviceId;
    /**
     * /matt读写标识
     */
    @ApiModelProperty(value = "/matt读写标识", required = false, example = "")
    private String flag;
    /**
     * 上报数据心跳包时间
     */
    @ApiModelProperty(value = "上报数据心跳包时间", required = false, example = "")
    private String heartbeatDate;

    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = false, example = "")
    private Long id;

    private String iocUrl;

    /**
     * 是否报警 0 否 1是
     */
    @ApiModelProperty(value = "是否报警 0 否 1是", required = false, example = "")
    private Long isAlarms;
    /**
     * 是否删除 0否 1是
     */
    @ApiModelProperty(value = "是否删除 0否 1是", required = false, example = "")
    private Long isDelete;
    /**
     * 是否在线 0 否 1是
     */
    @ApiModelProperty(value = "是否在线 0 否 1是", required = false, example = "")
    private Long isLine;
    /**
     * 是否添加映射 0 否 1是
     */
    @ApiModelProperty(value = "是否添加映射 0 否 1是", required = false, example = "")
    private Long isMapping;

    private String lat;
    private String lng;

    /**
     * 显示排序
     */
    @ApiModelProperty(value = "显示排序", required = false, example = "")
    private Long ordernum;

    /**
     * 映射公式
     */
    @ApiModelProperty(value = "映射公式", required = false, example = "")
    private String sensorMapping;

    /**
     * 传感器名称
     */
    @ApiModelProperty(value = "传感器名称", required = false, example = "")
    private String sensorName;
    /**
     * 传感器类型
     */
    @ApiModelProperty(value = "传感器类型", required = false, example = "")
    private Long sensorTypeId;
    /**
     * 开关数据
     */
    @ApiModelProperty(value = "开关数据", required = false, example = "")
    private String switcher;
    /**
     * tp500读写标识
     */
    @ApiModelProperty(value = "tp500读写标识", required = false, example = "")
    private String tp_flag;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位", required = false, example = "")
    private String unit;
    /**
     * 上报数据更新时间
     */
    @ApiModelProperty(value = "上报数据更新时间", required = false, example = "")
    private String updateDate;
    private Long userId;

    /**
     * 值类型 字符串 档位型数据
     */
    @ApiModelProperty(value = "值类型 字符串 档位型数据", required = false, example = "0.0000")
    private String value;

    /**
     * 视频监控地址
     */
    @ApiModelProperty(value = "视频监控地址", required = false, example = "")
    private String hls;
    /**
     * 视频监控地址
     */
    @ApiModelProperty(value = "视频监控地址", required = false, example = "")
    private String rtmp;
    /**
     * 视频直播地址
     */
    @ApiModelProperty(value = "视频直播地址", required = false, example = "")
    private String live;

    /**
     * 视频token
     */
    @ApiModelProperty(value = "视频token", required = false, example = "")
    private String accessToken;
    /**
     * 视频key
     */
    @ApiModelProperty(value = "视频key", required = false, example = "")
    private String appkey;
    /**
     * 关联视频配置Id
     */
    @ApiModelProperty(value = "关联视频配置Id", required = false, example = "")
    private String openysId;

    /**
     * 视频密钥
     */
    @ApiModelProperty(value = "视频密钥", required = false, example = "")
    private String secret;

    /**
     * 有效时间
     */
    @ApiModelProperty(value = "有效时间", required = false, example = "")
    private String fialtime;

    private String deviceName;
    private String userName;


}
