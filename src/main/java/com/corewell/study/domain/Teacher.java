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
@ApiModel("教师")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
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
     * 密码
     */
    @ApiModelProperty(value = "密码", required = false, example = "")
    private String password;
    /**
     * 名字
     */
    @ApiModelProperty(value = "名字", required = false, example = "")
    private String name;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = false, example = "")
    private Date updateTime;

}
