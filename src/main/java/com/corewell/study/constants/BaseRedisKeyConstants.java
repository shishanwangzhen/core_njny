package com.corewell.study.constants;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/11/30/9:51
 * @Description:
 */
public class BaseRedisKeyConstants {
    /**
     * Redis普通键前缀
     */
    public static final String REDIS_KEY_PRE = "core:study:";

    /**
     * token键
     */
    public static final String ACCESS_TOKEN_KEY = REDIS_KEY_PRE + "access:" + "ACCESS_TOKEN";

    /**
     * 传感器名称键
     */
    public static final String SENSOR_KEY = REDIS_KEY_PRE + "sensor:";

    /**
     * 设备名称键
     */
    public static final String DEVICE_KEY = REDIS_KEY_PRE + "device:";

    /**
     * 设备在线名称键
     */
    public static final String DEVICE_IS_LINE_KEY = REDIS_KEY_PRE + "device:" + "isLine:";


}
