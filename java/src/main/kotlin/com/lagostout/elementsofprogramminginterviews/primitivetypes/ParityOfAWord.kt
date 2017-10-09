package com.lagostout.elementsofprogramminginterviews.primitivetypes

/**
 * Problem 5.1 page 45
 */
@Suppress("NAME_SHADOWING")
fun computeParity(words: List<Long>): List<Int> {

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

    // Approach 1: Take advantage of XOR properties

    words.forEach { word ->
        // Reduce parity to parity of least-significant 32 bits
        var word = word xor (word ushr 32)
        // Reduce parity to parity of least-significant 16 bits
        word = word xor (word ushr 48)
        // Extract least-significant 16 bits
        word = word and (-1 ushr 48)
        val parity = cache[word.toInt()]
        parities.add(parity)
    }

    // Approach 2: Split 64 bit word into 4 x 16 bit words

//    val mask = (-1L ushr 48)
//    words.forEach { word ->
//        val parity = (0..3).map {
//            (word ushr it * 16) and mask
//        }.fold(0) {
//            acc, subWord ->
//            acc xor cache[subWord.toInt()]
//        }
//        parities.add(parity)
//    }

    return parities
}