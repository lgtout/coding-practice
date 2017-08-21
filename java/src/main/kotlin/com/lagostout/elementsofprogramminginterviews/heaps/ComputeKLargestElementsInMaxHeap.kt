package com.lagostout.elementsofprogramminginterviews.heaps

/**
 * Problem 11.6 page 185
 */
fun kLargestElementsInMaxHeap(heap: List<Int>, k: Int): Set<Int> {
    when {
        k < 0 -> throw IllegalArgumentException("K must be non-negative")
        k == 0 || heap.isEmpty() -> return emptySet()
    }
    val largestElementsCount = listOf(k, heap.size).min()!!
    fun log2Floor(number: Int): Int {
        return (Math.log(number.toDouble())/Math.log(2.toDouble())).toInt()
    }
    fun leftChildIndex(parentIndex: Int): Int {
        return parentIndex * 2 + 1
    }
    fun rightChildIndex(parentIndex: Int): Int {
        return leftChildIndex(parentIndex) + 1
    }
    var currentLevelIndices = setOf(0)
    val kLargestElementsIndices = mutableSetOf<Int>()
    val nextToLastLevelOfLargestElements = log2Floor(largestElementsCount)
    while (currentLevelIndices.isNotEmpty() &&
            log2Floor(kLargestElementsIndices.size + currentLevelIndices.size) <
                    nextToLastLevelOfLargestElements) {
        kLargestElementsIndices.addAll(currentLevelIndices)
        currentLevelIndices = currentLevelIndices.fold(mutableSetOf()) {
            acc, index ->
            acc.apply {
                addAll(arrayOf(leftChildIndex(index), rightChildIndex(index))
                        .filter { it < heap.size })
            }
        }
    }
    kLargestElementsIndices.addAll(
            currentLevelIndices.sortedDescending()
                    .take(largestElementsCount - kLargestElementsIndices.size))

    return kLargestElementsIndices.map {
        heap[it]
    }.toSet()
}