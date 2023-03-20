package com.corewell.study.controller;

import com.corewell.study.domain.Group;
import com.corewell.study.domain.request.GroupReq;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.GroupService;
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
@RequestMapping("/core/group")
@Api(tags = "项目组")
public class GroupController {
    @Resource
    private GroupService groupService;

    @ApiOperation(value = "查询项目组", response = Group.class)
    @PostMapping("/findGroup")
    public ResultMsg findGroup(@RequestBody GroupReq groupReq) {
        ResultMsg resultMsg = groupService.findGroup(groupReq);
        return resultMsg;

    }

    @ApiOperation("项目组信息修改")
    @PostMapping("/updateGroup")
    public ResultMsg updateGroup(@RequestBody Group group) {
        ResultMsg resultMsg = groupService.updateGroup(group);
        return resultMsg;

    }

    @ApiOperation("项目组信息新增")
    @PostMapping("/insertGroup")
    public ResultMsg insertGroup(@RequestBody Group group) {
        ResultMsg resultMsg = groupService.insertGroup(group);
        return resultMsg;

    }

    @ApiOperation("项目组删除")
    @PostMapping("/updateGroupStatus")
    @ApiImplicitParam(value = "主键id", name = "1", required = true)
    public ResultMsg updateGroupStatus(Long id) {
        ResultMsg resultMsg = groupService.updateGroupStatus(id);
        return resultMsg;

    }

    @ApiOperation("项目组学生删除")
    @PostMapping("/updateGroupStudent")
    @ApiImplicitParam(value = "学生主键id", name = "1", required = true)
    public ResultMsg updateGroupStudent(Long id) {
        ResultMsg resultMsg = groupService.updateGroupStudent(id);
        return resultMsg;

    }


}
