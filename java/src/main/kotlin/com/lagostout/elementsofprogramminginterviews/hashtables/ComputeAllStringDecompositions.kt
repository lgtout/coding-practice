package com.lagostout.elementsofprogramminginterviews.hashtables

import java.util.regex.Pattern

/**
 * Problem 13.12 page 232
 */
fun computeAllStringDecompositions(sentence: String, words: List<String>): List<Int> {
    if (words.isEmpty() || sentence.isEmpty()) return emptyList()

    fun createWordToCountMap(words: List<String>): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        words.forEach {
            with (map) {
                compute(it, {_, count -> (count ?: 0) + 1})
            }
        }
        return map

    }
    val wordToCountMap = createWordToCountMap(words)
    var substringWordToCountMap: Map<String, Int>
    var currentIndex = 0
    val wordLength = words.first().length
    val concatenations = mutableListOf<Int>()
    val substringLength = wordLength * words.size
    while (true) {
        val substringEndIndex = currentIndex + substringLength - 1
        if (substringEndIndex > sentence.lastIndex) break
        substringWordToCountMap = createWordToCountMap(
                sentence.substring(currentIndex, substringEndIndex + 1)
                        .split(Pattern.compile(
                                "(?<=\\G[\\w ]{$wordLength})(?!$)")))
        if (wordToCountMap == substringWordToCountMap)
            concatenations.add(currentIndex)
        ++currentIndex
    }

    return concatenations
}