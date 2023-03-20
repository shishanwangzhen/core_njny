package com.corewell.study.domain.request;


import com.corewell.study.domain.result.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Administrator
 */
@Data
@ApiModel("分页学生查询条件")
@AllArgsConstructor
@NoArgsConstructor
public class StudentParam {

    /**
     * 分页
     */
    @ApiModelProperty(value = "分页", required = true, example = "")
    private PageParam pageParam;

    /**
     * 状态（0.待审核，1.审核通过，2.审核未通过）
     */
    @ApiModelProperty(value = "状态（0.待审核，1.审核通过，2.审核未通过,3.已删除）", required = true, example = "0")
    private String status;

    /**
     * 老师id
     */
    @ApiModelProperty(value = "老师id", required = true, example = "1")
    private Long teacherId;

}
