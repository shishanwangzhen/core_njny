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
@ApiModel("日志还参")
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;
    /**
     * 数据id
     */
    @ApiModelProperty(value = "数据id", required = false, example = "")
    private Long dataId;
    /**
     * 用户账号id
     */
    @ApiModelProperty(value = "用户账号id", required = false, example = "")
    private Long accountId;
    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号", required = false, example = "")
    private String account;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", required = false, example = "")
    private String accountName;
    /**
     * 接口类型（1.删除，2.修改）
     */
    @ApiModelProperty(value = "接口类型（1.删除，2.修改）", required = false, example = "")
    private Long interfaceType;
    /**
     * 接口描述
     */
    @ApiModelProperty(value = "接口描述", required = false, example = "")
    private String interfaceInfo;
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称", required = false, example = "")
    private String interfaceName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false, example = "")
    private Date createTime;

}
