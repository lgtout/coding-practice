package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P24_ConvertNB {
    static String convert(int n, int b) {
        if (n < b) n
        else convert(n.intdiv(b) as int, b) + (n % b)
    }
}
