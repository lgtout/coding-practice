package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.common.swap

/* Problem 16.3.2 page 294 */

/* We use an approach that is time/space superior to these other
ways of preventing duplicates:
1. Adding each computed permutation to a set.
2. Comparing the permutation prefix computed so far with prefixes
of the same length of all completed permutations. */

@Suppress("NAME_SHADOWING")
fun permutationsWhenDuplicatesMayBePresent(items: List<Int>): List<List<Int>> {
    if (items.isEmpty()) return emptyList()
    val lastIndex = items.lastIndex
    val items = items.toMutableList()
    fun compute(startIndex: Int, permutation: MutableList<Int>,
                result: MutableList<List<Int>>) {
        if (startIndex > lastIndex) {
            result.add(permutation.toList())
            return
        }
        val usedItems = mutableSetOf<Int>()
        for (index in startIndex..items.lastIndex) {
            val item = items[index]
            if (item in usedItems) continue
            permutation.add(item)
            usedItems.add(item)
            items.swap(index, startIndex)
            compute(startIndex + 1, permutation, result)
            permutation.removeAt(permutation.lastIndex)
            items.swap(startIndex, index)
        }
    }
    val result = mutableListOf<List<Int>>()
    compute(0, mutableListOf(), result)
    return result
}
