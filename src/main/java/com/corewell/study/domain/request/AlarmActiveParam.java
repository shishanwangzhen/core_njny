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
@ApiModel("启停触发器入参")
public class AlarmActiveParam {

    /**
     * 触发器Id
     */
    @ApiModelProperty(value = "触发器Id", required = true, example = "")
    private Long id;

    /**
     * 启动关闭状态 0 关闭 1激活
     */
    @ApiModelProperty(value = "启动关闭状态:0关闭,1激活", required = true, example = "")
    private String active;

}
