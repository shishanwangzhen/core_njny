package com.corewell.study.domain.request;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@ApiModel("批量学生审核入参")
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatusReqParam {

    private List<StudentStatusReq> studentStatusReqs;

}
