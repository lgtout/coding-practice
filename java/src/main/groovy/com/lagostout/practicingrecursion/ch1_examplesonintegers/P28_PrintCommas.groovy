package com.lagostout.practicingrecursion.ch1_examplesonintegers

class P28_PrintCommas {
    static String printCommas(long n) {
        if (n < 1000) n
        else {
            long nextN = n / 1000 as long
            String result = printCommas(nextN) +
                    (nextN > 0 ? ',' : '')
            int end = (n % 1000)
            if (end < 100) result += '0'
            if (end < 10) result += '0'
            result + end
        }
    }
}
