package com.corewell.study.constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/30/9:51
 * @Description:
 */
public class BaseConstants {
    /**
     * TLINK还参成功码
     */
    public static final String SUCCESS_00 = "00";

    /**
     * TLINK还参码无数据
     */
    public static final String SUCCESS_01 = "01";

    /**
     * TLINK接口token异常
     */
    public static final String INVALID_TOKEN = "invalid_token";

    /**
     * 手机号正则表达式
     */

    public static final String REG_PATTERN_PHONE = "(^$)|(^(1)[0-9]{10}$)";

    /**
     * 江苏农林学号正则表达式
     */
    public static final String REG_PATTERN_ACCOUNT = "^(20)[0-9]{10}$";

}
