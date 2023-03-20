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
@ApiModel("传感器参数")
public class SensorParam {
    /**
     * 需要修改的传感器Id，可选参数，修改为必选，不传则为新增
     */
    @ApiModelProperty(value = "需要修改的传感器Id，可选参数，修改为必选，不传则为新增", required = false, example = "")
    private String sensorId;
    /**
     * 传感器名称
     */
    @ApiModelProperty(value = "传感器名称", required = true, example = "")
    private String sensorName;
    /**
     * 传感器类型，取值范围 1-8，1数值型，2开关型可操作，5开关型不可操作，3定位型，4图片型，6档位型，7视频，8字符串 必填参数
     */
    @ApiModelProperty(value = "传感器类型，取值范围 1-8，1数值型，2开关型可操作，5开关型不可操作，3定位型，4图片型，6档位型，7视频，8字符串 必填参数", required = true, example = "")
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
    private Long decimal;


}
