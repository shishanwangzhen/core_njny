package com.corewell.study.service.impl;

import com.corewell.study.dao.AgreementDao;
import com.corewell.study.domain.Agreement;
import com.corewell.study.domain.request.AgreementReq;
import com.corewell.study.domain.result.ResultMsg;
import com.corewell.study.domain.result.ResultStatusCode;
import com.corewell.study.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/17/13:31
 * @Description:
 */
@Service("AgreementService")
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    private AgreementDao agreementDao;

    @Override
    public ResultMsg findAgreement(AgreementReq agreementReq) {
        List<Agreement> agreementList = agreementDao.findAgreement(agreementReq);
        return ResultMsg.success(agreementList);
    }

    @Override
    public ResultMsg insertAgreement(Agreement agreement) {
        agreement.setCreateTime(new Date());
        agreement.setDeleteFlag("1");
        int result = agreementDao.insertAgreement(agreement);
        if (result == 1) {
            return ResultMsg.success();
        }
        return ResultMsg.error();
    }

    @Override
    public ResultMsg updateAgreement(Agreement agreement) {
        agreement.setUpdateTime(new Date());

        int result = agreementDao.updateAgreement(agreement);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.UPDATE_FAILED);
    }

    @Override
    public ResultMsg updateAgreementStatus(Long id) {
        int result = agreementDao.updateAgreementStatus(id);
        if (result == 1) {
            return ResultMsg.success();
        }
        return new ResultMsg(ResultStatusCode.DELETE_FAILED);
    }
}
