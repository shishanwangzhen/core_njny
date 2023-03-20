package com.corewell.study.controller;

import com.corewell.study.domain.request.PushDataParam;
import com.corewell.study.service.PushDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author 863586395
 */
@RestController
@RequestMapping("/data")
@Api(tags = "订阅数据")
@ApiIgnore
public class PushDataController {
    @Autowired
    private PushDataService pushDataService;

    @ApiOperation("订阅实时数据")
    @PostMapping("/getPushData")
    public void getPushData(@RequestBody PushDataParam pushData) {
        pushDataService.getPushData(pushData);

    }

}
