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
@ApiModel("用户登录")
@AllArgsConstructor
@NoArgsConstructor
public class AccountDo {
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
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = false, example = "")
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = false, example = "")
    private String password;

    /**
     * token
     */
    @ApiModelProperty(value = "token", required = false, example = "")
    private String token;


}
