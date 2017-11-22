package com.lagostout.elementsofprogramminginterviews.searching

fun computeRealSquareRoot(n: Double): Double {
    var left = 0.0
    var right = n
    var mid: Double
    var square: Double
    var previousMid: Double? = null
    do {
        mid = (right - left)/2 + left
        if (previousMid != null &&
                previousMid == mid) break
        square = mid * mid
        if (square == n) break
        else if (square > n)
            right = mid
        else {
            left = mid
        }
        previousMid = mid
        println(mid)
    } while (true)
    return mid
}