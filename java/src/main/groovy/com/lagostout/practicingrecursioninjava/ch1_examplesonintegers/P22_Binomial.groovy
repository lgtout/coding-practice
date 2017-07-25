package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P22_Binomial {
    static public int binomial(int n, int k) {
        if (k == 0 || n == k) return 1
        return binomial(n - 1, k - 1) + binomial(n - 1, k)
    }
}
