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
@ApiModel("获取参数还参")
public class ParamsDTO {

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值", required = true, example = "\"{\\\"TS\\\":\\\"30.0\\\",\\\"TP\\\":\\\"45.0\\\",\\\"HS\\\":\\\"45.0\\\",\\\"HP\\\":\\\"55.0\\\"}\"")
    private String params;

}
