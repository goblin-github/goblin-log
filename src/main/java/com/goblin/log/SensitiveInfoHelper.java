package com.goblin.log;

import com.goblin.log.config.SensitiveInfoConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangpeng
 * @version jdk1.8
 */
public class SensitiveInfoHelper {
    private static final String KEY = "**********";

    private static final char KEY_ = '*';

    public static String filterHuaWei(String content) {
        Pattern[] commonConfig = SensitiveInfoConfig.getHuaWeiConfig();
        for (Pattern pattern : commonConfig) {
            Matcher matcher = pattern.matcher(content);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, allSensitive(matcher.group(), 2, 2));
            }
            matcher.appendTail(sb);
            content = sb.toString();
        }
        return content;
    }

    /**
     * 匹配 例如 Info xxx.xxx password:shinemo123, session:asdu1js23sdj
     *
     * @param content
     * @return
     */
    public static String filterCommonKV(String content) {
        Pattern commonKVConfig = SensitiveInfoConfig.getCommonKVConfig();
        Matcher matcher = commonKVConfig.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group();
            int i = group.indexOf(":");
            if (i == -1) {
                i = group.indexOf("=");
            }
            if (i == -1) {
                continue;
            }
            matcher.appendReplacement(sb, allSensitive(group.substring(0, i), 2, 2) + ":" + allSensitive(group.substring(i + 1), 2, 2));
        }
        matcher.appendTail(sb);
        content = sb.toString();
        return content;
    }

    public static String filterCommonK(String content) {
        Pattern commonKConfig = SensitiveInfoConfig.getCommonKConfig();
        Matcher matcher = commonKConfig.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, allSensitive(matcher.group(), 2, 2));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String skipLogs(String content) {
        Pattern[] skipConfig = SensitiveInfoConfig.getSkipConfig();
        for (Pattern pattern : skipConfig) {
            Matcher matcher = pattern.matcher(content);
            if (matcher.find()) {
                return "the log is skip";
            }
        }
        return content;
    }

    /**
     * GPS脱敏
     */
    public static String filterGps(String content) {
        Matcher matcher = SensitiveInfoConfig.getGpsConfig().matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, baseSensitive(matcher.group(), 2, 2));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 邮箱脱敏
     */
    public static String filterEmail(String content) {
        Matcher matcher = SensitiveInfoConfig.getEmailConfig().matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, baseSensitive(matcher.group(), 3, 2));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 身份证号脱敏
     */
    public static String filterCard(String content) {
        Matcher matcher = SensitiveInfoConfig.getCardConfig().matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, baseSensitive(matcher.group(), 3, 0));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 手机号码脱敏
     */
    public static String filterMobile(String content) {
        Matcher matcher = SensitiveInfoConfig.getMobileConfig().matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, baseSensitive(matcher.group(), 3, 4));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 基础脱敏处理 指定起止展示长度 剩余用"KEY"中字符替换(只替换数字)
     *
     * @param str         待脱敏的字符串
     * @param startLength 开始展示长度
     * @param endLength   末尾展示长度
     * @return 脱敏后的字符串
     */
    public static String baseSensitive(String str, int startLength, int endLength) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String replacement = str.substring(startLength, str.length() - endLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < replacement.length(); i++) {
            char ch;
            if (replacement.charAt(i) >= '0' && replacement.charAt(i) <= '9') {
                ch = KEY.charAt(replacement.charAt(i) - '0');
            } else {
                ch = replacement.charAt(i);
            }
            sb.append(ch);
        }
        // StringUtils.leftPad 左边用sb.toString()填充str.length() - startLength个字符，右边显示StringUtils.right(str, endLength)
        return StringUtils.left(str, startLength)
                .concat(StringUtils.leftPad(StringUtils.right(str, endLength), str.length() - startLength, sb.toString()));
    }

    /**
     * 基础脱敏处理 指定起止展示长度 剩余用"KEY"中字符替换（替换所有）
     *
     * @param str         待脱敏的字符串
     * @param startLength 开始展示长度
     * @param endLength   末尾展示长度
     * @return 脱敏后的字符串
     */
    public static String allSensitive(String str, int startLength, int endLength) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        if (str.length() < 5) {
            if (str.length() == 3) {
                return KEY_ + String.valueOf(str.charAt(1)) + KEY_;
            }
            return str;
        }
        String replacement = str.substring(startLength, str.length() - endLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < replacement.length(); i++) {
            char ch;
            ch = KEY_;
            sb.append(ch);
        }
        return StringUtils.left(str, startLength)
                .concat(StringUtils.leftPad(StringUtils.right(str, endLength), str.length() - startLength, sb.toString()));
    }

    /**
     * 按"KEY"中字符解密
     */
    public static String decrypt(String str, int startLength, int endLength) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String replacement = str.substring(startLength, str.length() - endLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < replacement.length(); i++) {
            int index = KEY.indexOf(replacement.charAt(i));
            if (index != -1) {
                sb.append(index);
            } else {
                sb.append(replacement.charAt(i));
            }
        }
        return StringUtils.left(str, startLength)
                .concat(StringUtils.leftPad(StringUtils.right(str, endLength), str.length() - startLength, sb.toString()));
    }
}
