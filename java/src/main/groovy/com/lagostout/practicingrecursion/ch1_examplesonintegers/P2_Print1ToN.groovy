package com.lagostout.practicingrecursion.ch1_examplesonintegers

class P2_Print1ToN {

    static List<Integer> print1ToN(int n) {
        if (n == 1) return [1]
        return print1ToN(n-1) + [n]
    }

}
