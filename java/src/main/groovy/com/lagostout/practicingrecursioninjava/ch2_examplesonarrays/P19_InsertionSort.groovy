package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

class P19_InsertionSort {
    static void insertionSort(int[] a, int n) {
        if (n <= 1) return
        insertionSort(a, n-1)
        int i = n-1
        int temp = a[i]
        while (i > 0 && a[i-1] > temp) {
            a[i] = a[i-1]
            i--
        }
        if (i != n-1)
            a[i] = temp
    }
}
