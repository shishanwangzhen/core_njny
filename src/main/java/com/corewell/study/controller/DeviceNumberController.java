package com.corewell.study.controller;

import com.corewell.study.domain.DeviceNumber;
import com.corewell.study.domain.request.DeviceNumberReq;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.DeviceNumberService;
import io.swagger.annotations.Api;
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
@RequestMapping("/core/deviceNumber")
@Api(tags = "设备序列号")
public class DeviceNumberController {
    @Resource
    private DeviceNumberService deviceNumberService;

    @ApiOperation(value = "查询可用设备序列号", response = DeviceNumber.class)
    @PostMapping("/findDeviceNumber")
    public ResultMsg findDeviceNumber(@RequestBody DeviceNumberReq deviceNumberReq) {
        ResultMsg resultMsg = deviceNumberService.findDeviceNumber(deviceNumberReq);
        return resultMsg;

    }

}
