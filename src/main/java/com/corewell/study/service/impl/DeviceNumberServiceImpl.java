package com.corewell.study.service.impl;

import com.corewell.study.dao.DeviceNumberDao;
import com.corewell.study.domain.DeviceNumber;
import com.corewell.study.domain.request.DeviceNumberReq;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.DeviceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/06/10:48
 * @Description:
 */
@Service("DeviceNumberService")
public class DeviceNumberServiceImpl implements DeviceNumberService {
    @Autowired
    private DeviceNumberDao deviceNumberDao;

    @Override
    public ResultMsg findDeviceNumber(DeviceNumberReq deviceNumberReq) {
        List<DeviceNumber> deviceNumber = deviceNumberDao.findDeviceNumber(deviceNumberReq);
        return ResultMsg.success(deviceNumber);
    }
}
