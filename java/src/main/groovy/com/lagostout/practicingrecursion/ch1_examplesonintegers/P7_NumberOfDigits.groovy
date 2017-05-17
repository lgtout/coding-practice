package com.lagostout.practicingrecursion.ch1_examplesonintegers

class P7_NumberOfDigits {

    static int numberOfDigits(int n) {
        if (n <= 9) return 1
        numberOfDigits(n.intdiv(10).intValue()) + 1
    }
    
}
