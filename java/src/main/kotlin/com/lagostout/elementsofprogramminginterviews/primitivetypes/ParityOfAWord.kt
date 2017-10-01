package com.lagostout.elementsofprogramminginterviews.primitivetypes

/**
 * Problem 5.1 page 45
 */
fun computeParity(words: Array<Long>): List<Boolean> {
    // TODO Handle corner/degenerate cases
    val parities = mutableListOf<Boolean>()
    // Build cache of 16-bit words
    val cache = mutableListOf<Int>().apply {
        (0..(1 shl 15)).forEach {
            var number = it
            var parity = 0
            while (number > 0) {
                number = (number and (number - 1)).apply {
                    parity++
                }
            }
            set(it, parity)
        }
    }
    // Compute word parities
    words.forEach { word ->
        // TODO Simplify/consolidate next 5 lines
        val mask = (-1L ushr 48)
        val right = (word and mask)
        val middleRight = (word ushr 16) and mask
        val middleLeft = (word ushr 32) and mask
        val left = (word ushr 48) and mask
        val parity = listOf(right, middleRight, middleLeft, left).fold(0) {
            acc, subWord ->
            // TODO Reduce integer to boolean
            acc xor cache[subWord.toInt()]
        }
        // We represent parity 1 as boolean true
        parities.add(parity == 1)
    }
    return parities
}