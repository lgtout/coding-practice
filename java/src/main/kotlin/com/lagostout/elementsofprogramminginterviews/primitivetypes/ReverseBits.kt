package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.google.common.math.IntMath

fun reverseBits(number: Long): Int {
    // Construct 16-bit cache
    val cache = mapOf(0 to 0, 1 to 1)
    var level = 1
    while (level <= 15) {
        val bitCount = IntMath.pow(2, level)
        bitCount.let {
            IntRange(it, it * 2 - 1)
        }.forEach {
            (bitCount / 2).let { shiftCount ->
                Pair(it ushr shiftCount, it shl shiftCount shr shiftCount)
                // Lookup left and right halves.
                // Combine in reverse.
                // Add to cache.
            }
        }
        level *= 2
    }
    return 0
}