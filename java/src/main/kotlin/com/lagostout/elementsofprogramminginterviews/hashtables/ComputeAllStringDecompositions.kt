package com.lagostout.elementsofprogramminginterviews.hashtables

fun computeAllStringDecompositions(sentence: String, words: List<String>): List<Int> {
    var wordToCount = mutableMapOf<String, Int>().apply {
        words.forEach {
            put(it, getOrPut(it, { 0 }) + 1)
        }
    }
    val lengthOfConcatenation = words.size *
            if (words.isEmpty()) 0 else words.first().length
//    val currentConcatenation
    // How about moving forward one word length at a time,
    // instead of entire length of concatenated words at a time?
    return emptyList()
}