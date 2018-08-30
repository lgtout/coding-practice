package com.lagostout.bytebybyte.recursion

fun printOddIndicesOfIntegerArray(array: Array<Int>): List<Int> {
    return printOddIndicesOfIntegerArray(array, 1)
}

private fun printOddIndicesOfIntegerArray(
        array: Array<Int>, index: Int): List<Int> {
    if (index > array.lastIndex) return emptyList()
    println(array[index])
    return listOf(array[index]) + printOddIndicesOfIntegerArray(array, index + 2)
}