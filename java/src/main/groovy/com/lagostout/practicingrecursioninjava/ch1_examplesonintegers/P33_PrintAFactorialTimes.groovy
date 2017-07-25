package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P33_PrintAFactorialTimes {
    static String printAFactorialTimes(int n) {
        if (n == 1) return 'A'
        String result = ''
        for (int i = 0; i < n; i++) {
            result += printAFactorialTimes(n-1)
        }
        result
    }
}
