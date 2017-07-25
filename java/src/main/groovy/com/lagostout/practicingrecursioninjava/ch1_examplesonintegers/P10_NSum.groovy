package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P10_NSum {

    static public int nSum(int n) {
        if (n == 1) return 1
        n + nSum(n-1)
    }

}
