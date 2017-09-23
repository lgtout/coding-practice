package com.lagostout.common

import org.apache.commons.math3.random.RandomDataGenerator

random = new RandomDataGenerator()

static int partition(int[] a, int l, int r) {
    int x = a[l]
    int j = l
    // j always points to the rightmost element, in the examined left
    // segment of the array, that's <= the pivot.
    // i is the current element to examine, the rightmost element
    // of the examined segment of the array.
    // In essence, partition grows the partitioned segment from a
    // subarray containing just the pivot and occupying the first
    // position in the array, to the extent of the entire array.
    for (int i = l + 1; i <= r; i++) {
        if (a[i] <= x) {
            j++
            println "swapping a[$i] and a[$j]"
            // Doesn't need to swap when i == j
            int t = a[i]
            a[i] = a[j]
            a[j] = t
            println "$a"
        }
    }
    a[l] = a[j]
    a[j] = x
    return j
}

void randomizedQuickSort(int[] a, int l, int r) {
    if (l >= r) {
        return
    }
    int k = random.nextInt(l, r)
    println "left $l right $r pivot $k "
    println "before partition $a"
    int t = a[l]
    a[l] = a[k]
    a[k] = t
    int m = partition(a, l, r)
    println "after partition $a"
    randomizedQuickSort(a, l, m - 1)
    randomizedQuickSort(a, m + 1, r)
}

numbers = [2,1,9,2,0,6] as int[]
randomizedQuickSort(numbers, 0, numbers.size() - 1)
