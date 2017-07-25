package com.lagostout.practicingrecursioninjava.ch2_examplesonarrays

class P18_SelectionSort {

    static void selectionSort(int[] a, int n) {
        if (n <= 1) return
        int maxIndex = n-1
        for (int i = 0; i < n-1; i++) {
           if (a[i] > a[maxIndex]) maxIndex = i
        }
        if (maxIndex != n-1) {
            int temp = a[n-1]
            a[n-1] = a[maxIndex]
            a[maxIndex] = temp
        }
        selectionSort(a, n-1)
    }

}
