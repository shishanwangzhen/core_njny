package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corewell.study.annotation.AddLog;
import com.corewell.study.constants.BaseConstants;
import com.corewell.study.constants.BaseRedisKeyConstants;
import com.corewell.study.dao.DeviceDao;
import com.corewell.study.dao.DeviceNumberDao;
import com.corewell.study.dao.SensorDao;
import com.corewell.study.domain.Device;
import com.corewell.study.domain.DeviceNumber;
import com.corewell.study.domain.Sensor;
import com.corewell.study.domain.request.*;
import com.corewell.study.domain.response.*;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.service.DeviceService;
import com.corewell.study.timing.GetAccessToken;
import com.corewell.study.utils.InfluxDbUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/17/13:31
 * @Description:
 */
@Service("DeviceService")
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    private static final String TLINK_URL = "https://app.dtuip.com/api/device/";
    private static final String TLINK_ADDDEVICE_URL = TLINK_URL + "addDevice";
    private static final String TLINK_UPDATEDEVICE_URL = TLINK_URL + "updateDevice";
    private static final String TLINK_DELETEDEVICE_URL = TLINK_URL + "deleteDevice";
    private static final String TLINK_GETSINGLEDEVICEDATAS_URL = TLINK_URL + "getSingleDeviceDatas";
    private static final String TLINK_SWITCHER_URL = TLINK_URL + "switcherController";
    private static final String TLINK_WRITE_URL = TLINK_URL + "deviceWrite";
    private static final String TLINK_SENDDATAPOINT_URL = TLINK_URL + "sendDataPoint";
    private static final String TLINK_HISTORY_URL = TLINK_URL + "getSensorHistroy";
    private static final String TLINK_GETPARAMS_URL = TLINK_URL + "getParams";
    private static final String TLINK_SETPARAMS_URL = TLINK_URL + "setParams";
    private static final String TLINK_SETMODBUS_URL = TLINK_URL + "setModbus";
    private static final String TLINK_GETMODBUS_URL = TLINK_URL + "getModbus";
    private static final String TLINK_UPDATEMODBUS_URL = TLINK_URL + "updateModbus";
    private static final String TLINK_GETPROTOCOLLABEL_URL = TLINK_URL + "getProtocolLabel";
    private static final String TLINK_SETPROTOCOLLABEL_URL = TLINK_URL + "setProtocolLabel";
    private static final String TLINK_GETFLAG_URL = TLINK_URL + "getFlag";
    private static final String TLINK_SETFLAG_URL = TLINK_URL + "setFlag";
    private static final String TLINK_GETSINGLESENSORDATAS_URL = TLINK_URL + "getSingleSensorDatas";


    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private GetAccessToken getAccessToken;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeviceNumberDao deviceNumberDao;
    @Autowired
    private SensorDao sensorDao;


    @Autowired
    private InfluxDbUtils influxDbUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + " " + getAccessToken.getAccessToken());
        headers.add("Content-Type", "application/json");
        headers.add("tlinkAppId", "9b248223598b471489f75f1315a7d01b");
        log.info("headers  :::" + JSON.toJSONString(headers));
        return headers;
    }

    @Override
    public ResultMsg findDevice(DeviceReq deviceReq) {
        log.info("findDevice:  deviceReq:  " + JSON.toJSONString(deviceReq));
        List<DeviceDo> DeviceDOList = deviceDao.findDevice(deviceReq);
        return ResultMsg.success(DeviceDOList);
    }

    @Override
    public ResultMsg findDeviceByProjectId(DeviceByProjectIdAndTypeReq deviceByProjectIdAndTypeReq) {
        log.info("findDeviceByProjectId:  deviceByProjectIdAndTypeReq:  " + JSON.toJSONString(deviceByProjectIdAndTypeReq));
        List<DeviceDo> DeviceDOList = deviceDao.findDeviceByProjectId(deviceByProjectIdAndTypeReq);
        return ResultMsg.success(DeviceDOList);
    }

    @Override
    public ResultMsg findDeviceAndIsLine(DeviceReq deviceReq) {
        log.info("findDeviceAndIsLine:  deviceReq:  " + JSON.toJSONString(deviceReq));
        List<DeviceDo> DeviceDOList = deviceDao.findDevice(deviceReq);
        for (DeviceDo deviceDo : DeviceDOList) {
            deviceDo.setIsLine(0L);
            if (stringRedisTemplate.hasKey(BaseRedisKeyConstants.DEVICE_IS_LINE_KEY + deviceDo.getDeviceId())) {
                deviceDo.setIsLine(1L);
            }
        }
        return ResultMsg.success(DeviceDOList);
    }

    @Override
    public ResultMsg findDeviceIsLine(Long deviceId) {
        log.info("findDeviceIsLine:  deviceId:  " + JSON.toJSONString(deviceId));
        DeviceIsLineDTO deviceIsLineDTO = new DeviceIsLineDTO(deviceId, 0L);
        if (stringRedisTemplate.hasKey(BaseRedisKeyConstants.DEVICE_IS_LINE_KEY + deviceId)) {
            deviceIsLineDTO.setIsLine(1L);
            return ResultMsg.success(deviceIsLineDTO);
        }
        return ResultMsg.success(deviceIsLineDTO);
    }


    @Override
    public ResultMsg findControllerAndCollectionDevice(ControllerAndCollectionDeviceReq controllerAndCollectionDeviceReq) {
        log.info("findControllerAndCollectionDevice:  controllerAndCollectionDeviceReq:  " + JSON.toJSONString(controllerAndCollectionDeviceReq));
        List<Device> DeviceList = deviceDao.findControllerAndCollectionDevice(controllerAndCollectionDeviceReq);
        return ResultMsg.success(DeviceList);
    }

    @Override
    public ResultMsg findDeviceBindGroup(Long projectId) {
        log.info("findDeviceBindGroup:  projectId:  " + JSON.toJSONString(projectId));
        List<Device> DeviceList = deviceDao.findDeviceBindGroup(projectId);
        return ResultMsg.success(DeviceList);
    }


    @Override
    public ResultMsg findDeviceByDeviceId(Long deviceId) {
        log.info("findDeviceByDeviceId:  deviceId:  " + JSON.toJSONString(deviceId));
        try {
            if (deviceId == null || deviceId.longValue() == 0) {
                return ResultMsg.error();
            }
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614);
            mapParam.put("deviceId", deviceId);
            mapParam.put("pageSize", 99);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_GETSINGLEDEVICEDATAS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("findDeviceByDeviceId：responseEntity.getBody()：" + responseEntity.getBody());
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            String flag = jsonObject.get("flag").toString();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                DeviceDTO deviceDTO = JSON.parseObject(jsonObject.get("device").toString(), DeviceDTO.class);
                List<SensorDTO> sensorsList = deviceDTO.getSensorsList();
                if (sensorsList.size() == 1) {
                    Long id = sensorsList.get(0).getId();
                    if (id == 0) {
                        sensorsList.remove(0);
                    }
                }
                return ResultMsg.success(deviceDTO);
            } else {
                return ResultMsg.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg insertDevice(DeviceInsertParam deviceInsertParam) {
        log.info("insertDevice:  deviceInsertParam:  " + JSON.toJSONString(deviceInsertParam));
        if ("4".equals(deviceInsertParam.getType())) {
            Device device = new Device();
            device.setDeviceName(deviceInsertParam.getDeviceName());
            device.setType(deviceInsertParam.getType());
            device.setCreatorId(deviceInsertParam.getCreatorId());
            device.setCreateTime(new Date());
            int result = deviceDao.insertDevice(device);
            if (result == 1) {
                return ResultMsg.success();
            }
            return ResultMsg.error();
        }
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("deviceNo", deviceInsertParam.getDeviceNo());
            mapParam.put("lat", "22.601376");
            mapParam.put("lng", "113.956591");
            mapParam.put("linkType", deviceInsertParam.getLinkType());
            mapParam.put("timescale", deviceInsertParam.getTimescale());
            mapParam.put("userId", 80614L);
            mapParam.put("deviceName", deviceInsertParam.getDeviceName());
            mapParam.put("sensorList", deviceInsertParam.getSensorList());
            HttpHeaders headers = getHeaders();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_ADDDEVICE_URL, new HttpEntity<Map>(mapParam, headers), String.class);
            log.info("insertDevice：responseEntity：" + responseEntity);
            if (!JSON.parseObject(responseEntity.getBody()).containsKey("deviceId")) {
                return ResultMsg.error();
            }
            mapParam.put("pageSize", 99);
            ResponseEntity<String> responseEntity1 = restTemplate.postForEntity(TLINK_GETSINGLEDEVICEDATAS_URL, new HttpEntity<Map>(mapParam, headers), String.class);
            log.info("deviceNo查询设备还参：：" + responseEntity1.getBody());
            JSONObject jsonObject = JSON.parseObject(responseEntity1.getBody());
            String flag = jsonObject.get("flag").toString();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                DeviceDTO deviceDTO = JSON.parseObject(jsonObject.get("device").toString(), DeviceDTO.class);
                String deviceNo = deviceDTO.getDeviceNo();
                Device device = new Device();
                Long deviceId = deviceDTO.getId();
                String deviceName = deviceDTO.getDeviceName();
                device.setDeviceId(deviceId);
                device.setDeviceName(deviceName);
                device.setDeviceNo(deviceNo);
                device.setBinding("0");
                device.setLinkType(deviceDTO.getLinktype());
                device.setTimescale(Long.valueOf(deviceDTO.getDefaultTimescale()));
                device.setType(deviceInsertParam.getType());
                device.setCreatorId(deviceInsertParam.getCreatorId());
                device.setCreateTime(new Date());
                deviceDao.insertDevice(device);
                DeviceNumber deviceNumber = new DeviceNumber();
                deviceNumber.setDeviceNo(deviceNo);
                deviceNumber.setDeviceId(deviceDTO.getId());
                deviceNumberDao.updateDeviceNumberBind(deviceNumber);
                if ("1".equals(deviceInsertParam.getType())) {
                    List<SensorDTO> sensorsList = deviceDTO.getSensorsList();
                    if (sensorsList.size() == 1 && sensorsList.get(0).getId() == 0) {
                        sensorsList.remove(0);
                    } else {
                        for (SensorDTO sensorDTO : sensorsList) {
                            Sensor sensor = new Sensor();
                            sensor.setDeviceId(deviceId);
                            sensor.setDeviceName(deviceName);
                            Long sensorId = sensorDTO.getId();
                            sensor.setSensorId(sensorId);
                            sensor.setSensorName(sensorDTO.getSensorName());
                            sensor.setUnit(sensorDTO.getUnit());
                            sensor.setSensorType(sensorDTO.getSensorTypeId());
                            sensor.setDecimalPlacse(sensorDTO.getDecimalPlacse());
                            sensor.setMinimum(0D);
                            sensor.setMaximum(100D);
                            sensor.setCreateTime(new Date());
                            sensorDao.insertSensor(sensor);
                            stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId, JSON.toJSONString(sensor), 7 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
                        }
                    }
                    stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.DEVICE_KEY + deviceId, JSON.toJSONString(device));
                }
            } else {
                return ResultMsg.error();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        return ResultMsg.success();
    }

    @Override
    public ResultMsg insertVideoDevice(Device device) {
        log.info("insertVideoDevice:  device:  " + JSON.toJSONString(device));
        device.setCreateTime(new Date());
        int result = deviceDao.insertDevice(device);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    @AddLog(interfaceType = "2", interfaceInfo = "修改视频设备", interfaceName = "updateVideoDevice", dataId = "#{device.id}")
    public ResultMsg updateVideoDevice(Device device) {
        log.info("updateVideoDevice:  device:  " + JSON.toJSONString(device));
        device.setUpdateTime(new Date());
        int result = deviceDao.updateVideoDevice(device);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    @AddLog(interfaceType = "1", interfaceInfo = "删除视频设备", interfaceName = "deleteVideoDevice", dataId = "#{id}")
    public ResultMsg deleteVideoDevice(Long id) {
        log.info("deleteVideoDevice:  id:  " + JSON.toJSONString(id));
        int result = deviceDao.deleteDeviceById(id);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    @AddLog(interfaceType = "2", interfaceInfo = "修改设备", interfaceName = "updateDevice", dataId = "#{deviceUpdateParam.deviceId}")
    public ResultMsg updateDevice(DeviceUpdateParam deviceUpdateParam) {
        log.info("updateDevice:  deviceUpdateParam:  " + JSON.toJSONString(deviceUpdateParam));
        List<SensorParam> sensorList = deviceUpdateParam.getSensorList();
        HttpHeaders headers = getHeaders();
        Map<String, Object> body = new HashMap<>(16);
        try {
            body.put("lat", "22.601376");
            body.put("lng", "113.956591");
            body.put("userId", 80614L);
            body.put("sensorList", sensorList);
            body.put("deviceName", deviceUpdateParam.getDeviceName());
            body.put("timescale", deviceUpdateParam.getTimescale());
            body.put("linkType", deviceUpdateParam.getLinkType());
            body.put("deviceId", deviceUpdateParam.getDeviceId());
            body.put("delSensorIds", deviceUpdateParam.getDelSensorIds());
            HttpEntity<Map> mapHttpEntity = new HttpEntity<Map>(body, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_UPDATEDEVICE_URL, mapHttpEntity, String.class);
            log.info("updateDevice：responseEntity.getBody()：" + responseEntity.getBody());
            if (!BaseConstants.SUCCESS_00.equals(JSON.parseObject(responseEntity.getBody()).get("flag").toString())) {
                return ResultMsg.error();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        Device device = new Device();
        device.setDeviceId(deviceUpdateParam.getDeviceId());
        if (StringUtils.isNotBlank(deviceUpdateParam.getLinkType())) {
            device.setLinkType(deviceUpdateParam.getLinkType());
        }
        if (deviceUpdateParam.getTimescale() != null) {
            device.setTimescale(deviceUpdateParam.getTimescale());
        }
        if (StringUtils.isNotBlank(deviceUpdateParam.getDeviceName())) {
            device.setDeviceName(deviceUpdateParam.getDeviceName());
        }
        String type = deviceUpdateParam.getType();
        device.setType(type);
        device.setUpdateTime(new Date());
        deviceDao.updateDevice(device);
        if ("1".equals(type)) {
            if (deviceUpdateParam.getDelSensorIds() != "" && deviceUpdateParam.getDelSensorIds() != null) {
                //批量删除
                sensorDao.deleteSensorById(Long.valueOf(deviceUpdateParam.getDelSensorIds()));
                stringRedisTemplate.delete(BaseRedisKeyConstants.SENSOR_KEY + deviceUpdateParam.getDeviceId() + ":" + deviceUpdateParam.getDelSensorIds());
            }
            body.put("pageSize", 99);
            ResponseEntity<String> responseEntity1 = restTemplate.postForEntity(TLINK_GETSINGLEDEVICEDATAS_URL, new HttpEntity<Map>(body, headers), String.class);
            JSONObject jsonObject = JSON.parseObject(responseEntity1.getBody());
            String flag = jsonObject.get("flag").toString();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                DeviceDTO deviceDTO = JSON.parseObject(jsonObject.get("device").toString(), DeviceDTO.class);
                Long deviceId = deviceDTO.getId();
                String deviceName = deviceDTO.getDeviceName();
                List<SensorDTO> sensorsList = deviceDTO.getSensorsList();
                if (sensorsList.size() == 1 && sensorsList.get(0).getId() == 0) {
                    sensorsList.remove(0);
                } else {
                    for (SensorDTO sensorDTO : sensorsList) {
                        //Sensor sensor = JSON.parseObject(sensorDTO.toString(), Sensor.class);
                        Sensor sensor = new Sensor();
                        sensor.setDeviceId(deviceId);
                        sensor.setDeviceName(deviceName);

                        Long sensorId = sensorDTO.getId();
                        sensor.setSensorId(sensorId);
                        sensor.setSensorName(sensorDTO.getSensorName());
                        sensor.setUnit(sensorDTO.getUnit());
                        sensor.setSensorType(sensorDTO.getSensorTypeId());
                        sensor.setDecimalPlacse(sensorDTO.getDecimalPlacse());
                        Sensor sensorOld = sensorDao.findSensorBySensorId(sensorId);
                        if (sensorOld == null) {
                            sensor.setCreateTime(new Date());
                            sensor.setMinimum(0D);
                            sensor.setMaximum(100D);
                            sensorDao.insertSensor(sensor);
                        } else {
                            sensor.setId(sensorOld.getId());
                            sensor.setMinimum(sensorOld.getMinimum());
                            sensor.setMaximum(sensorOld.getMaximum());
                            sensor.setUpdateTime(new Date());
                            sensorDao.updateSensor(sensor);
                        }
                        stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId, JSON.toJSONString(sensor), 7 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);

                    }
                }
                stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.DEVICE_KEY + deviceId, JSON.toJSONString(device));
            }

        }
        return ResultMsg.success();
    }

    @Override
    @AddLog(interfaceType = "1", interfaceInfo = "删除设备", interfaceName = "deleteDevice", dataId = "#{deviceId}")
    public ResultMsg deleteDevice(Long deviceId) {
        log.info("deleteDevice:  deviceId:  " + JSON.toJSONString(deviceId));
        ResponseEntity<String> responseEntity = null;
        try {
            Map<String, Object> body = new HashMap<>(16);
            body.put("userId", 80614L);
            body.put("deviceId", deviceId);
            responseEntity = restTemplate.postForEntity(TLINK_DELETEDEVICE_URL, new HttpEntity<Map>(body, getHeaders()), String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        String flag = jsonObject.get("flag").toString();
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            deviceDao.deleteDevice(deviceId);
            deviceNumberDao.updateDeviceNumber(deviceId);
            sensorDao.deleteSensorByDeviceId(deviceId);
            stringRedisTemplate.delete(BaseRedisKeyConstants.DEVICE_KEY + deviceId);
        } else {
            return ResultMsg.error();
        }
        return ResultMsg.success();
    }

    @Override
    public ResultMsg updateDeviceBinding(DeviceBindingReq deviceBindingReq) {
        log.info("updateDeviceBinding:  deviceBindingReq:  " + JSON.toJSONString(deviceBindingReq));
        int result = deviceDao.updateDeviceBinding(deviceBindingReq);
        if (result > 0) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    public ResultMsg updateDeviceBindingById(Long id) {
        log.info("updateDeviceBindingById:  id:  " + JSON.toJSONString(id));
        int result = deviceDao.updateDeviceBindingById(id);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    public ResultMsg updateDeviceBindingGroup(DeviceBindingGroupReq deviceBindingGroupReq) {
        log.info("updateDeviceBindingGroup:  deviceBindingGroupReq:  " + JSON.toJSONString(deviceBindingGroupReq));
        int result = deviceDao.updateDeviceBindingGroup(deviceBindingGroupReq);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }


    @Override
    public ResultMsg switcherController(DeviceSwitcherParam deviceSwitcherParam) {
        log.info("switcherController:  deviceSwitcherParam:  " + JSON.toJSONString(deviceSwitcherParam));
        ResponseEntity<String> responseEntity = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceNo", deviceSwitcherParam.getDeviceNo());
            mapParam.put("switcher", deviceSwitcherParam.getSwitcher());
            mapParam.put("sensorId", deviceSwitcherParam.getSensorId());
            responseEntity = restTemplate.postForEntity(TLINK_SWITCHER_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("switcherController： responseEntity.getBody()：" + responseEntity.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        String flag = jsonObject.get("flag").toString();
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }


    @Override
    public ResultMsg deviceWrite(DeviceWriteParam deviceWriteParam) {
        log.info("deviceWrite:  deviceWriteParam:  " + JSON.toJSONString(deviceWriteParam));
        ResponseEntity<String> responseEntity = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceNo", deviceWriteParam.getDeviceNo());
            mapParam.put("value", deviceWriteParam.getValue());
            mapParam.put("sensorId", deviceWriteParam.getSensorId());
            responseEntity = restTemplate.postForEntity(TLINK_WRITE_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("deviceWrite：responseEntity.getBody()：" + responseEntity.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        String flag = jsonObject.get("flag").toString();
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg sendDataPoint(SendDataPointParam sendDataPointParam) {
        log.info("sendDataPoint:  sendDataPointParam:  " + JSON.toJSONString(sendDataPointParam));
        ResponseEntity<String> responseEntity = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceNo", sendDataPointParam.getDeviceNo());
            mapParam.put("addTime", sendDataPointParam.getAddTime());
            mapParam.put("sensorDatas", sendDataPointParam.getSensorDatas());
            responseEntity = restTemplate.postForEntity(TLINK_SENDDATAPOINT_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        String flag = jsonObject.get("flag").toString();
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }


    private QueryResult query(String command) {
        return influxDbUtils.getInfluxDB().query(new Query(command, "test"));
    }

    @Override
    public ResultMsg getSensorHistroy(SensorHistoryParam sensorHistoryParam) {
        log.info("getSensorHistroy：getSensorHistroy:  " + JSONObject.toJSON(sensorHistoryParam));
        StringBuilder command = new StringBuilder();
        command.append("SELECT time,value FROM CORE_NJNY where 1=1");
        if (sensorHistoryParam.getSensorId() != null && sensorHistoryParam.getSensorId() != 0) {
            command.append(" AND sensorsId=");
            command.append("'");
            command.append(sensorHistoryParam.getSensorId());
            command.append("'");
        }
        if (StringUtils.isNotBlank(sensorHistoryParam.getStartDate())) {
            command.append(" AND time>");
            command.append("'");
            command.append(sensorHistoryParam.getStartDate());
            command.append("'");
        }
        if (StringUtils.isNotBlank(sensorHistoryParam.getEndDate())) {
            command.append(" AND time<");
            command.append("'");
            command.append(sensorHistoryParam.getEndDate());
            command.append("'");
        }
        //TODO
        // command.append("GROUP BY *");
        QueryResult resultMsg = query(command.toString());
        log.info("getSensorHistroy：resultMsg:  " + JSONObject.toJSON(resultMsg));
        return ResultMsg.success(resultMsg);
    }

    @Override
    public ResultMsg getParams(Long deviceId) {
        log.info("getParams：deviceId:  " + JSONObject.toJSON(deviceId));
        ResponseEntity<String> responseEntity = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", deviceId);
            responseEntity = restTemplate.postForEntity(TLINK_GETPARAMS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("getParams：responseEntity.getBody()：" + responseEntity.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error("getParams：e:  " + e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        String flag = jsonObject.get("flag").toString();
        if (BaseConstants.SUCCESS_00.equals(flag) && jsonObject.containsKey("params")) {
            ParamsDTO paramsDTO = JSON.parseObject(jsonObject.get("params").toString(), ParamsDTO.class);
            return ResultMsg.success(paramsDTO);
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg setParams(SetParamsReq setParamsReq) {
        log.info("setParams：setParamsReq:  " + JSONObject.toJSON(setParamsReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", setParamsReq.getDeviceId());
            mapParam.put("params", setParamsReq.getParams());
            mapParam.put("isWrite", setParamsReq.getIsWrite());
            responseEntity = restTemplate.postForEntity(TLINK_SETPARAMS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            flag = jsonObject.get("flag").toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error("setParams：e:  " + e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg setModbus(ModbusReq modbusReq) {
        log.info("setModbus：modbusReq:  " + JSONObject.toJSON(modbusReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            List<Modbus> modbusList = modbusReq.getModbusList();
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", modbusReq.getDeviceId());
            mapParam.put("linktype", modbusReq.getLinktype());
            mapParam.put("modbusList", modbusList);
            if (modbusList.size() > 0) {
                responseEntity = restTemplate.postForEntity(TLINK_SETMODBUS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
                log.info("setModbus：responseEntity.getBody()：" + responseEntity.getBody());
                Iterator<Modbus> iterator = modbusList.iterator();
                while (iterator.hasNext()) {
                    Modbus modbus = iterator.next();
                    if (modbus.getId() == null || modbus.getId() == 0) {
                        iterator.remove();
                    }
                }
                if (modbusList.size() > 0) {
                    mapParam.put("modbusList", modbusList);
                    log.info("setModbus：mapParam：" + JSON.toJSONString(mapParam));
                    responseEntity = restTemplate.postForEntity(TLINK_UPDATEMODBUS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
                }

            }

            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            flag = jsonObject.get("flag").toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }


    @Override
    public ResultMsg getModbus(ModbusGetReq modbusGetReq) {
        log.info("getModbus：modbusGetReq:  " + JSONObject.toJSON(modbusGetReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", modbusGetReq.getDeviceId());
            mapParam.put("linktype", modbusGetReq.getLinktype());
            responseEntity = restTemplate.postForEntity(TLINK_GETMODBUS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("getModbus：responseEntity.getBody()：" + responseEntity.getBody());
            ModbusDTO modbusDTO = JSON.parseObject(responseEntity.getBody(), ModbusDTO.class);
            flag = modbusDTO.getFlag();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                return ResultMsg.success(modbusDTO);
            } else {
                return ResultMsg.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
    }


    @Override
    public ResultMsg updateModbus(ModbusReq modbusReq) {
        log.info("updateModbus：modbusReq：" + JSON.toJSONString(modbusReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", modbusReq.getDeviceId());
            mapParam.put("linktype", modbusReq.getLinktype());
            mapParam.put("modbusList", modbusReq.getModbusList());

            responseEntity = restTemplate.postForEntity(TLINK_UPDATEMODBUS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("updateModbus：responseEntity.getBody()：" + responseEntity.getBody());
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            flag = jsonObject.get("flag").toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.toString());
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg getProtocolLabel(Long deviceId) {
        log.info("getProtocolLabel：deviceId：" + JSON.toJSONString(deviceId));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", deviceId);

            responseEntity = restTemplate.postForEntity(TLINK_GETPROTOCOLLABEL_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("getProtocolLabel： responseEntity.getBody() ：" + responseEntity.getBody());
            ProtocolLabelDTO protocolLabelDTO = JSON.parseObject(responseEntity.getBody(), ProtocolLabelDTO.class);
            flag = protocolLabelDTO.getFlag();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                return ResultMsg.success(protocolLabelDTO);
            } else {
                return ResultMsg.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg setProtocolLabel(ProtocolLabelReq protocolLabelReq) {
        log.info("setProtocolLabel：protocolLabelReq：" + JSON.toJSONString(protocolLabelReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", protocolLabelReq.getDeviceId());
            mapParam.put("linktype", protocolLabelReq.getLinktype());
            mapParam.put("protocolLabel", protocolLabelReq.getProtocolLabel());

            responseEntity = restTemplate.postForEntity(TLINK_SETPROTOCOLLABEL_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("setProtocolLabel：responseEntity.getBody()：" + responseEntity.getBody());
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            flag = jsonObject.get("flag").toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg getFlag(GetSensorFlagReq getSensorFlagReq) {
        log.info("getFlag：getSensorFlagReq：" + JSON.toJSONString(getSensorFlagReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", getSensorFlagReq.getDeviceId());
            mapParam.put("linktype", getSensorFlagReq.getLinktype());

            responseEntity = restTemplate.postForEntity(TLINK_GETFLAG_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("getFlag：responseEntity.getBody()：" + responseEntity.getBody());
            GetFlagDTO getFlagDTO = JSON.parseObject(responseEntity.getBody(), GetFlagDTO.class);
            flag = getFlagDTO.getFlag();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                return ResultMsg.success(getFlagDTO);
            } else {
                return ResultMsg.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg setFlag(SetSensorFlagReq setFlagReq) {
        log.info("setFlag：setFlagReq：" + JSON.toJSONString(setFlagReq));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("deviceId", setFlagReq.getDeviceId());
            mapParam.put("linktype", setFlagReq.getLinktype());
            mapParam.put("sensorList", setFlagReq.getSensorList());
            responseEntity = restTemplate.postForEntity(TLINK_SETFLAG_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("setFlag：responseEntity.getBody()：" + responseEntity.getBody());
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            flag = jsonObject.get("flag").toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success();
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg getSingleSensorDatas(Long sensorId) {
        log.info("getSingleSensorDatas：sensorId：" + JSON.toJSONString(sensorId));
        ResponseEntity<String> responseEntity = null;
        String flag = null;
        JSONObject jsonObject = null;
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("userId", 80614L);
            mapParam.put("sensorId", sensorId);

            responseEntity = restTemplate.postForEntity(TLINK_GETSINGLESENSORDATAS_URL, new HttpEntity<Map>(mapParam, getHeaders()), String.class);
            log.info("getSingleSensorDatas：：" + responseEntity.getBody());
            jsonObject = JSONObject.parseObject(responseEntity.getBody());
            flag = jsonObject.get("flag").toString();
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains(BaseConstants.INVALID_TOKEN)) {
                getAccessToken.getNewAccessToken();
            }
            return ResultMsg.error();
        }
        if (BaseConstants.SUCCESS_00.equals(flag)) {
            return ResultMsg.success(jsonObject);
        } else {
            return ResultMsg.error();
        }
    }

    @Override
    public ResultMsg updateBindingGroupById(Long id) {
        log.info("updateBindingGroupById：id：" + JSON.toJSONString(id));
        int result = deviceDao.updateBindingGroupById(id);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.DELETE_FAILED);
    }

}
