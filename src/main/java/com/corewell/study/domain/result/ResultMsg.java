package com.corewell.study.domain.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ResultMsg
 * @Description: 封装返回的结果
 * @author: Administrator
 * @date: 2017年5月4日
 * @version:1.0
 */

/**
 * @description: 修改成了泛型容器，这样才能再swagger中看到具体的model属性
 * @author: yanglimou
 * @time: 2020/5/14 15:09
 */
@ApiModel("通用返回对象")
@Data
public class ResultMsg<T> {
    @ApiModelProperty(value = "错误码", example = "0")
    private int errcode;
    @ApiModelProperty(value = "错误信息", example = "操作成功")
    private String errmsg;
    @ApiModelProperty(value = "数据", example = "")
    private T p2pdata;

    public ResultMsg(int ErrCode, String ErrMsg, T P2pData) {
        this.errcode = ErrCode;
        this.errmsg = ErrMsg;
        this.p2pdata = P2pData;
    }

    public ResultMsg(ResultStatusCode rsc, T P2pData) {
        this.errcode = rsc.getErrcode();
        this.errmsg = rsc.getErrmsg();
        this.p2pdata = P2pData;
    }

    public ResultMsg(ResultStatusCode rsc) {
        this.errcode = rsc.getErrcode();
        this.errmsg = rsc.getErrmsg();
        this.p2pdata = null;
    }

    public ResultMsg(boolean res) {
        if (res) {
            this.errcode = ResultStatusCode.OK.getErrcode();
            this.errmsg = ResultStatusCode.OK.getErrmsg();
            this.p2pdata = null;
        } else {
            this.errcode = ResultStatusCode.FALSE.getErrcode();
            this.errmsg = ResultStatusCode.FALSE.getErrmsg();
            this.p2pdata = null;
        }
    }

    public static ResultMsg success() {
        return new ResultMsg(true);
    }

    public static <T> ResultMsg<T> success(T data) {
        ResultMsg resultMsg = success();
        resultMsg.setP2pdata(data);
        return resultMsg;
    }

    public static ResultMsg error() {
        return new ResultMsg(false);
    }

    public static ResultMsg error(int errcode, String errmsg) {
        ResultMsg error = error();
        error.setErrcode(errcode);
        error.setErrmsg(errmsg);
        return error;
    }
}  
