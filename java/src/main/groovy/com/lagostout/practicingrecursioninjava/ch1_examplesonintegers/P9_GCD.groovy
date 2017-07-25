package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P9_GCD {

    static public int gcd(int m, int n) {
        if (m % n == 0) return n;
        return gcd(n, m % n)
    }

}
