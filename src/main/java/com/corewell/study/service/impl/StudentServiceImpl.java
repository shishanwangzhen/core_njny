package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.corewell.study.annotation.AddLog;
import com.corewell.study.dao.StudentDao;
import com.corewell.study.domain.Student;
import com.corewell.study.domain.request.StudentParam;
import com.corewell.study.domain.request.StudentReq;
import com.corewell.study.domain.request.StudentStatusReq;
import com.corewell.study.domain.request.StudentStatusReqParam;
import com.corewell.study.domain.response.AccountDo;
import com.corewell.study.domain.response.StudentDTO;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.service.StudentService;
import com.corewell.study.utils.JwtUtil;
import com.corewell.study.utils.ValidateCore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/03/13:10
 * @Description:
 */
@Service("StudentService")
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public ResultMsg selectStudentByAccount(String account, String password) {
        log.info("selectStudentByAccount:  account:  " + JSON.toJSONString(account));
        AccountDo accountDo = studentDao.selectStudentByAccount(account);
        if (accountDo == null) {
            return new ResultMsg(ResultStatusCode.NO_USER);
        }
        if (!password.equals(accountDo.getPassword())) {
            return new ResultMsg(ResultStatusCode.LOGIN_ERR);
        }
        //准备存放在IWT中的自定义数据
        Map<String, Object> info = new HashMap<>();
        info.put("id", accountDo.getId());
        info.put("account", account);
        info.put("password", accountDo.getPassword());
        info.put("name", accountDo.getName());
        //生成JWT字符串
        String token = JwtUtil.sign(account, info);
        accountDo.setToken(token);
        return ResultMsg.success(accountDo);
    }

    @Override
    public ResultMsg findStudent(StudentReq studentReq) {
        log.info("findStudent:  studentReq:  " + JSON.toJSONString(studentReq));
        List<Student> studentList = studentDao.findStudent(studentReq);
        return ResultMsg.success(studentList);
    }

    @Override
    public List<Student> findStudentByPage(StudentParam studentParam) {
        log.info("findStudentByPage:  studentParam:  " + JSON.toJSONString(studentParam));
        List<Student> studentList = studentDao.findStudentByPage(studentParam);
        return studentList;
    }

    @Override
    public ResultMsg selectStudentGroup(Long groupId) {
        log.info("selectStudentGroup:  groupId:  " + JSON.toJSONString(groupId));
        List<Student> studentList = studentDao.findStudentByGroupId(groupId);
        return ResultMsg.success(studentList);
    }

    @Override
    public ResultMsg selectStudentById(Long id) {
        log.info("selectStudentById:  id:  " + JSON.toJSONString(id));
        StudentDTO studentDTO = studentDao.selectStudentById(id);
        return ResultMsg.success(studentDTO);

    }


    @Override
    public ResultMsg insertStudent(Student student) {
        log.info("insertStudent:  student:  " + JSON.toJSONString(student));
        String account = student.getAccount();
        Student studentOld = studentDao.findStudentByAccount(account);
        if (studentOld != null) {
            String status = studentOld.getStatus();
            switch (status) {
                case "0":
                    return new ResultMsg(ResultStatusCode.USER_TO_EXAMINE);
                case "1":
                    return new ResultMsg(ResultStatusCode.USER_ISEXIT);
                default:
                    studentDao.deleteStudentById(studentOld.getId());
                    break;
            }
        }
        student.setCreateTime(new Date());
        student.setStatus("0");
        int result = studentDao.insertStudent(student);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    @AddLog(interfaceType = "2", interfaceInfo = "学生信息修改", interfaceName = "updateStudent", dataId = "#{student.id}")
    public ResultMsg updateStudent(Student student) {
        log.info("updateStudent:  student:  " + JSON.toJSONString(student));
        //TODO
        //校验账号，上线放开
        if (StringUtils.isNotBlank(student.getAccount())&& !ValidateCore.verifyAccount(student.getAccount())){
            return new ResultMsg(ResultStatusCode.ILLEGAL_ACCOUNT);
        }
        student.setUpdateTime(new Date());
        int result = studentDao.updateStudent(student);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.UPDATE_FAILED);
    }

    @Override
    @AddLog(interfaceType = "1", interfaceInfo = "删除学生信息", interfaceName = "deleteStudentById", dataId = "#{id}")
    public ResultMsg deleteStudentById(Long id) {
        log.info("deleteStudentById:  id:  " + JSON.toJSONString(id));
        int result = studentDao.deleteStudentById(id);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.DELETE_FAILED);
    }

    @Override
    @AddLog(interfaceType = "2", interfaceInfo = "批量修改学生状态", interfaceName = "updateStudentStatus")
    public ResultMsg updateStudentStatus(StudentStatusReqParam studentStatusReqParam) {
        log.info("批量修改学生状态updateStudentStatus:  studentStatusReqParam:  " + JSON.toJSONString(studentStatusReqParam));
        List<StudentStatusReq> studentStatusReqs = studentStatusReqParam.getStudentStatusReqs();
        try {
            for (StudentStatusReq studentStatusReq : studentStatusReqs) {
                studentDao.updateStudentStatus(studentStatusReq);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.error();
        }
        return ResultMsg.success();
    }

}
