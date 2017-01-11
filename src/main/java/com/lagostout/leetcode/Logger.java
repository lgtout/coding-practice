package com.lagostout.leetcode;

import java.util.HashMap;

public class Logger {

    private final HashMap<String, Integer> messageToTimestampMap;

    public Logger() {
        messageToTimestampMap = new HashMap<>();
    }

    // faster
    public boolean shouldPrintMessage(int timestamp, String message) {
        int previousTimestamp = messageToTimestampMap.
                getOrDefault(message, -10);
        boolean shouldPrint = false;
        if (timestamp - previousTimestamp >= 10) {
            messageToTimestampMap.put(message, timestamp);
            shouldPrint = true;
        }
        return shouldPrint;
    }

    // slower
    public boolean shouldPrintMessage2(int timestamp, String message) {
        Integer previousTimestamp = messageToTimestampMap.get(message);
        if (previousTimestamp == null) {
            messageToTimestampMap.put(message, -10);
            previousTimestamp = -10;
        }
        boolean shouldPrint = false;
        if (timestamp - previousTimestamp >= 10) {
            messageToTimestampMap.put(message, timestamp);
            shouldPrint = true;
        }
        return shouldPrint;
    }
}
