package com.lagostout.elementsofprogramminginterviews.primitivetypes

/**
 * Problem 5.1 page 45
 */
fun computeParity(words: Array<Long>): List<Int> {
    if (words.isEmpty()) return emptyList()
    val parities = mutableListOf<Int>()
    // Build cache of 16-bit words
    val cache = mutableListOf<Int>().apply {
        (0..(1 shl 15)).forEach {
            var number = it
            var parity = 0
            while (number > 0) {
                number = (number and (number - 1)).apply {
                    parity = parity xor 1
                }
            }
            add(it, parity)
        }
    }
    // Compute word parities
    val mask = (-1L ushr 48)
    words.forEach { word ->
        val parity = (0..3).map {
            (word ushr it * 16) and mask
        }.fold(0) {
            acc, subWord ->
            acc xor cache[subWord.toInt()]
        }
        parities.add(parity)
    }
    return parities
}