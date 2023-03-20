package com.corewell.study.dao;

import com.corewell.study.domain.Student;
import com.corewell.study.domain.request.StudentParam;
import com.corewell.study.domain.request.StudentReq;
import com.corewell.study.domain.request.StudentStatusReq;
import com.corewell.study.domain.response.AccountDo;
import com.corewell.study.domain.response.StudentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
@Mapper
public interface StudentDao {
    /**
     * 查询账号
     *
     * @param account
     * @return
     */
    AccountDo selectStudentByAccount(String account);

    /**
     * 查询学生
     *
     * @param studentReq
     * @return
     */
    List<Student> findStudent(StudentReq studentReq);


    /**
     * 分页查询学生
     *
     * @param studentParam
     * @return
     */
    List<Student> findStudentByPage(StudentParam studentParam);

    /**
     * 查询学生
     *
     * @param groupId
     * @return
     */
    List<Student> findStudentByGroupId(Long groupId);

    /**
     * 查询学生
     *
     * @param account
     * @return
     */
    Student findStudentByAccount(String account);

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    StudentDTO selectStudentById(Long id);

    /**
     * 注册新增学生
     *
     * @param student
     * @return
     */
    int insertStudent(Student student);

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除学生信息
     *
     * @param id
     * @return
     */
    int deleteStudentById(@Param("id") Long id);

    /**
     * 审核学生注册申请
     *
     * @param studentStatusReq
     * @return
     */
    int updateStudentStatus(StudentStatusReq studentStatusReq);

    /**
     * 删除学生项目组
     *
     * @param id
     * @return
     */
    int updateGroupStudent(Long id);

    /**
     * 删除项目组所有学生
     *
     * @param groupId
     * @return
     */
    int updateGroupStudentByGroupId(Long groupId);

    /**
     * 删除项目所有学生
     *
     * @param projectId
     * @return
     */
    int updateProjectStudentByProjectId(Long projectId);

    /**
     * 删除项目
     *
     * @param creatorId
     * @return
     */
    int updateProjectStatusByTeacherId(Long creatorId);


}
