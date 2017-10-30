package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.google.common.math.IntMath

/**
 * Problem 5.3 page 49
 */
fun reverseBits(number: Long): Long {
    // Construct 16-bit cache
    val cache = mutableMapOf(0 to 0, 1 to 1)
    var exponent = 1
    while (exponent <= 15) {
        IntMath.pow(2, exponent).let {
            IntRange(it, it * 2 - 1)
        }.forEach { cacheNumber ->
            ((exponent + 1) / 2).let { bitCount ->
                val rightHalfShift = 64 - bitCount
                Pair(cacheNumber ushr bitCount,
                        cacheNumber shl rightHalfShift shr rightHalfShift).let {
                    cache[cacheNumber] = it.second shl bitCount and it.first
                }
            }
        }
        ++exponent
    }
    // Reverse bits
    return (0..3).map {
        (number shl it * 16 ushr 48).toInt()
    }.withIndex().map { (index, value) ->
        (cache[value]!! shl (index * 16)).toLong()
    }.reduce { acc, l ->
        acc and l
    }.apply {
        println(this)
    }
}