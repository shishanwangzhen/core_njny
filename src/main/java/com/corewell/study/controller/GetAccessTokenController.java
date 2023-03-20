package com.corewell.study.controller;

import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.GetAccessTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/30/10:36
 * @Description:
 */
@RestController
@RequestMapping("/core/token")
@ApiIgnore//隐藏接口
@Api(tags = "手动更新token")
public class GetAccessTokenController {
    @Resource
    private GetAccessTokenService getAccessTokenService;

    @ApiOperation("手动更新token")
    @PostMapping("/getAccessToken")
    public ResultMsg getAccessToken() {
        ResultMsg resultMsg = getAccessTokenService.getAccessToken();
        return resultMsg;

    }
}
