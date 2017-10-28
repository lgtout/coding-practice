package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.2.2 page 194
 */
// Is this the fastest solution?
fun findArrayEntryEqualToItsIndexWhenThereMayBeDuplicates(
        array: List<Int>): Int? {
    var entryEqualToIndex: Int? = null
    if (array.isEmpty()) return entryEqualToIndex
    var currentIndex = 0
    loop@ while (currentIndex <= array.lastIndex) {
        val entry = array[currentIndex]
        when {
            entry == currentIndex -> {
                entryEqualToIndex = entry
                break@loop
            }
            entry < currentIndex -> ++currentIndex
            else -> currentIndex = entry
        }
    }
    return entryEqualToIndex
}