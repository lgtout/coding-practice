package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.4 page 196
 */
fun computeIntegerSquareRoot(n: Int): Int {
    var left = 1
    var right = n
    var mid: Int
    var square: Int
    do {
        mid = (right - left)/2 + left
        square = mid * mid
        if (square == n) break
        else if (square > n)
            right = mid - 1
        else {
            left = mid + 1
        }
    } while (left <= right)
    return if (square > n) mid - 1 else mid
}