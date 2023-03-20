package com.corewell.study.service;

import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/30/10:40
 * @Description:
 */
public interface GetAccessTokenService {
    /**
     * 手动更新token
     *
     * @return
     */
    ResultMsg getAccessToken();

}
