package com.corewell.study.domain.request;

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
@ApiModel("modbus协议读写指令设置入参")
public class ModbusReq {
    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = true, example = "")
    private Long deviceId;
    /**
     * 协议类型 取值范围 modbus/mdtcp 必选参数
     */
    @ApiModelProperty(value = "设备名称", required = true, example = "modbus")
    private String linktype;

    /**
     * 需要设置的读写指令集合
     */
    @ApiModelProperty(value = "需要设置的读写指令集合", required = true, example = "")
    private List<Modbus> modbusList;

}
