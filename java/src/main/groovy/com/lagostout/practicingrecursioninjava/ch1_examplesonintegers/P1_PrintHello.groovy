package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P1_PrintHello {

    static String printHello(int n) {
        if (n == 1) return "Hello_${n}_"
        return printHello(n-1) + "Hello_${n}_"
    }

    static String printHello_moreRecursion(int n) {
        if (n == 0) return ""
        return printHello_moreRecursion(n-1) + "Hello_${n}_"
    }

}
