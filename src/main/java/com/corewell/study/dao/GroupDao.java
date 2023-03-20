package com.corewell.study.dao;

import com.corewell.study.domain.Group;
import com.corewell.study.domain.request.GroupReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
@Mapper
public interface GroupDao {
    /**
     * 查询项目组
     *
     * @param groupReq
     * @return
     */
    List<Group> findGroup(GroupReq groupReq);

    /**
     * 新增项目组
     *
     * @param group
     * @return
     */
    int insertGroup(Group group);

    /**
     * 修改项目组
     *
     * @param group
     * @return
     */
    int updateGroup(Group group);

    /**
     * 删除项目组
     *
     * @param id
     * @return
     */
    int updateGroupStatus(Long id);

    /**
     * 根据projectId删除项目组
     *
     * @param projectId
     * @return
     */
    int updateGroupStatusByProjectId(Long projectId);

    /**
     * 删除项目
     *
     * @param creatorId
     * @return
     */
    int updateGroupStatusByCreatorId(Long creatorId);


}
