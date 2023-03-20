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
@ApiModel("用户登录参数")
@AllArgsConstructor
@NoArgsConstructor
public class AccountReq {
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", required = true, example = "admin")
    @NotNull(message = "账号不能为空")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotNull(message = "密码不能为空")
    private String password;

}
