package com.corewell.study.utils;

import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/18/11:21
 * @Description:
 */
public class BaseUtil {
    private static final String CLIENT_ID="9b248223598b471489f75f1315a7d01b";
    private static final String SECRET="45136f83b1504761b7cba47a92b77509";
    /**
     * BASE64加密
     * */
    public static String encodedString(){
        String originalInput = CLIENT_ID+":"+SECRET;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);
        return encodedString;
    }

    public static void main(String[] args) {
        System.out.println(encodedString());
    }
}
