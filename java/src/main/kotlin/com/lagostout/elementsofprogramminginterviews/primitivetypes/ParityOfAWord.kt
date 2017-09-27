package com.lagostout.elementsofprogramminginterviews.primitivetypes

/**
 * Problem 5.1 page 45
 */
fun computeParity(words: Array<Long>): Array<Boolean> {
    val parities = arrayOf<Boolean>()
    // Build cache of 8-bit words
    val cache = arrayOfNulls<Int>(1 shl 8)
    // Compute parities
    cache.withIndex().forEach { (index, value) ->
        value ?: run {
            var number = index
            var parity = 0
            while (number > 0) {
                number = (number and (number - 1)).apply {
                    parity++
                }
            }
        }
    }
    return parities
}