package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corewell.study.constants.BaseConstants;
import com.corewell.study.constants.BaseRedisKeyConstants;
import com.corewell.study.dao.SensorDao;
import com.corewell.study.domain.Alarm;
import com.corewell.study.domain.Sensor;
import com.corewell.study.domain.request.AlarmActiveParam;
import com.corewell.study.domain.request.AlarmAddParam;
import com.corewell.study.domain.request.AlarmReq;
import com.corewell.study.domain.request.AlarmUpdateParam;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.AlarmService;
import com.corewell.study.timing.GetAccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/17:04
 * @Description:
 */
@Service("AlarmService")
@Slf4j
public class AlarmServiceImpl implements AlarmService {
    private static final String TLINK_URL = "https://app.dtuip.com/api/alarms/";
    private static final String TLINK_ADDALARMS_URL = TLINK_URL + "addAlarms";
    private static final String TLINK_UPDATEALARMS_URL = TLINK_URL + "updateAlarms";
    private static final String TLINK_DELETEALARMS_URL = TLINK_URL + "deleteAlarms";
    private static final String TLINK_GETALARMS_URL = TLINK_URL + "getAlarms";
    private static final String TLINK_ACTIVEALARMS_URL = TLINK_URL + "activeAlarms";
    @Autowired
    private GetAccessToken getAccessToken;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SensorDao sensorDao;

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + " " + getAccessToken.getAccessToken());
        headers.add("Content-Type", "application/json");
        headers.add("tlinkAppId", "9b248223598b471489f75f1315a7d01b");
        log.info(JSON.toJSONString(headers));
        return headers;
    }

    @Override
    public ResultMsg addAlarms(AlarmAddParam alarmAddParam) {
        log.info("addAlarms：AlarmAddParam：" + JSON.toJSONString(alarmAddParam));
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("deviceId", alarmAddParam.getDeviceId());
            mapParam.put("sensorId", alarmAddParam.getSensorId());
            mapParam.put("alarmType", alarmAddParam.getAlarmType());
            mapParam.put("heightValue", alarmAddParam.getHeightValue());
            mapParam.put("belowValue", alarmAddParam.getBelowValue());
            mapParam.put("duration", alarmAddParam.getDuration());
            mapParam.put("isForward", alarmAddParam.getIsForward());
            mapParam.put("forwardDeviceId", alarmAddParam.getForwardDeviceId());
            mapParam.put("forwardSensorId", alarmAddParam.getForwardSensorId());
            mapParam.put("forwardDataType", alarmAddParam.getForwardDataType());
            mapParam.put("userId", 80614L);
            mapParam.put("forwardLinkType", alarmAddParam.getForwardLinkType());
            mapParam.put("forwardValue", alarmAddParam.getForwardValue());
            HttpHeaders headers = getHeaders();
           log.info("addAlarms：mapParam：" + JSON.toJSONString(mapParam));
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_ADDALARMS_URL, new HttpEntity<Map>(mapParam, headers), String.class);
            log.info("addAlarms：responseEntity：" + responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            String flag = jsonObject.get("flag").toString();
            if (!BaseConstants.SUCCESS_00.equals(flag)) {
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
    public ResultMsg updateAlarms(AlarmUpdateParam alarmUpdateParam) {
        log.info("updateAlarms：AlarmUpdateParam：" + JSON.toJSONString(alarmUpdateParam));
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("id", alarmUpdateParam.getId());
            mapParam.put("deviceId", alarmUpdateParam.getDeviceId());
            mapParam.put("sensorId", alarmUpdateParam.getSensorId());
            mapParam.put("alarmType", alarmUpdateParam.getAlarmType());
            mapParam.put("heightValue", alarmUpdateParam.getHeightValue());
            mapParam.put("belowValue", alarmUpdateParam.getBelowValue());
            mapParam.put("duration", alarmUpdateParam.getDuration());
            mapParam.put("isForward", alarmUpdateParam.getIsForward());
            mapParam.put("forwardDeviceId", alarmUpdateParam.getForwardDeviceId());
            mapParam.put("forwardSensorId", alarmUpdateParam.getForwardSensorId());
            mapParam.put("forwardDataType", alarmUpdateParam.getForwardDataType());
            mapParam.put("userId", 80614L);
            mapParam.put("forwardLinkType", alarmUpdateParam.getForwardLinkType());
            mapParam.put("forwardValue", alarmUpdateParam.getForwardValue());
            HttpHeaders headers = getHeaders();
            log.info("updateAlarms：mapParam：" + JSON.toJSONString(mapParam));
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_UPDATEALARMS_URL, new HttpEntity<Map>(mapParam, headers), String.class);
           log.info("updateAlarms：responseEntity：" + responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            String flag = jsonObject.get("flag").toString();
            if (!BaseConstants.SUCCESS_00.equals(flag)) {
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
    public ResultMsg deleteAlarms(Long id) {
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("id", id);
            mapParam.put("userId", 80614L);
            HttpHeaders headers = getHeaders();
            log.info("deleteAlarms：mapParam：" + JSON.toJSONString(mapParam));
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_DELETEALARMS_URL, new HttpEntity<Map>(mapParam, headers), String.class);
            log.info("deleteAlarms：responseEntity：" + responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            String flag = jsonObject.get("flag").toString();
            if (!BaseConstants.SUCCESS_00.equals(flag)) {
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
    public ResultMsg getAlarms(AlarmReq alarmReq) {
        log.info("getAlarms：alarmReq：" + JSON.toJSONString(alarmReq));
        Long deviceId = alarmReq.getDeviceId();
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("deviceId", deviceId);
            mapParam.put("sensorId", alarmReq.getSensorId());
            mapParam.put("currPage", 1);
            mapParam.put("pageSize", 99);
            mapParam.put("userId", 80614L);
            HttpHeaders headers = getHeaders();
            log.info("getAlarms：mapParam：" + JSON.toJSONString(mapParam));
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_GETALARMS_URL, new HttpEntity<Map>(mapParam, headers), String.class);
            log.info("getAlarms：responseEntity：" + responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            String flag = jsonObject.get("flag").toString();
            if (BaseConstants.SUCCESS_00.equals(flag)) {
                List<Alarm> alarmList = JSONObject.parseArray(jsonObject.get("dataList").toString(), Alarm.class);
                Set<String> set = new HashSet<>();
                String sensorName = null;
                Long sensorId = null;
                for (Alarm alarm : alarmList) {
                    sensorId = alarm.getSensorId();
                    if (stringRedisTemplate.hasKey(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId)) {
                        String sensor = stringRedisTemplate.opsForValue().get(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId);
                        sensorName = JSONObject.parseObject(sensor).getString("sensorName");
                        alarm.setSensorName(sensorName);
                        set.add(sensorName);
                    } else {
                        Sensor sensor = sensorDao.findSensorBySensorId(sensorId);
                        sensorName = sensor.getSensorName();
                        alarm.setSensorName(sensorName);
                        set.add(sensorName);
                        stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.SENSOR_KEY + deviceId + ":" + sensorId, JSON.toJSONString(sensor), 7 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
                    }
                }
                Map<String, List> map = new HashMap<>(16);
                for (String sensorName1 : set) {
                    List<Alarm> alarms = new ArrayList<>();
                    for (Alarm alarm : alarmList) {
                        if (sensorName1.equals(alarm.getSensorName())) {
                            alarms.add(alarm);
                        }
                    }
                    map.put(sensorName1, alarms);
                }
                return ResultMsg.success(map);
            } else if (BaseConstants.SUCCESS_01.equals(flag)) {
                return ResultMsg.success();
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
    public ResultMsg activeAlarms(AlarmActiveParam alarmActiveParam) {
        log.info("activeAlarms：alarmActiveParam：" + JSON.toJSONString(alarmActiveParam));
        try {
            Map<String, Object> mapParam = new HashMap<>(16);
            mapParam.put("id", alarmActiveParam.getId());
            mapParam.put("userId", 80614L);
            mapParam.put("active", alarmActiveParam.getActive());
            HttpHeaders headers = getHeaders();
           log.info("activeAlarms：mapParam：" + JSON.toJSONString(mapParam));
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TLINK_ACTIVEALARMS_URL, new HttpEntity<Map>(mapParam, headers), String.class);
            log.info("activeAlarms：responseEntity：" + responseEntity);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            String flag = jsonObject.get("flag").toString();
            if (!BaseConstants.SUCCESS_00.equals(flag)) {
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
}
