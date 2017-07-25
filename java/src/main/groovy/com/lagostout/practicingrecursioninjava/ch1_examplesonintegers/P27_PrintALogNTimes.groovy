package com.lagostout.practicingrecursioninjava.ch1_examplesonintegers

class P27_PrintALogNTimes {

    static String printALogNTimes(int n) {
        if (n < 2) return ''
        return 'A' + printALogNTimes(n.intdiv(2) as int)
    }

}
