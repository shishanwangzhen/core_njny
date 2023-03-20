package com.corewell.study.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("项目")
public class Project {
    /**
     * 主键id,修改必传
     */
    @ApiModelProperty(value = "主键id,修改必传", required = false, example = "")
    private Long id;
    /**
     * 项目编号
     */
    @ApiModelProperty(value = "项目编号", required = false, example = "")
    private String number;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", required = false, example = "")
    private String name;
    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", required = true, example = "")
    private Long creatorId;
    /**
     * 项目描述
     */
    @ApiModelProperty(value = "项目描述", required = false, example = "")
    private String info;
    /**
     * 状态（0.删除，1.正常）
     */
    @ApiModelProperty(value = "状态（0.删除，1.正常）", required = false, example = "")
    private String status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false, example = "")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = false, example = "")
    private Date updateTime;


}
