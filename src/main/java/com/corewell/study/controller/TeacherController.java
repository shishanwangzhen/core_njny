package com.corewell.study.controller;

import com.corewell.study.domain.Teacher;
import com.corewell.study.domain.response.TeacherDTO;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */

@RestController
@RequestMapping("/teacher")
@Api(tags = "老师")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    @ApiOperation(value = "查询老师", response = TeacherDTO.class)
    @PostMapping("/findTeacher")
    public ResultMsg findTeacher() {
        ResultMsg resultMsg = teacherService.findTeacher();
        return resultMsg;

    }
    @ApiOperation(value = "查询老师详情", response = TeacherDTO.class)
    @PostMapping("/findTeacherById")
    @ApiImplicitParam(value = "主键id", name = "1", required = true)
    public ResultMsg findTeacherById(Long id) {
        ResultMsg resultMsg = teacherService.findTeacherById(id);
        return resultMsg;

    }

    @ApiOperation(value = "修改老师")
    @PostMapping("/updateTeacher")
    public ResultMsg updateTeacher(@RequestBody Teacher teacher) {
        ResultMsg resultMsg = teacherService.updateTeacher(teacher);
        return resultMsg;

    }

}
