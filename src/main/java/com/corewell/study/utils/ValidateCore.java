package com.corewell.study.utils;

import com.corewell.study.constants.BaseConstants;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2023/03/01/15:28
 * @Description:
 */
public class ValidateCore {

  /*  public static boolean verifyPhone(String phone) {
        Pattern pattern = Pattern.compile(BaseConstants.REG_PATTERN_PHONE);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }*/

    /**
     * 校验手机号
     */

    public static boolean verifyPhone(String phone) {
        return Pattern.matches(BaseConstants.REG_PATTERN_PHONE, phone);
    }


    /**
     * 校验账号
     */

    public static boolean verifyAccount(String account) {
            return Pattern.matches(BaseConstants.REG_PATTERN_ACCOUNT, account);
    }
}
