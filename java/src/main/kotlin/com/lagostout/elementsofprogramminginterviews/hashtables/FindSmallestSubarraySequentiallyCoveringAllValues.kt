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
        // General case assumes there's at least 2 words in the paragraph,
        // so we need to explicitly handle when there's 1.
        if (paragraph.size == 1) {
            if (keywords.first() != paragraph.first())
                return null
            return Pair(0,0)
        }
        val keywordToIndexMap = keywords.withIndex().map {
            it.value to it.index }.toMap()
        val nextWordToSubarrayStart = mutableMapOf<String, Int>()
        var rangeOfSmallestSubarray: Pair<Int, Int>? = null
        paragraph.forEachIndexed {
            index, word ->
            val isLastWord = word == keywords.last()
            // TODO This is a mess.  Needs work, clarity.  Break out when blocks.
            if (isLastWord) {
                val subarrayStart = nextWordToSubarrayStart.remove(word)!!

            }
            val nextWordToSubarrayStartContainsWord =
                    nextWordToSubarrayStart.containsKey(word)
            when {
                word == keywords.first() -> Pair(keywords[1], index)
                isLastWord || nextWordToSubarrayStartContainsWord -> {
                    val subarrayStart = nextWordToSubarrayStart.remove(word)!!
                    when {
                        isLastWord -> {
                            rangeOfSmallestSubarray = rangeOfSmallestSubarray?.let {
                                currentRange ->
                                val currentSmallestSubarrayLength =
                                        currentRange.second - currentRange.first
                                val candidateSmallestSubarrayLength = index - subarrayStart
                                if ( currentSmallestSubarrayLength > candidateSmallestSubarrayLength) {
                                    Pair(subarrayStart, index)
                                } else currentRange
                            }
                        }
                        nextWordToSubarrayStartContainsWord -> {
                            if (word != keywords.last()) {
                                val nextKeyword = keywords[keywordToIndexMap[word]!! + 1]
                                if (!nextWordToSubarrayStart.contains(nextKeyword) ||
                                        nextWordToSubarrayStart[nextKeyword]!! > subarrayStart) {
                                    nextWordToSubarrayStart[nextKeyword] = subarrayStart
                                }
                            } else {
                                rangeOfSmallestSubarray = rangeOfSmallestSubarray?.let { currentRange ->
                                    if (currentRange.second - currentRange.first >
                                            index - subarrayStart) {
                                        Pair(subarrayStart, index)
                                    } else currentRange
                                }
                            }
                        }
                    }
                }
            }
        }
        return rangeOfSmallestSubarray
    }

}