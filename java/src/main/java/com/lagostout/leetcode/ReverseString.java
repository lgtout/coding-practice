package com.lagostout.leetcode;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("Duplicates")
public class ReverseString {

    public String reverseString1(String s) {
        if (s.length() == 0) return "";
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length() - 2; i >= 0; i--) {
            sb.append(sb.charAt(i));
        }
        return sb.substring(s.length()-1);
    }

    public String reverseString3(String s) {
        if (s.length() == 0) return "";
        StopWatch watch = new StopWatch();
        watch.start();
        char[] a = s.toCharArray();
        int len = a.length;
        for (int i = len - 1, j = 0; i > j; i--, j++) {
            a[i] = (char) (a[i] ^ a[j]);
            a[j] = (char) (a[i] ^ a[j]);
            a[i] = (char) (a[i] ^ a[j]);
        }
        watch.stop();
        System.out.println(watch.getTime(TimeUnit.MICROSECONDS));
        watch.reset();
        watch.start();
        String result = String.valueOf(a);
        watch.stop();
        System.out.println(watch.getTime(TimeUnit.MICROSECONDS));
        return result;
    }

    public String reverseString2(String s) {
        if (s.length() == 0) return "";
        StopWatch watch = StopWatch.createStarted();
        StringBuilder sb = new StringBuilder();
        System.out.println(watch.getTime(TimeUnit.MICROSECONDS));
        watch = StopWatch.createStarted();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(watch.getTime(TimeUnit.MICROSECONDS));
        watch = StopWatch.createStarted();
        String result = sb.toString();
        System.out.println(watch.getTime(TimeUnit.MICROSECONDS));
        watch.stop();
        return result;
    }

}
