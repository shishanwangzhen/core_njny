package com.corewell.study.domain.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Administrator
 */
@Data
@ApiModel("学生用户审核条件")
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatusReq {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true, example = "")
    @NotNull(message = "主键id不能为空")
    private Long id;

    /**
     * 状态（0.待审核，1.审核通过，2.审核未通过,3.已删除）
     */
    @ApiModelProperty(value = "状态（0.待审核，1.审核通过，2.审核未通过,3.已删除）", required = true, example = "0")
    private String status;

}
