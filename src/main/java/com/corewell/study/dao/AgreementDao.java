package com.corewell.study.dao;

import com.corewell.study.domain.Agreement;
import com.corewell.study.domain.request.AgreementReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/02/16:06
 * @Description:
 */
@Mapper
public interface AgreementDao {
    /**
     * 查询项目
     *
     * @param agreementReq
     * @return
     */
    List<Agreement> findAgreement(AgreementReq agreementReq);

    /**
     * 新增项目
     *
     * @param agreement
     * @return
     */
    int insertAgreement(Agreement agreement);

    /**
     * 修改项目
     *
     * @param agreement
     * @return
     */
    int updateAgreement(Agreement agreement);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    int updateAgreementStatus(@Param("id") Long id);


}
