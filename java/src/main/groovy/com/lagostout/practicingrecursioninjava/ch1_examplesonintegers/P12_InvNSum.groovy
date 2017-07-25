package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P12_InvNSum {
    static public double invNSum(int n) {
        if (n == 1) return 1
        1D / n + invNSum(n-1)
    }
}
