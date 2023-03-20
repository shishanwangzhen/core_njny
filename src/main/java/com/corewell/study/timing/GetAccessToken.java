package com.corewell.study.timing;

import com.alibaba.fastjson.JSONObject;
import com.corewell.study.constants.BaseRedisKeyConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/08/14:28
 * @Description: 将tlink传感器数据同步到省农业平台
 */
@Component
@Slf4j
public class GetAccessToken {

    private static String accessToken;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String LOGIN_URL = "https://app.dtuip.com/oauth/token?grant_type=password&username=njny&password=corewell2023";
    HttpHeaders headers = new HttpHeaders();


    public String getAccessToken() {
        try {
            if (stringRedisTemplate.hasKey(BaseRedisKeyConstants.ACCESS_TOKEN_KEY)) {
                accessToken = stringRedisTemplate.opsForValue().get(BaseRedisKeyConstants.ACCESS_TOKEN_KEY);
                if (StringUtils.isNotBlank(accessToken)) {
                    return accessToken;
                }
            }
            headers.clear();
            headers.add("authorization", "Basic OWIyNDgyMjM1OThiNDcxNDg5Zjc1ZjEzMTVhN2QwMWI6NDUxMzZmODNiMTUwNDc2MWI3Y2JhNDdhOTJiNzc1MDk=");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(LOGIN_URL, new HttpEntity<Map>(null, headers), String.class);
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            Long expiresIn = jsonObject.getLong("expires_in");
            accessToken = jsonObject.get("access_token").toString();
            stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.ACCESS_TOKEN_KEY, accessToken, expiresIn, TimeUnit.SECONDS);
            log.info("GetAccessToken获取tlink的access_token值为：" + stringRedisTemplate.opsForValue().get(BaseRedisKeyConstants.ACCESS_TOKEN_KEY));
        } catch (Exception e) {
            log.error(e.toString());
        }
        return accessToken;
    }

    public String getNewAccessToken() {
        try {
            headers.clear();
            headers.add("authorization", "Basic NjIxZDM1ODYwY2NlNDUxYTg4NmI5MzI5YWZmYjUyYzY6Mzk0MDA5YWI2ZjQxNGMxNGE3ZTYyZjlmZGRmOTM2OWI=");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(LOGIN_URL, new HttpEntity<Map>(null, headers), String.class);
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            Long expiresIn = jsonObject.getLong("expires_in");
            accessToken = jsonObject.get("access_token").toString();
            stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.ACCESS_TOKEN_KEY, accessToken, expiresIn, TimeUnit.SECONDS);
            log.info("GetAccessToken获取tlink的access_token值为：" + stringRedisTemplate.opsForValue().get(BaseRedisKeyConstants.ACCESS_TOKEN_KEY));
        } catch (Exception e) {
            log.error(e.toString());
        }
        return accessToken;
    }


}






