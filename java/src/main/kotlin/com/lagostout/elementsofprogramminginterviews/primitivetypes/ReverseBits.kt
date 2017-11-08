package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.google.common.math.IntMath
import com.lagostout.common.toBinaryString

/**
 * Problem 5.3 page 49
 */
fun reverseBits(number: Long): Long {
    // Construct 16-bit cache
    val cache = mutableMapOf(0 to 0, 1 to (1 shl 15))
    fun printCache() {
        cache.forEach { t: Int, u: Int ->
            println("${t.toBinaryString()}: ${u.toBinaryString()}")
        }
    }
    printCache()
    println()

    // TODO Continue redo
    var bitCount = 2
    while (bitCount < 16) {
        (IntMath.pow(2, bitCount) until
                IntMath.pow(2, bitCount + 1)).forEach { number ->
            val rightHalfShift = 32 - bitCount
            Pair(number ushr bitCount,
                    number shl rightHalfShift ushr rightHalfShift).let {
                Pair(cache[it.second]!! shl (32 - bitCount),
                        cache[it.first]!! shl (32 - (2 * bitCount))).also {
                    cache[number] = it.first and it.second
                }.also {
                    println("${it.first.toBinaryString()}, " +
                            it.second.toBinaryString())
                }
            }
        }

        bitCount *= 2
        break
    }

    var exponent = 1
//    while (exponent <= 15) {
    while (exponent <= 4) {
        IntMath.pow(2, exponent).let {
            IntRange(it, it * 2 - 1)
        }.forEach { cacheNumber ->
            ((exponent + 1) / 2).let { bitCount ->
                val rightHalfShift = 32 - bitCount
//                println("cacheNumber ${cacheNumber.toBinaryString()}")
                Pair(cacheNumber ushr bitCount,
                        cacheNumber shl rightHalfShift ushr rightHalfShift).let {
//                    println("${it.first.toBinaryString()}, " +
//                            "${it.second.toBinaryString()}")
                    Pair(cache[it.second]!! shl (32 - bitCount),
                            cache[it.first]!! shl (32 - (2 * bitCount))).also {
                        cache[cacheNumber] = it.first and it.second
                    }.also {
                        println("${it.first.toBinaryString()}, " +
                                it.second.toBinaryString())
                    }
                }
            }
        }
        ++exponent
    }
//    println(cache)
    // Reverse bits
    return (0..3).map {
        (number shl it * 16 ushr 48).toInt()
    }.withIndex().map { (index, value) ->
        (cache[value]!! shl (index * 16)).toLong()
    }.reduce { acc, l ->
        acc and l
    }.apply {
        println(this.toBinaryString())
    }
}