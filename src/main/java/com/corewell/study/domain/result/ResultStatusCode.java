package com.corewell.study.domain.result;

/**
 * @ClassName: ResultStatusCode
 * @Description:添加返回状态枚举
 * @author: Administrator
 * @date: 2017年5月4日
 * @version:
 */
public enum ResultStatusCode {

    OK(0, "操作成功"),
    FALSE(1, "操作失败"),
    LOGIN_WIN(10001, "登录成功"),
    LOGIN_ERR(10002, "用户名或密码不正确"),
    INVALID_CLIENTID(10003, "无效的客户端"),
    INVALID_PASSWORD(10004, "用户名或密码不正确"),
    TOKEN_IS_NULL(10005, "token为空"),
    INVALID_TOKEN(10006, "无效的Token"),
    ILLEGAL_ACCOUNT(10007, "账号不合法"),
    USERNAME_PASSWORD_NULL(10008, "用户名或密码不能为空"),
    USERNAME_REGISTERED(10009, "该用户名已被注册"),
    PARAM_NULL(10010, "存在参数为空"),
    PHOTO_PATH_WRONG(10011, "图片路径有误"),
    QUERY_SAME_RESULT(10013, "查询到相同的结果"),
    ROLE_POSITION_NOT_EXIST(10014, "该请假角色不存在"),
    OLD_PASSWORD_ERROR(10015, "旧密码输入错误"),
    NO_USER(10016, "该用户不存在"),
    NO_POWER_QUERY(10017, "该用户无权查看"),
    ACCOUNT_ALL(10018, "同一账号无法操作"),
    ACCOUNT_NO(10019, "验证账号或密码错误"),
    NO_COME_TIME(10020, "实际归队时间为空，不能销假！"),
    NO_OUT_LIST(10021, "该人员无外出审批记录！"),
    BAD_WAY_ACCESS(10022, "通过门禁方式错误！"),
    ACCOUNT_NOT_LOGIN(30000, "没有登录"),
    DELETE_FALSE(333, "请先删除子部门"),
    UPLOAD_FAIL(30001, "上传失败"),
    LABEL_BOUND(30002, "标签已绑定其他载体或载体绑定过标签,请先解绑"),
    APPROVAL_STATUS(3004, "请确认审核"),
    APPROVAL_STATUS_RFID(3005, "标签与所绑定载体设备无法匹配"),
    APPROVAL_IS_ALLOW1(3006, "已逾期，请输入逾期原因"),
    APPROVAL_IS_ALLOW2(3007, "已逾期，待逾期审核"),
    ASSET_STATUS(30003, "该设备载体已不在库"),
    USER_TO_EXAMINE(30004, "账号审核中"),
    USER_ISEXIT(30005, "账号已申请，请正常登录"),
    TID_ISNOTEXIT(30006, "标签不在标签库,请先注册"),
    ASSETCODE_ISEXIST(30007, "此载体编号已存在"),
    RFID_ISNOTZHUCE(30008, "标签未注册"),
    RFID_ISNOTBIND(30009, "标签未绑定"),
    RFID_ISRELIEVE(30012, "标签已解除"),
    PLEASE_BIND(30010, "删除前请先解绑"),
    TID_ISRELIEVED(30011, "此标签已解除,无法再次绑定"),
    DELETE_FAILED(20001, "删除失败"),
    UPDATE_FAILED(30001, "修改失败"),
    FILE_IS_NOT_EXSIT(30012, "目标文件不在指定路径,请检查!");


    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    private ResultStatusCode(int Errode, String ErrMsg) {
        this.errcode = Errode;
        this.errmsg = ErrMsg;
    }

}
