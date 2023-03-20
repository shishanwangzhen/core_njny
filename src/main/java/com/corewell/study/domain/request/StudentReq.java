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
@ApiModel("学生用户查询条件")
@AllArgsConstructor
@NoArgsConstructor
public class StudentReq {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", required = false, example = "")
    private String account;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", required = false, example = "0")
    private String role;
    /**
     * 状态（0.待审核，1.审核通过，2.审核未通过）
     */
    @ApiModelProperty(value = "状态（0.待审核，1.审核通过，2.审核未通过,3.已删除）", required = false, example = "0")
    private String status;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", required = false, example = "1")
    private Long projectId;
    /**
     * 项目组id
     */
    @ApiModelProperty(value = "项目组id", required = false, example = "1")
    private Long groupId;
    /**
     * 老师id
     */
    @ApiModelProperty(value = "老师id", required = false, example = "1")
    private Long teacherId;

}
