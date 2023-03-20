package com.corewell.study.service;

import com.corewell.study.domain.request.PushDataParam;

/**
 * @author 863586395
 */
public interface PushDataService {
    /**
     * 处理订阅数据
     *
     * @param pushData
     * @return
     */
    void getPushData(PushDataParam pushData);
}
