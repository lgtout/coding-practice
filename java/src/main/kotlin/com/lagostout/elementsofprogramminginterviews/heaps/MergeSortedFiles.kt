package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

/**
 * Problem 11.1 page 178
 */
fun mergeSortedLists(lists: List<List<Int>>): List<Int> {
    val mergedList = mutableListOf<Int>()
    val listToPositionMap = mutableMapOf<Int, Int>()
    val heap = PriorityQueue<Int>()
    val nonEmptyLists = lists.filter { it.isNotEmpty() }
    nonEmptyLists.indices.forEach {
        listToPositionMap.put(it, 0)
    }
    while (listToPositionMap.isNotEmpty()) {
        val listsToRemove = mutableListOf<Int>()
        listToPositionMap.entries.forEach {
            (listIndex, position) ->
            val list = nonEmptyLists[listIndex]
            if (position == list.size) {
                listsToRemove.add(listIndex)
            } else {
                heap.add(list[position])
                listToPositionMap[listIndex] = position + 1
            }
        }
        listsToRemove.forEach {
            listToPositionMap.remove(it)
        }
        while (heap.isNotEmpty()) {
            mergedList.add(heap.poll())
        }
    }
    return mergedList
}