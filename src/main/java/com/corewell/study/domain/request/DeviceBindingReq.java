package com.corewell.study.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("项目配置设备入参")
public class DeviceBindingReq {
    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    /**
     * 设备主键id集合
     */
    @ApiModelProperty(value = "设备主键id集合", required = true, example = "")
    private List<Long> ids;


    /**
     * 绑定状态（0.未绑定项目，1.已绑定项目）
     */
    @ApiModelProperty(value = "绑定状态（0.未绑定项目，1.已绑定项目）", required = true, example = "")
    private String binding;

    /**
     * 项目负责人id
     */
    @ApiModelProperty(value = "项目负责人id", required = true, example = "")
    private Long bindingId;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id", required = true, example = "")
    private Long projectId;


}
