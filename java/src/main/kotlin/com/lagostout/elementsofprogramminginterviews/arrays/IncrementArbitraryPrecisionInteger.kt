package com.lagostout.elementsofprogramminginterviews.arrays

fun incrementInteger(list: MutableList<Int>) {
    var index = list.lastIndex
    var done = false
    while (index >= 0 && !done) {
        val sum = list[index] + 1
        list[index] = sum % 10
        done = sum == list[index]
        --index
    }
}