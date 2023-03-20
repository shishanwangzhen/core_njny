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
@ApiModel("还参")
public class ResultDTO {
    /**
     *
     */
    @ApiModelProperty(value = "", required = false, example = "")
    private String msg;

    /**
     * 00 表示请求成功 01 表示请求失败
     */
    @ApiModelProperty(value = "00 表示请求成功 01 表示请求失败", required = false, example = "00")
    private String flag;

}
