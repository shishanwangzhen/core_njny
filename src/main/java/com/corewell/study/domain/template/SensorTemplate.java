package com.corewell.study.domain.template;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@ApiModel("传感器数据导出模板")
@AllArgsConstructor
@NoArgsConstructor
@HeadRowHeight(value = 20)
public class SensorTemplate {
/*
    @ApiModelProperty("传感器名称")
    @ExcelProperty(value = "传感器名称", index = 0)
    @ColumnWidth(value = 15)
    private String SensorName;*/

    @ApiModelProperty("采集时间")
    @ExcelProperty(value = "采集时间", index = 1)
    @ColumnWidth(value = 15)
    private String time;

    @ApiModelProperty("采集数据值")
    @ExcelProperty(value = "采集数据值", index = 2)
    @ColumnWidth(value = 15)
    private String value;
/*
    @ApiModelProperty("手机号码")
    @ExcelProperty(value = "手机号码", index = 3)
    @ColumnWidth(value = 20)
    private String phone;

    @ApiModelProperty("角色")
    @ExcelProperty(value = "角色", index = 4)
    @ColumnWidth(value = 15)
    private String roleName;

    @ApiModelProperty("单位名称")
    @ExcelProperty(value = "单位名称", index = 5)
    @ColumnWidth(value = 15)
    private String departmentName;*/

//    @ApiModelProperty("人员涉密等级")
//    @ExcelProperty(value = "人员涉密等级", index = 6)
//    @ColumnWidth(value = 15)
//    private String level;

}
