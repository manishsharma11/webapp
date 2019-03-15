package com.main.sts.util;

public class LogUtil {

    public static void logActivity(String logMessage, int commuter_id) {
        // "Added to Transaction history for commuter:" + commuter_id

        // [commuter:commuter_id] Added to Transaction history

        String customMsg = "[commuterId:%s]";
        System.out.println(String.format(customMsg, commuter_id) + "-" + logMessage);
    }

    public static void main(String[] args) {
        LogUtil.logActivity("Recharge successful", 80);
        LogUtil.logActivity("Transaction failed", 80);
    }
}
