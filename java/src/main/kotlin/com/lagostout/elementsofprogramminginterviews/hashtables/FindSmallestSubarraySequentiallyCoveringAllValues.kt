package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.8 page 225
 */
object FindSmallestSubarraySequentiallyCoveringAllValues {

    fun smallestSubarraySequentiallyCoveringAllValues(
            paragraph: List<String>, keywords: List<String>): Pair<Int,Int>? {
        if (paragraph.isEmpty() || keywords.isEmpty() ||
                paragraph.size < keywords.size)
            return null
        val keywordToIndexMap = keywords.withIndex().map {
            it.value to it.index }.toMap()
        val nextWordToSubarrayStart = mutableMapOf<String, Int>()
        var rangeOfSmallestSubarray: Pair<Int,Int>? = null
        paragraph.forEachIndexed { wordIndex, word ->
            val currentStart = if (word == keywords.first()) wordIndex
            else nextWordToSubarrayStart.remove(word)
            currentStart?.let {
                if (word == keywords.last()) {
                    val currentSubarray = Pair(currentStart, wordIndex)
                    rangeOfSmallestSubarray = rangeOfSmallestSubarray?.let {
                        if (currentSubarray.run { second - first }
                                < it.run { second - first} ) {
                            currentSubarray
                        } else it
                    } ?: currentSubarray
                } else {
                    keywordToIndexMap[word]?.let { keywordIndex ->
                        val nextKeyword = keywords[keywordIndex + 1]
                        nextWordToSubarrayStart[nextKeyword] = currentStart
                    }
                }
            }
        }
        return rangeOfSmallestSubarray
    }

}