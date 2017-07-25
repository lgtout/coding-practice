package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P23_Convert {
    static String convert(int n) {
        if (n < 2)
            n
        else
            convert(n.intdiv(2) as int) + (n % 2)
    }
}
