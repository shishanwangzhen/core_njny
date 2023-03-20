package com.corewell.study.dao;

import com.corewell.study.domain.Device;
import com.corewell.study.domain.request.*;
import com.corewell.study.domain.response.DeviceDo;
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
public interface DeviceDao {
    /**
     * 查询设备
     *
     * @param deviceReq
     * @return
     */
    List<DeviceDo> findDevice(DeviceReq deviceReq);

    /**
     * 学生端查询设备添加到项目组
     *
     * @param deviceByProjectIdAndTypeReq
     * @return
     */
    List<DeviceDo> findDeviceByProjectId(DeviceByProjectIdAndTypeReq deviceByProjectIdAndTypeReq);



    /**
     * 查询采集控制设备
     *
     * @param controllerAndCollectionDeviceReq
     * @return
     */
    List<Device> findControllerAndCollectionDevice(ControllerAndCollectionDeviceReq controllerAndCollectionDeviceReq);


    /**
     * 查询设备绑定项目组
     *
     * @param projectId
     * @return
     */
    List<Device> findDeviceBindGroup(@Param("projectId") Long projectId);


    /**
     * 新增设备
     *
     * @param device
     * @return
     */
    int insertDevice(Device device);

    /**
     * 修改设备
     *
     * @param device
     * @return
     */
    int updateDevice(Device device);

    /**
     * 修改设备
     *
     * @param device
     * @return
     */
    int updateVideoDevice(Device device);

    /**
     * 删除设备
     *
     * @param deviceId
     * @return
     */
    int deleteDevice(@Param("deviceId") Long deviceId);

    /**
     * 删除设备通过id
     *
     * @param id
     * @return
     */
    int deleteDeviceById(@Param("id") Long id);

    /**
     * 删除项目解绑设备
     *
     * @param projectId
     * @return
     */
    int updateBindingByProjectId(@Param("projectId") Long projectId);

    /**
     * 一键删除项目解绑设备
     *
     * @param bindingId
     * @return
     */
    int updateBindingByBindingId(Long bindingId);

    /**
     * 删除项目组设备移除项目组
     *
     * @param groupId
     * @return
     */
    int updateBindingByGroupId(@Param("groupId") Long groupId);

    /**
     * 设备移除项目组
     *
     * @param id
     * @return
     */
    int updateBindingGroupById(@Param("id") Long id);


    /**
     * 修改设备
     *
     * @param deviceBindingReq
     * @return
     */
    int updateDeviceBinding(DeviceBindingReq deviceBindingReq);

    /**
     * 解绑设备
     *
     * @param id
     * @return
     */
    int updateDeviceBindingById(@Param("id") Long id);

    /**
     * 项目组绑定设备
     *
     * @param deviceBindingGroupReq
     * @return
     */
    int updateDeviceBindingGroup(DeviceBindingGroupReq deviceBindingGroupReq);


}
