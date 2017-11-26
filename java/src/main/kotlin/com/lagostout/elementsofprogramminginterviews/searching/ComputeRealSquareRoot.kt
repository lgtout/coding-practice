package com.lagostout.elementsofprogramminginterviews.searching

/**
 * Problem 12.5.1 page 197
 */
fun computeRealSquareRoot(n: Double, epsilon: Double): Double {
    var left = 0.0
    var right = if (n < 1) 1.0 else n
    var mid: Double
    var square: Double
    var previousMid: Double? = null
    do {
        mid = (right - left)/2 + left
        if (previousMid != null &&
                previousMid == mid) break
        square = mid * mid
        if (square >= n - epsilon && square <= n - epsilon) break
        else if (square > n)
            right = mid
        else {
            left = mid
        }
        previousMid = mid
    } while (true)
    return mid
}