package com.corewell.study.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corewell.study.annotation.AddLog;
import com.corewell.study.config.UserRequest;
import com.corewell.study.domain.Log;
import com.corewell.study.domain.response.AccountDo;
import com.corewell.study.service.LogService;
import com.corewell.study.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2023/02/25/11:29
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class AddLogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.corewell.study.annotation.AddLog)")
    public void addLogCut() {

    }

    @Before(value = "addLogCut()")
    public Object before(JoinPoint point) {
        log.info("====================AddLogAspect before come in start====================");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        AddLog addLog = method.getAnnotation(AddLog.class);
        Object interfaceType = AnnotationResolver.newInstance().resolver(point, addLog.interfaceType());
        Object interfaceName = AnnotationResolver.newInstance().resolver(point, addLog.interfaceName());
        Object interfaceInfo = AnnotationResolver.newInstance().resolver(point, addLog.interfaceInfo());
        Object dataId = AnnotationResolver.newInstance().resolver(point, addLog.dataId());

        log.info("切面日志入参：interfaceInfo：" + JSON.toJSONString(interfaceInfo) + "  interfaceName：" + JSON.toJSONString(interfaceName) + "  interfaceType：" + JSON.toJSONString(interfaceType)+ "  interfaceType：" + JSON.toJSONString(dataId));
        String token = UserRequest.getCurrentToken();
        String account = JwtUtil.getUserId(token);
        Map<String, Object> map = JwtUtil.getInfo(token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        AccountDo accountDo = jsonObject.toJavaObject(AccountDo.class);
        log.info("请求头 ：token：" + JSON.toJSONString(token) );
        log.info("token解密用户信息：accountDo：" + JSON.toJSONString(accountDo));
        Log log = new Log();
        log.setAccount(account);
        log.setAccountId(Long.valueOf(map.get("id").toString()));
        log.setAccountName(map.get("name").toString());
        log.setCreateTime(new Date());
        log.setInterfaceType(Long.valueOf(interfaceType.toString()));
        log.setInterfaceName(interfaceName.toString());
        log.setInterfaceInfo(interfaceInfo.toString());
        if (StringUtils.isNotBlank(dataId.toString())){
            log.setDataId(Long.valueOf(dataId.toString()));
        }
        logService.insertLog(log);
        return null;
    }
}
