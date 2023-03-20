package com.corewell.study.service;

import com.corewell.study.domain.request.DeviceNumberReq;
import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface DeviceNumberService {
    /**
     * 查询设备序列号
     *
     * @param deviceNumberReq
     * @return
     */
    ResultMsg findDeviceNumber(DeviceNumberReq deviceNumberReq);


}
