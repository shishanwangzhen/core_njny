package com.corewell.study.controller;

import com.corewell.study.domain.Student;
import com.corewell.study.domain.request.AccountReq;
import com.corewell.study.domain.response.AccountDo;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.service.StudentService;
import com.corewell.study.service.TeacherService;
import com.corewell.study.utils.ValidateCore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录")
public class LoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "教师登录", response = AccountDo.class)
    @PostMapping("/loginTeacher")
    public ResultMsg loginTeacher(@RequestBody AccountReq accountReq) {
        String account = accountReq.getAccount();
        String password = accountReq.getPassword();
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return new ResultMsg(ResultStatusCode.USERNAME_PASSWORD_NULL);
        }
        ResultMsg resultMsg = teacherService.selectTeacher(account, password);
        return resultMsg;

    }


    @ApiOperation(value = "学生登录", response = AccountDo.class)
    @PostMapping("/loginStudent")
    public ResultMsg loginStudent(@RequestBody AccountReq accountReq) {
        String account = accountReq.getAccount();
        String password = accountReq.getPassword();
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return new ResultMsg(ResultStatusCode.USERNAME_PASSWORD_NULL);
        }
        //TODO
        //上线后账号校验放开
        if (!ValidateCore.verifyAccount(account)) {
            return new ResultMsg(ResultStatusCode.ILLEGAL_ACCOUNT);
        }
        ResultMsg resultMsg = studentService.selectStudentByAccount(account, password);
        return resultMsg;

    }

    @ApiOperation("学生注册")
    @PostMapping("/registerStudent")
    public ResultMsg registerStudent(@RequestBody Student student) {
        String account = student.getAccount();
        String password = student.getPassword();
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            return new ResultMsg(ResultStatusCode.USERNAME_PASSWORD_NULL);
        }
        //TODO
        //上线后账号校验放开
        if (!ValidateCore.verifyAccount(account)) {
            return new ResultMsg(ResultStatusCode.ILLEGAL_ACCOUNT);
        }
        ResultMsg resultMsg = studentService.insertStudent(student);
        return resultMsg;

    }

    @ApiOperation("退出登录")
    @PostMapping("/loginOut")
    public ResultMsg loginOut() {

        return ResultMsg.success();

    }

}
