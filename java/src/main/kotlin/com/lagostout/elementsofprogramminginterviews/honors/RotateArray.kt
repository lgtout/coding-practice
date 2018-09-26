package com.lagostout.elementsofprogramminginterviews.honors

/* Problem 25.6 page 450 */

fun <T> rotateArray(distance: Int, array: MutableList<T?>) {
    val rotationDistance = if (array.isNotEmpty()) distance % array.size
    else return
    var index = -1
    var element: T? = null
    var count = 1
    while (count <= array.size) {
        if (element == null) {
            index += 1
            element = array[index]
            array[index] = null
        }
        index = (index + rotationDistance) % array.size
        val nextElement = array[index]
        array[index] = element
        element = nextElement
        count += 1
    }
}