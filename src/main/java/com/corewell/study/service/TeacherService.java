package com.corewell.study.service;

import com.corewell.study.domain.Teacher;
import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface TeacherService {
    /**
     * 查询账号
     *
     * @param account
     * @param password
     * @return
     */
    ResultMsg selectTeacher(String account, String password);

    /**
     * 查询老师
     *
     * @return
     */
    ResultMsg findTeacher();

    /**
     * 查询老师详情
     * @param id
     * @return
     */
    ResultMsg findTeacherById(Long id);


    /**
     * 修改老师
     * @param teacher
     * @return
     */
    ResultMsg updateTeacher(Teacher teacher);
}
