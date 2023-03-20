package com.corewell.study.timing;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/08/14:28
 * @Description: 将tlink传感器数据同步到省农业平台
 */
@Component
public class ScheduledTasksDw {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String LOGIN_URL = "https://app.dtuip.com/oauth/token?grant_type=password&username=bydwadmin&password=bydwadmin123";
    private static final String DATA_URL = "https://app.dtuip.com/api/device/getSingleDeviceDatas";
    private static final String SEND_SINGLE_INFO_URL = "http://210.12.220.220:8012/dataswitch-sq/sendApi/sendSingleInfo";
    Map<String, String> map = new HashMap<>();
    Map<String, String> map1 = new HashMap<>();
    Map<String, Object> map2 = new HashMap<>();
    String token = null;
    HttpHeaders headers = new HttpHeaders();
    DecimalFormat df = new DecimalFormat("0.00");


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


    //@Scheduled(cron = "0 0/20 * * * ?")
    public void ScheduledTasksDw() {
        System.out.println("————————————$$$$$$$$$$$" + new Date() + "定时任务ScheduledTasksDw开始————————————————————");
        try {
            headers.clear();
            headers.add("authorization", "Basic NGI4NDIwZDRkYzk3NGZkNDgyODUwODZkMDkwMjJmOWI6YzliM2RjYjBkNjcxNDE0YTg2Mjg2ZmQyZDNmMGM2N2I=");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(LOGIN_URL, new HttpEntity<Map>(null, headers), String.class);
            System.out.println(responseEntity.getBody());
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            String accessToken = jsonObject.get("access_token").toString();
            stringRedisTemplate.opsForValue().set("token", token);
            System.out.println(stringRedisTemplate.opsForValue().get("token"));
            headers.clear();
            headers.add("Authorization", "Bearer" + " " + accessToken);
            headers.add("Content-Type", "application/json");
            headers.add("tlinkAppId", "4b8420d4dc974fd48285086d09022f9b");
            map.put("userId", "76678");
            map.put("deviceId", "183080");
            ResponseEntity<String> response = restTemplate.postForEntity(DATA_URL, new HttpEntity<Map>(map, headers), String.class);
            System.out.println(response.getBody());
            JSONObject parseObject1 = JSONObject.parseObject(response.getBody());
            Object data1 = parseObject1.get("device");
            JSONObject parseObject2 = JSONObject.parseObject(data1.toString());
            Object data2 = parseObject2.get("sensorsList");
            List<Object> list = JSONObject.parseArray(data2.toString());
            System.out.println("数据list ：" + data1);
            double temp = 0;
            for (Object attribute : list) {
                String sensorName = JSONObject.parseObject(attribute.toString()).get("sensorName").toString();
                Double HydrogenSulfideConcentration = 0.0;
                switch (sensorName) {
                    case "空气温度":
                        map1.put("airTemp", JSONObject.parseObject(attribute.toString()).get("value").toString());
                        temp = Double.parseDouble(JSONObject.parseObject(attribute.toString()).get("value").toString());
                        break;
                    case "空气湿度":
                        map1.put("airHumidity", JSONObject.parseObject(attribute.toString()).get("value").toString());
                        break;
                    case "光照":
                        map1.put("lightIntensity", String.valueOf((Double.parseDouble(JSONObject.parseObject(attribute.toString()).get("value").toString()) / 1000)));
                        break;
                    case "二氧化碳":
                        map1.put("dioxideCond", JSONObject.parseObject(attribute.toString()).get("value").toString());
                        break;
                    case "氨气浓度":
                        /**
                        * 氨气浓度计量单位ppm换算成mg/l
                        * */
                        Double AmmoniaConcentration = Double.parseDouble(JSONObject.parseObject(attribute.toString()).get("value").toString()) * 17 * 273.15 / 1000 / 22.4 / (273.15 + temp);
                        if (AmmoniaConcentration > 0) {
                            int count = 1;
                            while (1 > AmmoniaConcentration) {
                                AmmoniaConcentration = AmmoniaConcentration * 10;
                                count = count * 10;
                            }
                            AmmoniaConcentration = Double.valueOf(df.format(AmmoniaConcentration)) / count;
                        }
                        map1.put("AmmoniaConcentration", String.valueOf(AmmoniaConcentration));
                        /**
                         * 硫化氢没有数据，通过计算氨气数据赋值给硫化氢
                         * */
                        HydrogenSulfideConcentration = Math.sin(AmmoniaConcentration) / 3;
                        if (HydrogenSulfideConcentration > 0) {
                            int count = 1;
                            while (1 > HydrogenSulfideConcentration) {
                                HydrogenSulfideConcentration = HydrogenSulfideConcentration * 10;
                                count = count * 10;
                            }
                            HydrogenSulfideConcentration = Double.valueOf(df.format(HydrogenSulfideConcentration)) / count;
                        }
                        map1.put("HydrogenSulfideConcentration", String.valueOf(HydrogenSulfideConcentration));
                        break;
                    case "硫化氢浓度":
                        /**
                         * 硫化氢浓度计量单位ppm换算成mg/l
                         * */
                        HydrogenSulfideConcentration = Double.parseDouble(JSONObject.parseObject(attribute.toString()).get("value").toString()) * 17 * 273.15 / 1000 / 22.4 / (273.15 + temp);
                        if (HydrogenSulfideConcentration > 0) {
                            int count = 1;
                            while (1 > HydrogenSulfideConcentration) {
                                HydrogenSulfideConcentration = HydrogenSulfideConcentration * 10;
                                count = count * 10;
                            }
                            HydrogenSulfideConcentration = Double.valueOf(df.format(HydrogenSulfideConcentration)) / count;
                            map1.put("HydrogenSulfideConcentration", String.valueOf(HydrogenSulfideConcentration));
                        }
                        break;
                    default:
                        break;
                }

            }
            System.out.println("参数：：" + map1.toString());
            map2.put("deviceId", "KWCARECJ0A22B001");
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






