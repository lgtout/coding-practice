package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.google.common.math.IntMath
import com.lagostout.common.positionOfMostSignificantBit
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
    var exponent = 1
    while (exponent < 16) {
        (IntMath.pow(2, exponent) until
                IntMath.pow(2, exponent + 1)).forEach { number ->
            println()
            println("number $number")
            println("number ${number.toBinaryString()}")
            val halfLength = IntMath.pow(2, exponent - 1)
            println("halfLength $halfLength")
            val rightHalfShift = 32 - halfLength
            Pair(number ushr halfLength,
                    number shl rightHalfShift ushr rightHalfShift).let {
                println("left ${it.first} right ${it.second}")
                Pair(cache[it.second]!!, cache[it.first]!! shr halfLength).also {
                    println("left ${it.first} right ${it.second}")
                    println("left msb ${it.first.positionOfMostSignificantBit()} " +
                            "right msb ${it.second.positionOfMostSignificantBit()}")
                    println("anded: ${it.first and it.second}")
                    cache[number] = it.first or it.second
                }.also {
                    println("${it.first.toBinaryString()}, " +
                            it.second.toBinaryString())
                    println(cache[number]?.toBinaryString())
                }
            }
        }
        exponent *= 2
//        break
    }
    println(cache)

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