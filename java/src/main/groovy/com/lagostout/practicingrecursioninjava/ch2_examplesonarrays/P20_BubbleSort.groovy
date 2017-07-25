package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

class P20_BubbleSort {
    static void bubbleSort(int[] a, int n) {
        if (n == 1) return
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i-1]) {
                int temp = a[i]
                a[i] = a[i-1]
                a[i-1] = temp
            }
        }
        bubbleSort(a, n-1)
    }
}
