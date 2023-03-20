package com.corewell.study.domain.response;

import com.corewell.study.domain.request.Modbus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@ApiModel("modbus")
public class ModbusDTO extends ResultDTO {
    /**
     * 需要设置的读写指令集合
     */
    @ApiModelProperty(value = "需要设置的读写指令集合", required = true, example = "modbus")
    private List<Modbus> modbusList;

}
