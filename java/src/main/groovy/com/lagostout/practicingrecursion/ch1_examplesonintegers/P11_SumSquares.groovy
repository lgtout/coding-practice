package com.lagostout.practicingrecursion.ch1_examplesonintegers

class P11_SumSquares {

    static public int sumSquares(int n) {
        if (n == 1) return 1
        n * n + sumSquares(n-1)
    }

}
