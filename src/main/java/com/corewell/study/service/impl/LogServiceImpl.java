package com.corewell.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.corewell.study.dao.LogDao;
import com.corewell.study.domain.Log;
import com.corewell.study.domain.request.LogReq;
import com.corewell.study.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2023/02/25/13:32
 * @Description:
 */
@Service("LogService")
@Slf4j
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public List<Log> findLog(LogReq logReq) {
        log.info("findLog:  logReq:  " + JSON.toJSONString(logReq));
        List<Log> logList = logDao.findLog(logReq);
        return logList;
    }

    @Override
    public int insertLog(Log log) {
        int result = logDao.insertLog(log);
        return result;
    }
}
