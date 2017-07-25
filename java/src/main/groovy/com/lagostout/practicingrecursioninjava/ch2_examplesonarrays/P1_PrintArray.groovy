package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

class P1_PrintArray {
    static String printArray(int[] a, int n) {
        if (n == 1) return a[0]
        return printArray(a, n-1) + ' ' + a[n-1]
    }
}
