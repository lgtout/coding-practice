package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

/**
 * Problem 11.1 page 178
 */
fun mergeSortedLists(lists: List<List<Int>>): List<Int> {
    data class Item(val list: List<Int>) {
        private var position = 0
        val next: Int
            get() {
                val item = peek
                position++
                return item
            }
        val peek: Int
            get() {
                return list[position]
            }
        val isNotEmpty: Boolean
            get() {
                return position < list.size
            }
    }
    val mergedList = mutableListOf<Int>()
    val heap = PriorityQueue<Item>({
        firstList, secondList ->
        firstList.peek - secondList.peek})
    heap.addAll(lists.filter { it.isNotEmpty() }.map { Item(it) })
    while (heap.isNotEmpty()) {
        val item = heap.poll()
        mergedList.add(item.next)
        if (item.isNotEmpty) {
            heap.add(item)
        }
    }
    return mergedList
}