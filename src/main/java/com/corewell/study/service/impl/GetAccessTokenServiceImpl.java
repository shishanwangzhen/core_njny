package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.corewell.study.constants.BaseRedisKeyConstants;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.GetAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/30/10:43
 * @Description:
 */
@Service("GetAccessTokenService")
public class GetAccessTokenServiceImpl implements GetAccessTokenService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final String LOGIN_URL = "https://app.dtuip.com/oauth/token?grant_type=password&username=bydwadmin&password=bydwadmin123";
    String accessToken = null;
    HttpHeaders headers = new HttpHeaders();

    @Override
    public ResultMsg getAccessToken() {
        try {
            headers.clear();
            headers.add("authorization", "Basic NGI4NDIwZDRkYzk3NGZkNDgyODUwODZkMDkwMjJmOWI6YzliM2RjYjBkNjcxNDE0YTg2Mjg2ZmQyZDNmMGM2N2I=");
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(LOGIN_URL, new HttpEntity<Map>(null, headers), String.class);
            JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            accessToken = jsonObject.get("access_token").toString();
            stringRedisTemplate.opsForValue().set(BaseRedisKeyConstants.ACCESS_TOKEN_KEY, accessToken);
        } catch (Exception e) {
            System.out.println(e);
        }

        return ResultMsg.success();
    }
}
