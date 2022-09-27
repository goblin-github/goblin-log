package com.goblin.log.config;

import java.util.regex.Pattern;

/**
 * @author wangpeng
 * @version jdk1.8
 */
public class SensitiveInfoConfig {

    public static Pattern getMobileConfig() {
        return PatternConfig.mobile;
    }

    public static Pattern getCardConfig() {
        return PatternConfig.card;
    }

    public static Pattern getEmailConfig() {
        return PatternConfig.email;
    }

    public static Pattern getGpsConfig() {
        return PatternConfig.gps;
    }

    public static Pattern[] getHuaWeiConfig() {
        return PatternConfig.HuaWeiPattern;
    }

    public static Pattern[] getSkipConfig() {
        return PatternConfig.skipPattern;
    }

    public static Pattern getCommonKVConfig() {
        return PatternConfig.commonKVPattern;
    }

    public static Pattern getCommonKConfig() {
        return PatternConfig.commonKPattern;
    }
}
