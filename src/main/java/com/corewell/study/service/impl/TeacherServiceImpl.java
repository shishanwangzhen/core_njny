package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corewell.study.config.UserRequest;
import com.corewell.study.dao.TeacherDao;
import com.corewell.study.domain.Teacher;
import com.corewell.study.domain.response.AccountDo;
import com.corewell.study.domain.response.TeacherDTO;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.service.TeacherService;
import com.corewell.study.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
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
 * @Date: 2022/11/02/16:06
 * @Description:
 */
@Service("TeacherService")
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public ResultMsg selectTeacher(String account, String password) {
        log.info("selectTeacher:  account:  " + JSON.toJSONString(account));
        AccountDo accountDo = teacherDao.selectTeacher(account);
        if (accountDo == null) {
            return new ResultMsg(ResultStatusCode.NO_USER);
        }
        if (!password.equals(accountDo.getPassword())) {
            return new ResultMsg(ResultStatusCode.LOGIN_ERR);
        }
        //准备存放在JWT中的自定义数据
        Map<String, Object> info = new HashMap<>(16);
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
    public ResultMsg findTeacher() {
        log.info("findTeacher: :  ");
        List<TeacherDTO> teacherList = teacherDao.findTeacher();
        return ResultMsg.success(teacherList);
    }

    @Override
    public ResultMsg findTeacherById(Long id) {
        log.info("findTeacherById:  id:  " + JSON.toJSONString(id));
        String token = UserRequest.getCurrentToken();
        Map<String, Object> map = JwtUtil.getInfo(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        AccountDo accountDo = jsonObject.toJavaObject(AccountDo.class);
        if ((id.equals(accountDo.getId()))) {
            Teacher teacher = teacherDao.findTeacherById(id);
            return ResultMsg.success(teacher);
        }else {
            return ResultMsg.error();
        }

    }

    @Override
    public ResultMsg updateTeacher(Teacher teacher) {
        log.info("updateTeacher:  teacher:  " + JSON.toJSONString(teacher));
        String token = UserRequest.getCurrentToken();
        Map<String, Object> map = JwtUtil.getInfo(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        AccountDo accountDo = jsonObject.toJavaObject(AccountDo.class);
        if ((teacher.getId()).equals(accountDo.getId())) {
            teacher.setUpdateTime(new Date());
            int result = teacherDao.updateTeacher(teacher);
            if (result == 1) {
                return ResultMsg.success();
            } else {
                return ResultMsg.error();
            }
        } else {
            return ResultMsg.error();
        }
    }
}
