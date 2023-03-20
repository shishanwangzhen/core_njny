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
@ApiModel("项目组")
public class Group {
    /**
     * 主键id,修改操作必选
     */
    @ApiModelProperty(value = "主键id,修改操作必选", required = true, example = "")
    private Long id;
    /**
     * 项目组编号
     */
    @ApiModelProperty(value = "项目组编号", required = false, example = "")
    private String number;
    /**
     * 项目组名称
     */
    @ApiModelProperty(value = "项目组名称", required = false, example = "")
    private String name;
    /**
     * 项目组描述
     */
    @ApiModelProperty(value = "项目组描述", required = false, example = "")
    private String info;
    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", required = false, example = "")
    private Long creatorId;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", required = false, example = "")
    private Long projectId;
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
