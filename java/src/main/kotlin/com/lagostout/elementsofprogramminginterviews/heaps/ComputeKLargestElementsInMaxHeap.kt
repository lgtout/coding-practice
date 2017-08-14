package com.lagostout.elementsofprogramminginterviews.heaps

fun kLargestElementsInMaxHeap(heap: List<Int>, k: Int): Set<Int> {
    if (k < 0) throw IllegalArgumentException("K must be positive")
    if (heap.isEmpty()) return emptySet()
    fun log2(number: Int): Int {
        return (Math.log(number.toDouble())/Math.log(2.toDouble())).toInt()
    }
    fun leftChild(parentIndex: Int): Int {
        return parentIndex * 2 - 1
    }
    fun rightChild(parentIndex: Int): Int {
        return leftChild(parentIndex) + 1
    }
    var currentLevelIndices = setOf(0)
    val kLargestElements = mutableSetOf<Int>()
    while ((kLargestElements.size + currentLevelIndices.size <= log2(k))
            && (kLargestElements.size < k)) {
        kLargestElements.addAll(currentLevelIndices)
        currentLevelIndices = currentLevelIndices.fold(mutableSetOf()) {
            acc, index ->
            acc.apply {
                addAll(arrayOf(leftChild(index), rightChild(index))
                        .filter { it < heap.size })
            }
        }
    }
    kLargestElements.addAll(
            currentLevelIndices.toList().sortedDescending()
                    .take(k - kLargestElements.size))

    return kLargestElements
}