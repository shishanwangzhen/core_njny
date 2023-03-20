package com.corewell.study.domain.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 分页参数封装类，其他搜索model都可以extends自PageParam
 * @author: yanglimou
 * @time: 2020/5/14 11:10
 */
@Data
@ApiModel("分页参数")
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    @ApiModelProperty(value = "每页显示", example = "10")
    private int pageSize = 10;
    @ApiModelProperty(value = "当前页", example = "1")
    private int pageNum = 1;
    @ApiModelProperty(value = "排序", example = " ")
    private String orderBy = "";
}
