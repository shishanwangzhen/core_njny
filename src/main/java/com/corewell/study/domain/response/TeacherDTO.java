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
@ApiModel("查询教师还参")
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;
    /**
     * 名字
     */
    @ApiModelProperty(value = "名字", required = false, example = "")
    private String name;

}
