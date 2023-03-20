package com.corewell.study.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/09/11:58
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("tcp,udp协议标签还参")
public class ProtocolLabelDTO extends ResultDTO {

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", required = false, example = "")
    private String deviceName;
    /**
     * 设备Id
     */
    @ApiModelProperty(value = "设备Id", required = false, example = "")
    private String deviceId;
    /**
     * 当前协议对应的解析标签
     */
    @ApiModelProperty(value = "当前协议对应的解析标签", required = false, example = "")
    private String protocolLabel;

}
