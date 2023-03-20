package com.corewell.study.controller;

import com.corewell.study.domain.Log;
import com.corewell.study.domain.request.LogReq;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.LogService;
import com.corewell.study.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */
@RestController
@RequestMapping("/core/log")
@Api(tags = "日志")
public class LogController {
    @Resource
    private LogService logService;

    @ApiOperation(value = "查询日志", response = Log.class)
    @PostMapping("/findLog")
    public ResultMsg findSensor(@RequestBody LogReq logReq) {
        PageUtil.setPageParams(logReq.getPageParam());
        List<Log> logList = logService.findLog(logReq);
        PageInfo<Log> logPageInfo = new PageInfo<>(logList);
        return ResultMsg.success(logPageInfo);
    }

}
