package com.corewell.study.controller;

import com.corewell.study.domain.Project;
import com.corewell.study.domain.request.ProjectReq;
import com.corewell.study.domain.response.ProjectDo;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */
@RestController
@RequestMapping("/core/project")
@Api(tags = "项目")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @ApiOperation(value = "查询项目", response = ProjectDo.class)
    @PostMapping("/findProject")
    public ResultMsg findProject(@RequestBody ProjectReq projectReq) {
        ResultMsg resultMsg = projectService.findProject(projectReq);
        return resultMsg;

    }

    @ApiOperation("项目信息修改")
    @PostMapping("/updateProject")
    public ResultMsg updateProject(@RequestBody Project project) {
        ResultMsg resultMsg = projectService.updateProject(project);
        return resultMsg;

    }

    @ApiOperation("项目信息新增")
    @PostMapping("/insertProject")
    public ResultMsg insertProject(@RequestBody Project project) {
        ResultMsg resultMsg = projectService.insertProject(project);
        return resultMsg;

    }

    @ApiOperation("项目删除")
    @PostMapping("/updateProjectStatus")
    @ApiImplicitParam(value = "主键id", name = "1", required = true)
    public ResultMsg updateProjectStatus(Long id) {
        ResultMsg resultMsg = projectService.updateProjectStatus(id);
        return resultMsg;

    }

    @ApiOperation("项目一键删除")
    @PostMapping("/updateProjectStatusByCreatorId")
    @ApiImplicitParam(value = "实验创建者id", name = "1", required = true)
    public ResultMsg updateProjectStatusByCreatorId(Long creatorId) {
        ResultMsg resultMsg = projectService.updateProjectStatusByCreatorId(creatorId);
        return resultMsg;

    }

}
