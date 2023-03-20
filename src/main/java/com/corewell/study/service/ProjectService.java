package com.corewell.study.service;

import com.corewell.study.domain.Project;
import com.corewell.study.domain.request.ProjectReq;
import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface ProjectService {
    /**
     * 查询项目
     *
     * @param projectReq
     * @return
     */
    ResultMsg findProject(ProjectReq projectReq);

    /**
     * 新增项目
     *
     * @param project
     * @return
     */
    ResultMsg insertProject(Project project);

    /**
     * 修改项目
     *
     * @param project
     * @return
     */
    ResultMsg updateProject(Project project);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    ResultMsg updateProjectStatus(Long id);

    /**
     * 一键删除项目
     *
     * @param creatorId
     * @return
     */
    ResultMsg updateProjectStatusByCreatorId(Long creatorId);


}
