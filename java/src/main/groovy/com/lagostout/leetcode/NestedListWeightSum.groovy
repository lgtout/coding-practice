package com.lagostout.leetcode

import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

// https://leetcode.com/problems/nested-list-weight-sum/

class NestedListWeightSum {
    static class NestedInteger {

        private Integer integer
        private List<NestedInteger> list;

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger() {
            return integer != null
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger() {
            return integer
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList() {
            return list
        }

        NestedInteger(List<NestedInteger> list) {
            this.list = list
        }

        NestedInteger(int integer) {
            this.integer = integer
        }

        @Override
        String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE)
        }

    }

    int recursiveDepthSum(List<NestedInteger> nestedIntegers, int depth) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                sum += (nestedInteger.getInteger() * depth);
            } else {
                sum += recursiveDepthSum(nestedInteger.getList(), depth + 1);
            }
        }
        return sum;
    }

    int fasterRecursiveDepthSum(List<NestedInteger> nestedIntegers, int depth) {
        if (nestedIntegers.isEmpty()) return 0;
        int nestedIntSum = 0, intSum = 0;
        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                intSum += nestedInteger.getInteger();
            } else {
                nestedIntSum += recursiveDepthSum(nestedInteger.getList(), depth + 1);
            }
        }
        return nestedIntSum + intSum * depth;
    }

    int depthSum(List<NestedInteger> nestedIntegers) {
//        fasterRecursiveDepthSum(nestedIntegers, 1);
        breadthFirstDepthSum(nestedIntegers)
    }

    static int breadthFirstDepthSum(List<NestedInteger> nestedIntegers) {
        if (nestedIntegers.isEmpty()) return 0;
        Queue<NestedInteger> queue = new LinkedList<>(nestedIntegers);
        int depth = 0;
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size()
            depth++
            for (int i = 0; i < size; i++) {
                NestedInteger nestedInteger = queue.remove();
                if (nestedInteger.isInteger()) {
                    sum += nestedInteger.getInteger() * depth;
                } else (
                    queue.addAll(nestedInteger.getList())
                )
            }
        }
        return sum;
    }

    int depthFirstDepthSum(List<NestedInteger> nestedIntegers) {
        Deque<NestedInteger> integersStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        int depth = 1;
        for (NestedInteger nestedInteger : nestedIntegers) {
            integersStack.push(nestedInteger);
            depthStack.push(depth);
        }
        int sum = 0
        while (!integersStack.isEmpty()) {
            NestedInteger nestedInteger = integersStack.pop();
            depth = depthStack.pop();
            if (nestedInteger.isInteger()) {
                sum += depth * nestedInteger.getInteger();
            } else {
                ++depth;
                for (NestedInteger ni : nestedInteger.getList()) {
                    integersStack.push(ni);
                    depthStack.push(depth);
                }
            }
        }
        return sum;
    }

}
