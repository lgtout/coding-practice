package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.google.common.math.IntMath

/**
 * Problem 5.3 page 49
 */
fun reverseBits(number: Long): Long {
    // Construct 16-bit cache
    val cache = mutableMapOf(0 to 0, 1 to (1 shl 15))

    var bitCount = 2
    while (bitCount < 16) {
        val halfBitCount = bitCount / 2
        val nextBitCount = bitCount * 2
        (IntMath.pow(2, bitCount - 1) until
                IntMath.pow(2, nextBitCount - 1)).forEach { number ->
            val rightHalfShift = 32 - halfBitCount
            Pair(number ushr halfBitCount,
                    number shl rightHalfShift ushr rightHalfShift).let {
                Pair(cache[it.second]!!, cache[it.first]!! ushr halfBitCount).also {
                    cache[number] = it.first or it.second
                }
            }
        }
        bitCount = nextBitCount
    }

    // Reverse bits
    return (0..3).map {
        it to (number shl (it * 16) ushr 48).toInt()
    }.map { (index, value) ->
        (cache[value]!!).toLong() shl (index * 16)
    }.reduce { acc, l ->
        acc or l
    }
}