package com.corewell.study.domain.response;

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
@ApiModel("获取mqtt,tp500,coap协议读写标识还参")
public class GetFlagDTO extends ResultDTO {
    /**
     * 获取mqtt,tp500,coap协议读写标识集合
     */
    @ApiModelProperty(value = "获取mqtt,tp500,coap协议读写标识集合", required = false, example = "modbus")
    private List<FlagSensorsDTO> sensorList;


}
