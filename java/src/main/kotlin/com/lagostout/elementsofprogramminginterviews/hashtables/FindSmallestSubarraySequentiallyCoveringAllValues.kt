package com.lagostout.elementsofprogramminginterviews.hashtables

object FindSmallestSubarraySequentiallyCoveringAllValues {

    data class Range(val start: Int = 0, val end: Int = 0) {
        val size: Int
            get() = end - start + 1
    }

    fun smallestSubarraySequentiallyCoveringAllValues(
            paragraph: List<String>, keywords: List<String>): Range? {
        if (paragraph.isEmpty() || keywords.isEmpty() ||
                paragraph.size < keywords.size)
            return null
        if (paragraph.size == 1) {
            if (keywords.first() != paragraph.first())
                return null
            return Range()
        }
        val keywordToIndexMap = keywords.withIndex().map {
            it.value to it.index }.toMap()
        var rangeOfSmallestSubarray: Range? = null
        var nextWordSoughtToStartStringIndexMap =
                mutableMapOf<String, Int?>()
        paragraph.forEachIndexed {
            index, word ->
            when (word) {
                keywords.first() ->
                    nextWordSoughtToStartStringIndexMap[keywords[1]] = index
                in nextWordSoughtToStartStringIndexMap ->
                    if (nextWordSoughtToStartStringIndexMap.containsKey(word)) {
                        val nextKeywordIndex = keywordToIndexMap[word]!! + 1
                        if (nextKeywordIndex > keywords.lastIndex) {
                            val currentRange = Range(
                                    nextWordSoughtToStartStringIndexMap[word]!!,
                                    index)
                            if (rangeOfSmallestSubarray == null ||
                                    (rangeOfSmallestSubarray?.let {
                                        it.size < currentRange.size
                                    } == true)) {
                                rangeOfSmallestSubarray = currentRange
                            }
                        }
                        nextWordSoughtToStartStringIndexMap[
                                keywords[keywordToIndexMap[word]!! + 1]] =
                                nextWordSoughtToStartStringIndexMap[word]
                        nextWordSoughtToStartStringIndexMap.remove(word)
                    }
            }
        }
        return rangeOfSmallestSubarray
    }
}