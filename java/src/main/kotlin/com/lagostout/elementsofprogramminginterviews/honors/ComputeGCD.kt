package com.lagostout.elementsofprogramminginterviews.honors

@Suppress("NAME_SHADOWING")
fun computeGCD(first: Int, second: Int): Int {
    if (first == second) return first
    var first = first
    var second = second
    if (first < second) {
        second -= first
    } else {
        first -= second
    }
    return computeGCD(first, second)
}