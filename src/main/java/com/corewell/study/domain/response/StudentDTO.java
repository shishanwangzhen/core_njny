package com.corewell.study.domain.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@ApiModel("学生信息")
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", required = false, example = "")
    private String role;

    /**
     * 项目组id
     */
    @ApiModelProperty(value = "项目组id", required = false, example = "")
    private String groupId;
    /**
     * 教师id
     */
    @ApiModelProperty(value = "教师id", required = false, example = "")
    private String teacherId;
    /**
     * 项目组id
     */
    @ApiModelProperty(value = "项目组id", required = false, example = "")
    private String projectId;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", required = false, example = "")
    private String projectName;
    /**
     * 项目组名称
     */
    @ApiModelProperty(value = "项目组名称", required = false, example = "")
    private String groupName;


}
