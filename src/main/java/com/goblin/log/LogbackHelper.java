package com.goblin.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import static com.goblin.log.constant.LogConfigurationKey.LOG_SENSITIVE;

/**
 * @author wangpeng
 * @version jdk1.8
 */

public class LogbackHelper extends MessageConverter {
    public static boolean SENSITIVE = Boolean.parseBoolean(System.getProperty(LOG_SENSITIVE));

    @Override
    public String convert(ILoggingEvent event) {
        if (event.getLevel().levelInt <= Level.DEBUG.levelInt) {
            return event.getFormattedMessage();
        }
        // 获取原始日志
        String content = event.getFormattedMessage();
        if (content == null || ("").equals(content.trim())) {
            return content;
        }
        if (SENSITIVE) {
            // 先统一公共的过滤
            content = SensitiveInfoHelper.skipLogs(content);
            content = SensitiveInfoHelper.filterCommonKV(content);
            content = SensitiveInfoHelper.filterCommonK(content);
            // 根据值的具体匹配规则过滤
            content = SensitiveInfoHelper.filterMobile(content);
            content = SensitiveInfoHelper.filterGps(content);
            content = SensitiveInfoHelper.filterEmail(content);
            content = SensitiveInfoHelper.filterCard(content);
            // 华为提供的统一处理的正则，放在最后处理（因为是他们后面提供的，会影响之前写的一些匹配）
            content = SensitiveInfoHelper.filterHuaWei(content);
        }
        return content;
    }

}
