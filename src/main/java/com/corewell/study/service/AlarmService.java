package com.corewell.study.service;

import com.corewell.study.domain.request.AlarmActiveParam;
import com.corewell.study.domain.request.AlarmAddParam;
import com.corewell.study.domain.request.AlarmReq;
import com.corewell.study.domain.request.AlarmUpdateParam;
import com.corewell.study.domain.result.ResultMsg;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface AlarmService {
    /**
     * 添加触发器
     *
     * @param alarmAddParam
     * @return
     */
    ResultMsg addAlarms(AlarmAddParam alarmAddParam);

    /**
     * 修改触发器
     *
     * @param alarmUpdateParam
     * @return
     */
    ResultMsg updateAlarms(AlarmUpdateParam alarmUpdateParam);

    /**
     * 删除触发器
     *
     * @param id
     * @return
     */
    ResultMsg deleteAlarms(Long id);

    /**
     * 获取触发器列表
     *
     * @param alarmReq
     * @return
     */
    ResultMsg getAlarms(AlarmReq alarmReq);

    /**
     * 启动/停止触发器
     *
     * @param alarmActiveParam
     * @return
     */
    ResultMsg activeAlarms(AlarmActiveParam alarmActiveParam);


}
