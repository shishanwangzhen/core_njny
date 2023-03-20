package com.corewell.study.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取32位的UUID作为主键
     *
     * @return
     */
    public static String get32uuid() {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    // 生成4位随机数字+字母,
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    //测试
    public static void main(String[] args) {
        System.out.println(getStringRandom(4) + "\n" +
                getStringRandom(4) + "\n" +
                getStringRandom(4) + "\n" +
                getStringRandom(8) + "\n" +
                getStringRandom(8) + "\n" +
                getStringRandom(8) + "\n");


    }
}
