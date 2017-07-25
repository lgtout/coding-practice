package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P29_PrintAExpTimes {
    static String printAExpTimes(int n) {
        if (n == 0) return 'A'
        return printAExpTimes(n-1) + printAExpTimes(n-1)
    }
}
