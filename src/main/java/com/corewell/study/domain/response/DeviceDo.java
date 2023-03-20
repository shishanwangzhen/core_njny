package com.corewell.study.domain.response;

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
@ApiModel("设备查询还参")
public class DeviceDo {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = false, example = "")
    private Long id;

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", required = false, example = "")
    private Long deviceId;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称", required = true, example = "")
    private String deviceName;

    /**
     * 设备序列号
     */
    @ApiModelProperty(value = "设备序列号", required = false, example = "")
    private String deviceNo;
    /**
     * 协议类型 "tcp","modbus" "mdtcp","udp","mqtt","tp500","coap","http","nbiot" 注意区分大小写 必填参数
     */
    @ApiModelProperty(value = "协议类型  tcp,modbus,mdtcp,udp,mqtt,tp500coap,http,nbiot 注意区分大小写 必填参数", required = true, example = "")
    private String linkType;
    /**
     * 掉线延时
     */
    @ApiModelProperty(value = "掉线延时", required = false, example = "")
    private Long timescale;
    /**
     * 设备图标
     */
    @ApiModelProperty(value = "设备图标", required = false, example = "")
    private String iocUrl;

    /**
     * 设备类型（1.采集设备，2.控制设备，3.被控设备，4.视频设备，5.虚拟设备）
     */
    @ApiModelProperty(value = "设备类型（1.采集设备，2.控制设备，3.被控设备，4.视频设备，5.虚拟设备）", required = false, example = "")
    private String type;

    /**
     * 绑定状态（0.未绑定项目，1.已绑定项目）
     */
    @ApiModelProperty(value = "绑定状态（0.未绑定项目，1.已绑定项目）", required = false, example = "")
    private String binding;
    /**
     * 项目负责人id
     */
    @ApiModelProperty(value = "项目负责人id", required = false, example = "")
    private Long bindingId;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", required = false, example = "")
    private Long projectId;
    /**
     * 项目组id
     */
    @ApiModelProperty(value = "项目组id", required = false, example = "")
    private Long groupId;
    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", required = true, example = "")
    private Long creatorId;

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

    /**
     * 绑定项目负责人
     */
    @ApiModelProperty(value = "绑定项目负责人", required = false, example = "")
    private String bindingName;

    /**
     * 设备导入人
     */
    @ApiModelProperty(value = "设备导入人", required = false, example = "")
    private String creatorName;
    /**
     * 绑定项目
     */
    @ApiModelProperty(value = "绑定项目", required = false, example = "")
    private String projectName;
    /**
     * 绑定项目组
     */
    @ApiModelProperty(value = "绑定项目组", required = false, example = "")
    private String groupName;
    /**
     * 视频地址
     */
    @ApiModelProperty(value = "视频地址", required = false, example = "")
    private String videoUrl;

    /**
     * 是否在线 0 否 1 是
     */
    @ApiModelProperty(value = "是否在线 0 否 1 是", required = false, example = "")
    private Long isLine;


}
