package com.lagostout.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {

    private final int maxWindowSize;
    private Deque<Integer> deque = new ArrayDeque<>();
    private int sum;

    public MovingAverage(int size) {
        this.maxWindowSize = size;
    }

    public double next(int val) {
        int first = 0;
        if (deque.size() >= maxWindowSize) {
            first = deque.removeFirst();
        }
        deque.addLast(val);
        sum = (sum - first) + val;
        return sum/(double) deque.size();
    }

}
