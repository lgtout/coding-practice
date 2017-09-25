package com.lagostout.elementsofprogramminginterviews.hashtables

/**
 * Problem 13.8 page 225
 */
object FindSmallestSubarraySequentiallyCoveringAllValues {

    fun smallestSubarraySequentiallyCoveringAllValues(
            paragraph: List<String>, keywords: List<String>): Pair<Int, Int>? {
        if (paragraph.isEmpty() || keywords.isEmpty() ||
                paragraph.size < keywords.size)
            return null
        // TODO This may not be necessary as a special case - handled by general case
        if (paragraph.size == 1) {
            if (keywords.first() != paragraph.first())
                return null
            return Pair(1,1)
        }
        // We'll use this to figure out what the To be able to
        val keywordToIndexMap = keywords.withIndex().map {
            it.value to it.index }.toMap()
//        val subarrays = Array<Set<Int>>(keywords.size, { mutableSetOf() })
        val nextWordToSubarrayStart = mutableMapOf<String, Int>()
        var rangeOfSmallestSubarray: Pair<Int, Int>? = null
        paragraph.forEachIndexed {
            index, word ->
            val nextWord = keywords[keywordToIndexMap[word]!! + 1]
            if (!nextWordToSubarrayStart.containsKey(nextWord) ||
                    nextWordToSubarrayStart[word]!! >
                    nextWordToSubarrayStart[nextWord]!!) {
                val start = if (word == keywords.first())
                    index else nextWordToSubarrayStart[word]!!
                nextWordToSubarrayStart[nextWord] = start
            }
            if (word != keywords.first()) {
                nextWordToSubarrayStart.remove(word)
            }
        }
//        nextWordToSubarrayStart.filterKeys { it == keywords.last() }.maxBy { it.value }
        return rangeOfSmallestSubarray
    }
}