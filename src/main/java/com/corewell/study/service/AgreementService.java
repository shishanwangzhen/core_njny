package com.corewell.study.service;

import com.corewell.study.domain.Agreement;
import com.corewell.study.domain.request.AgreementReq;
import com.corewell.study.domain.result.ResultMsg;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
public interface AgreementService {
    /**
     * 查询项目
     *
     * @param agreementReq
     * @return
     */
    ResultMsg findAgreement(AgreementReq agreementReq);

    /**
     * 新增项目
     *
     * @param agreement
     * @return
     */
    ResultMsg insertAgreement(Agreement agreement);

    /**
     * 修改项目
     *
     * @param agreement
     * @return
     */
    ResultMsg updateAgreement(Agreement agreement);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    ResultMsg updateAgreementStatus(@Param("id") Long id);


}
