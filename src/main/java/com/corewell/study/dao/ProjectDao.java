package com.corewell.study.dao;

import com.corewell.study.domain.Project;
import com.corewell.study.domain.request.ProjectReq;
import com.corewell.study.domain.response.ProjectDo;
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
public interface ProjectDao {
    /**
     * 查询项目
     *
     * @param projectReq
     * @return
     */
    List<ProjectDo> findProject(ProjectReq projectReq);

    /**
     * 新增项目
     *
     * @param project
     * @return
     */
    int insertProject(Project project);

    /**
     * 修改项目
     *
     * @param project
     * @return
     */
    int updateProject(Project project);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    int updateProjectStatus(@Param("id") Long id);

    /**
     * 删除项目
     *
     * @param creatorId
     * @return
     */
    int updateProjectStatusByCreatorId(Long creatorId);

    /**
     * 查询项目
     *
     * @param creatorId
     * @return
     */
    List<Long> findProjectId(Long creatorId);


}
