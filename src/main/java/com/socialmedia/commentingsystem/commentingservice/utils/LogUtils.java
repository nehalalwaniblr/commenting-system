package com.socialmedia.commentingsystem.commentingservice.utils;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(LogUtils.class);

    private LogUtils() {
    }

    public static void myLog(String methodName, LogGate type, String message, Object... args) {
        String logMsg = "methodName=" + methodName + ", type=" + type.name() + ", message=" + message;
        if (!type.name().equalsIgnoreCase("ERROR")) {
            log.info(logMsg, args);
        } else {
            log.error(logMsg, args);
        }

    }
}
