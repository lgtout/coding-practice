package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.2 page 444
 */
fun findFirstMissingPositiveEntry(array: MutableList<Int>): Int {
    var incrementedIndex = 0
    while (incrementedIndex <= array.lastIndex) {
        var entry = array[incrementedIndex]
        ++incrementedIndex
        if (entry == 0) continue
        while (true) {
            if (entry !in (1..array.size)) {
                break
            } else {
                val entryIndex = entry - 1
                entry = array[entry - 1]
                array[entryIndex] = 0
            }
        }
    }
    println(array)
    return array.foldRightIndexed(array.size + 1) {
        index, entry, acc ->
        if (entry != 0) index + 1 else acc
    }
}