package com.lagostout.elementsofprogramminginterviews.searching

fun findFirstIndexOfElementGreaterThanKey(sortedList: List<Int>, key: Int): Int? {
    // Cases:
    // Key is less than every element in the list.
    // Key is greater than every element in the list.
    // Key is in the range of elements in the list.
    // List is empty.
    var i = 0
    var j = sortedList.size
    // Condition i < j is safe when list is empty.
    while (i < j) {
        val mid = j + (i - j)/2
        val value = sortedList[mid]
        if (value <= key)
            i = mid + 1
        else if (value > key)
            j = mid - 1
    }
    return if (i < sortedList.lastIndex) i else null
}