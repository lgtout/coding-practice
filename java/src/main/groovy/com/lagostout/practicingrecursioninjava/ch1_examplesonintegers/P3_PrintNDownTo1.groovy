package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P3_PrintNDownTo1 {

    static List<Integer> printNDownTo1(int n) {
        if (n == 1) return [n]
        return [n] + printNDownTo1(n-1)
    }

}
