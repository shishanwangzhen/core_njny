package com.corewell.study.timing;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/08/14:28
 * @Description: corewell同步到省农业平台
 */
@Component
public class ScheduledTasks {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String LOGIN_URL = "https://service.corewell.cn/dmp/api/user/api/login?username=xpadmin&password=xpadmin";
    private static final String DATA_URL = "https://service.corewell.cn/dmp/api/device/data/recent/data";
    private static final String SEND_SINGLE_INFO_URL = "http://210.12.220.220:8012/dataswitch-sq/sendApi/sendSingleInfo";
    Map<String, String> map = new HashMap<>();
    Map<String, String> map1 = new HashMap<>();
    Map<String, Object> map2 = new HashMap<>();
    String token = null;
    HttpHeaders headers = new HttpHeaders();


    /**
     * 常用表达式例子
     * (1)0/2 * * * * ? 表示每2秒执行任务
     * (1)0 0/2 * * * ? 表示每2分钟 执行任务
     * (1)0 0 2 1 * ?表示在每月的1日的凌晨2点调整任务
     * (2)0 15 10 ? * MON-FRI 表示周一到周五每天上午10:15执行作业
     * (3) 0 15 10 ? 6L 2002-2006 表示2002-2006年的每个月的最后一个星期五上午10:15执行作
     * (4)0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
     * (5) 0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时
     * (6)0 0 12 ? * WED 表示每个星期三中午12点
     * (7)0 0 12 * * ? 每天中午12点触发
     * (8)0 15 10 ? * * 每天上午10:15触发
     * (9)0 15 10 * * ? 每天上午10:15触发
     * (10)0 15 10 * * ? 每天上午10:15触发
     * (11) 0 15 10 * * ? 2005 2005年的每天上午10:15触发
     * (12)0 * 14 * * ? 在每天下午2点到下午2:59期间的每1分钟触发(13)00/514**? 在每天下午2点到下午2:55期间的每5分钟触发
     * (14)0 0/5 14,18 * * ? 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
     * (15)0 0-5 14 * * ? 在每天下午2点到下午2:05期间的每1分钟触发
     */


   // @Scheduled(cron = "0 0/20 * * * ?")
    public void scheduledTasks() {
        System.out.println("————————————$$$$$$$$$$$" + new Date() + "定时任务开始————————————————————");
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(LOGIN_URL, null, String.class);
            System.out.println(responseEntity.getBody());
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            Object data = jsonObject.get("data");
            JSONObject parseObject = JSONObject.parseObject(data.toString());
            token = parseObject.getString("token");
            stringRedisTemplate.opsForValue().set("token", token);
            System.out.println(stringRedisTemplate.opsForValue().get("token"));

            headers.clear();
            headers.add("token", token);
            map.put("device_id", "a5b65584-022b-4e61-845a-64780c8c04bc");
            map.put("time_type", "month");
            ResponseEntity<String> response = restTemplate.postForEntity(DATA_URL, new HttpEntity<Map>(map, headers), String.class);
            System.out.println(response.getBody());
            JSONObject parseObject1 = JSONObject.parseObject(response.getBody());
            Object data1 = parseObject1.get("data");
            List<Object> list = JSONObject.parseArray(data1.toString());
            JSONObject parseObject2 = JSONObject.parseObject(list.get(0).toString());
            JSONObject parseObject3 = JSONObject.parseObject(parseObject2.get("data").toString());
            String soil_H1 = parseObject3.getString("soil_H1");
            String soil_T1 = parseObject3.getString("soil_T1");
            String soil_C1 = parseObject3.getString("soil_C1");
            map1.put("soilTemp", soil_H1);
            map1.put("conductivity", soil_C1);
            map1.put("soilMoisture", soil_T1);

            map2.put("deviceId", "njpk01");
            map2.put("sessionKey", "121345");
            map2.put("data", map1);
            System.out.println(map2);
            ResponseEntity<String> response1 = restTemplate.postForEntity(SEND_SINGLE_INFO_URL, new HttpEntity<Map>(map2, null), String.class);
            System.out.println(response1.getBody());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
