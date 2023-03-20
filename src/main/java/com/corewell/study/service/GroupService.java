package com.corewell.study.service;

import com.corewell.study.domain.Group;
import com.corewell.study.domain.request.GroupReq;
import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface GroupService {
    /**
     * 查询项目组
     *
     * @param groupReq
     * @return
     */
    ResultMsg findGroup(GroupReq groupReq);

    /**
     * 新增项目组
     *
     * @param group
     * @return
     */
    ResultMsg insertGroup(Group group);

    /**
     * 修改项目组
     *
     * @param group
     * @return
     */
    ResultMsg updateGroup(Group group);

    /**
     * 删除项目组
     *
     * @param id
     * @return
     */
    ResultMsg updateGroupStatus(Long id);

    /**
     * 删除项目组一个学生
     *
     * @param id
     * @return
     */
    ResultMsg updateGroupStudent(Long id);


}
