package com.company.decathlon.utils;

import java.util.logging.Logger;

/**
 * Created by zygis on 27/09/2015.
 */
public class AbstractLogger {

    protected Logger logger() {
        return Logger.getLogger(getClass().getName());
    }

    protected void logInfo(String message) {
        logger().info(message);
    }
}
