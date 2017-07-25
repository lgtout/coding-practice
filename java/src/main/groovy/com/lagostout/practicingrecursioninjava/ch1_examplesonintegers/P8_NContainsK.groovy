package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P8_NContainsK {

    static boolean nContainsK(int n, int k) {
        if (n < 10) return n == k
        return (n % 10) == k || nContainsK(n.intdiv(10).intValue(), k)
    }

}
