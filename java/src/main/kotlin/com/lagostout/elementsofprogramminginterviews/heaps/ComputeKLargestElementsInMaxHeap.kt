package com.lagostout.elementsofprogramminginterviews.heaps

/**
 * Problem 11.6 page 185
 */
fun kLargestElementsInMaxHeap(heap: List<Int>, k: Int): Set<Int> {
    var kOrHeapSize = k
    when {
        k < 0 -> throw IllegalArgumentException("K must be non-negative")
        k == 0 || heap.isEmpty() -> return emptySet()
        k > heap.size -> kOrHeapSize = heap.size
    }
    fun log2(number: Int): Int {
        return (Math.log(number.toDouble())/Math.log(2.toDouble())).toInt()
    }
    fun leftChildIndex(parentIndex: Int): Int {
        return parentIndex * 2 + 1
    }
    fun rightChildIndex(parentIndex: Int): Int {
        return leftChildIndex(parentIndex) + 1
    }
    var currentLevelIndices = setOf(0)
    val kLargestElements = mutableSetOf<Int>()
    val log2K = log2(kOrHeapSize)
    while ((kLargestElements.size + currentLevelIndices.size <= log2K)
            && (kLargestElements.size < kOrHeapSize)) {
        kLargestElements.addAll(currentLevelIndices)
        currentLevelIndices = currentLevelIndices.fold(mutableSetOf()) {
            acc, index ->
            acc.apply {
                addAll(arrayOf(leftChildIndex(index), rightChildIndex(index))
                        .filter { it < heap.size })
            }
        }
    }
    kLargestElements.addAll(
            currentLevelIndices.sortedDescending()
                    .take(kOrHeapSize - kLargestElements.size))

    return kLargestElements
}