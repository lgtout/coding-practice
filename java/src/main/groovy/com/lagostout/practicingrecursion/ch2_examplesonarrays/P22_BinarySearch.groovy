package com.lagostout.practicingrecursion.ch2_examplesonarrays

class P22_BinarySearch {
    static int _binarySearch(int[] a, int key, int left, int right) {
        if (left > right) return right
        int mid = (left + right)/2
        if (a[mid] == key) return mid
        else if (a[mid] < key) return binarySearch(a, key, mid+1, right)
        else return binarySearch(a, key, left, mid-1)
    }

    static int binarySearch(int[] a, int key, int left, int right) {
        if (left > right) return right
        int mid = (left + right)/2
        if (a[mid] == key) return mid
        else if (a[mid] > key) return binarySearch(a, key, left, mid-1)
        else return binarySearch(a, key, mid+1, right)
    }

}
