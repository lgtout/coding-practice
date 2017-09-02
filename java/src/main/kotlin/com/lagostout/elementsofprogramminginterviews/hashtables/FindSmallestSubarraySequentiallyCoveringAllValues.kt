package com.lagostout.elementsofprogramminginterviews.hashtables

object FindSmallestSubarraySequentiallyCoveringAllValues {

    fun smallestSubarraySequentiallyCoveringAllValues(
            paragraph: List<String>, keywords: List<String>): Pair<Int, Int>? {
        if (paragraph.isEmpty() || keywords.isEmpty() ||
                paragraph.size < keywords.size)
            return null
        if (paragraph.size == 1) {
            if (keywords.first() != paragraph.first())
                return null
            return Pair(1,1)
        }
        val keywordToIndexMap = keywords.withIndex().map {
            it.value to it.index }.toMap()
        val subarrays = Array<Set<Int>>(keywords.size, { mutableSetOf() })
        var rangeOfSmallestSubarray: Pair<Int, Int>? = null
        paragraph.forEachIndexed {
            index, word ->
            if (word == keywords.first()) {
//                subarrays[]
            }
        }
        return rangeOfSmallestSubarray
    }
}