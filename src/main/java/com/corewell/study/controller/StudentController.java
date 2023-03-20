package com.corewell.study.controller;

import com.corewell.study.domain.Student;
import com.corewell.study.domain.request.StudentParam;
import com.corewell.study.domain.request.StudentReq;
import com.corewell.study.domain.request.StudentStatusReqParam;
import com.corewell.study.domain.response.StudentDTO;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.StudentService;
import com.corewell.study.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */
@RestController
@RequestMapping("/core/student")
@Api(tags = "学生")
public class StudentController {
    @Resource
    private StudentService studentService;

    @ApiOperation(value = "查询学生", response = Student.class)
    @PostMapping("/selectStudent")
    public ResultMsg selectStudent(@RequestBody StudentReq studentReq) {
        ResultMsg resultMsg = studentService.findStudent(studentReq);
        return resultMsg;

    }

    @ApiOperation(value = "分页查询学生", response = Student.class)
    @PostMapping("/findStudentByPage")
    public ResultMsg findStudentByPage(@RequestBody StudentParam studentParam) {
        PageUtil.setPageParams(studentParam.getPageParam());
        List<Student> studentList = studentService.findStudentByPage(studentParam);
        PageInfo<Student> pageInfo = new PageInfo(studentList);
        return ResultMsg.success(pageInfo);

    }

    @ApiOperation(value = "查询组成员", response = Student.class)
    @PostMapping("/selectStudentGroup")
    @ApiImplicitParam(value = "项目组主键id", name = "1", required = true)
    public ResultMsg selectStudentGroup(Long id) {
        ResultMsg resultMsg = studentService.selectStudentGroup(id);
        return resultMsg;

    }

    @ApiOperation(value = "查询学生信息", response = StudentDTO.class)
    @PostMapping("/selectStudentById")
    @ApiImplicitParam(value = "主键id", name = "1", required = true)
    public ResultMsg selectStudentById(Long id) {
        ResultMsg resultMsg = studentService.selectStudentById(id);
        return resultMsg;

    }

    @ApiOperation("学生信息修改")
    @PostMapping("/updateStudent")
    public ResultMsg updateStudent(@RequestBody Student student) {
        ResultMsg resultMsg = studentService.updateStudent(student);
        return resultMsg;

    }

    @ApiOperation("删除学生信息")
    @PostMapping("/deleteStudentById")
    @ApiImplicitParam(value = "主键id", name = "1", required = true)
    public ResultMsg deleteStudentById(Long id) {
        ResultMsg resultMsg = studentService.deleteStudentById(id);
        return resultMsg;

    }

    @ApiOperation("批量修改学生状态")
    @PostMapping("/updateStudentStatus")
    public ResultMsg updateStudentStatus(@RequestBody StudentStatusReqParam studentStatusReqParam) {
        ResultMsg resultMsg = studentService.updateStudentStatus(studentStatusReqParam);
        return resultMsg;

    }

}
