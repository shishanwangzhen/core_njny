package com.corewell.study.controller;

import com.corewell.study.domain.Alarm;
import com.corewell.study.domain.request.AlarmActiveParam;
import com.corewell.study.domain.request.AlarmAddParam;
import com.corewell.study.domain.request.AlarmReq;
import com.corewell.study.domain.request.AlarmUpdateParam;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */
@RestController
@RequestMapping("/core/alarm/")
@Api(tags = "触发器")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    @ApiOperation(value = "添加触发器")
    @PostMapping("addAlarms")
    public ResultMsg addAlarms(@RequestBody AlarmAddParam alarmAddParam) {
        ResultMsg resultMsg = alarmService.addAlarms(alarmAddParam);
        return resultMsg;
    }


    @ApiOperation("修改触发器")
    @PostMapping("updateAlarms")
    public ResultMsg updateAlarms(@RequestBody AlarmUpdateParam alarmUpdateParam) {
        ResultMsg resultMsg = alarmService.updateAlarms(alarmUpdateParam);
        return resultMsg;
    }

    @ApiOperation("删除触发器")
    @PostMapping("deleteAlarms")
    @ApiImplicitParam(value = "主键id", name = "1", required = true)
    public ResultMsg deleteAlarms(Long id) {
        ResultMsg resultMsg = alarmService.deleteAlarms(id);
        return resultMsg;
    }

    @ApiOperation(value = "查询触发器", response = Alarm.class)
    @PostMapping("getAlarms")
    public ResultMsg getAlarms(@RequestBody AlarmReq alarmReq) {
        ResultMsg resultMsg = alarmService.getAlarms(alarmReq);
        return resultMsg;
    }

    @ApiOperation("启动/停止触发器")
    @PostMapping("activeAlarms")
    public ResultMsg activeAlarms(@RequestBody AlarmActiveParam alarmActiveParam) {
        ResultMsg resultMsg = alarmService.activeAlarms(alarmActiveParam);
        return resultMsg;
    }


}
