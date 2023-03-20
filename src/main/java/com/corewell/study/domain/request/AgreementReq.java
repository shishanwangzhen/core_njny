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
@ApiModel("协议查询条件")
public class AgreementReq {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;
    /**
     * 协议名称
     */
    @ApiModelProperty(value = "协议名称", required = false, example = "")
    private String name;
    /**
     * 协议编号
     */
    @ApiModelProperty(value = "协议编号", required = false, example = "")
    private String number;
    /**
     * 协议类型
     */
    @ApiModelProperty(value = "协议类型", required = false, example = "")
    private String type;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false, example = "")
    private String remarks;
    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", required = false, example = "")
    private Long creatorId;

}
