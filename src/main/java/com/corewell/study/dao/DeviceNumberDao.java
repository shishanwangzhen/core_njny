package com.corewell.study.dao;

import com.corewell.study.domain.DeviceNumber;
import com.corewell.study.domain.request.DeviceNumberReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
@Mapper
public interface DeviceNumberDao {
    /**
     * 查询可用设备序列号
     *
     * @param deviceNumberReq
     * @return
     */
    List<DeviceNumber> findDeviceNumber(DeviceNumberReq deviceNumberReq);

    /**
     * 解绑设备序列号
     *
     * @param deviceId
     * @return
     */
    int updateDeviceNumber(@Param("deviceId") Long deviceId);

    /**
     * 绑定设备序列号
     *
     * @param deviceNumber
     * @return
     */
    int updateDeviceNumberBind(DeviceNumber deviceNumber);


}
