package com.corewell.study.config;

import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //从 http 请求头中取出 token
        log.info(request.getRequestURI());
        String token = request.getHeader("token");
        log.info("此处测试是否拿到了token：" + token);
        if (token == null) {
            throw new RuntimeException(ResultStatusCode.TOKEN_IS_NULL.getErrmsg());
        }

        //验证 token
        JwtUtil.checkSign(token);

        //验证通过后， 这里测试取出JWT中存放的数据
        //获取 token 中的 userId
        String userId = JwtUtil.getUserId(token);
        log.info("userId : " + userId);

        //获取 token 中的其他数据
        Map<String, Object> info = JwtUtil.getInfo(token);
        info.forEach((k, v) -> log.info(k + ":" + v));
        return true;
    }
}

