package com.corewell.study.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corewell.study.domain.Agreement;
import com.corewell.study.domain.request.AgreementReq;
import com.corewell.study.domain.request.SensorHistoryParam;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.service.AgreementService;
import com.corewell.study.utils.InfluxDbUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/10/31/14:41
 * @Description:
 */
@RestController
@RequestMapping("/core/agreement")
@Api(tags = "协议控制器")
@ApiIgnore
@Slf4j
public class AgreementController {
    @Resource
    private AgreementService agreementService;

    @ApiOperation(value = "查询项目", response = Agreement.class)
    @ApiIgnore//隐藏接口
    @PostMapping("/findAgreement")
    public ResultMsg findAgreement(@RequestBody AgreementReq agreementReq) {
        ResultMsg resultMsg = agreementService.findAgreement(agreementReq);
        return resultMsg;

    }

    @ApiOperation("查询历史数据")
    @PostMapping("/findData")
    public ResultMsg findData(@RequestBody SensorHistoryParam sensorHistoryParam) {
      /*  StringBuilder command = new StringBuilder();
        command.append("SELECT time,value FROM CORE_NJNY where 1=1");
        if (sensorHistoryParam.getSensorId()!=null&&sensorHistoryParam.getSensorId()!=0){
            command.append(" AND sensorsId=");
            command.append("'");
            command.append(sensorHistoryParam.getSensorId());
            command.append("'");
        }
        if (StringUtils.isNotBlank(sensorHistoryParam.getStartDate())) {
            command.append(" AND time>");
            command.append("'");
            command.append(sensorHistoryParam.getStartDate());
            command.append("'");
        }
        if (StringUtils.isNotBlank(sensorHistoryParam.getEndDate())) {
            command.append(" AND time<");
            command.append("'");
            command.append(sensorHistoryParam.getEndDate());
            command.append("'");
        }
        //TODO
       // command.append("GROUP BY *");

        QueryResult resultMsg = query(command.toString());*/
        StringBuilder command = new StringBuilder();
        command.append("SELECT reVal FROM CORE_NJNY where sensorsId=");
        command.append("'");
        command.append(sensorHistoryParam.getSensorId());
        command.append("'");
        command.append(" order by time desc limit 3");
        QueryResult resultMsg = influxDbUtils.getInfluxDB().query(new Query(command.toString(), "test"));

        log.info("查询历史数据还参：resultMsg" + JSONObject.toJSON(resultMsg));
        return ResultMsg.success(resultMsg);
    }

    @Autowired
    private InfluxDbUtils influxDbUtils;

    public QueryResult query(String command) {
        return influxDbUtils.getInfluxDB().query(new Query(command, "test"));
    }

    @ApiOperation(value = "协议修改", hidden = true)
    @PostMapping("/updateAgreement")
    public ResultMsg updateAgreement(@RequestBody Agreement agreement) {
        System.out.println(JSON.toJSON(agreement));
        ResultMsg resultMsg = agreementService.updateAgreement(agreement);
        return resultMsg;

    }

    @ApiOperation("协议新增")
    @PostMapping("/insertAgreement")
    public ResultMsg insertAgreement(@RequestBody Agreement agreement) {
        System.out.println(JSON.toJSON(agreement));
        ResultMsg resultMsg = agreementService.insertAgreement(agreement);
        return resultMsg;

    }

    @ApiOperation("协议删除")
    @PostMapping("/updateAgreementStatus")
    public ResultMsg updateAgreementStatus(Long id) {
        ResultMsg resultMsg = agreementService.updateAgreementStatus(id);
        return resultMsg;

    }

}
