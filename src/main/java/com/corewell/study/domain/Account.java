package com.corewell.study.domain;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@ApiModel("账户")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String account;
    private String password;
    private String studentId;
    private Date loginTime;
    private String delFlag;
}
