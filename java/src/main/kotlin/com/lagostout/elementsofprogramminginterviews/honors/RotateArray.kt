package com.lagostout.elementsofprogramminginterviews.honors

/* Problem 25.6 page 450 */

fun <T> rotateArray(distance: Int, array: MutableList<T?>) {
    val rotationDistance = distance % array.size
    var index = 0
    var element = array[index]
    var count = 0
    do {
        index = (index + rotationDistance) % array.size
        val nextElement = array[index]
        array[index] = element
        element = nextElement
        count += 1
    } while (count < array.size)
}