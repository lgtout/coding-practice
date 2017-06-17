package com.lagostout.common

static void randomShuffle(int[] ar) {
    Random rnd = new Random()
    for (int i = ar.length - 1; i > 0; i--) {
        int index = rnd.nextInt(i + 1)
        int a = ar[index]
        ar[index] = ar[i]
        ar[i] = a
    }
}

static int[] build(String text) {
    int [] order = new int[text.length()]
    for (int i = 0; i < order.length; i++) {
        order[i] = i
    }
    randomShuffle(order)
    sort(text, order, 0, text.length() - 1)
    return order
}

static private void sort(String text, int[] order, int lo, int hi) {
    if (lo >= hi) return
    int k = partition(text, order, lo, hi)
    sort(text, order, lo, k - 1)
    sort(text, order, k + 1, hi)
}

static private int partition(String text, int[] order, int lo, int hi) {
    int i = lo
    int j = hi + 1
    String s = text.substring(order[lo])
    while (true) {
        // We don't need <= in the comparison because two suffixes
        // of the same string can never be equal.  At the very least,
        // they will be of different lengths.
        while (s.compareTo(text.substring(order[++i])) > 0) {
            if (i == hi) break
        }
        // Same explanation as above for why we don't test for why
        // we don't need >= in the comparison.
        while (s.compareTo(text.substring(order[--j])) < 0) {
            if (j == lo) break
        }
        if (i >= j) break
        swap(order, i, j)
    }
    swap(order, j, lo)
    return j
}

static private void swap(int[] order, int i, int j) {
    int temp = order[i]
    order[i] = order[j]
    order[j] = temp
}

def suffixArray = build("AAA")


