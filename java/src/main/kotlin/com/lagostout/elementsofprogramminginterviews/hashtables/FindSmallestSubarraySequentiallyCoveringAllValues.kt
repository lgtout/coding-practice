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
        val keywordToIndexMap = keywords.withIndex().map {
            it.value to it.index }.toMap()
        val nextWordToSubarrayStart = mutableMapOf<String, Int>()
        var rangeOfSmallestSubarray: Pair<Int, Int>? = null
        paragraph.forEachIndexed {
            index, word ->
            val nextWordToSubarrayStartPair = when {
                word == keywords.first() -> Pair(keywords[1], index)
                nextWordToSubarrayStart.containsKey(word) -> {
                    val subarrayStart = nextWordToSubarrayStart.remove(word)!!
                    // TODO Need a check here to see if we're at the last word.
                    // If so, we won't have a next keyword to use as key when
                    // we update the map.  We'll need to store it in the
                    // map using a distinct special key.  Or we update our
                    // best result so far.  Either way, we'll need to return
                    // null for this branch of the when block.
                    if (word != keywords.last()) {
                        Pair(keywords[keywordToIndexMap[word]!! + 1], subarrayStart)
                    } else {
                        rangeOfSmallestSubarray = rangeOfSmallestSubarray?.let { currentRange ->
                            if (currentRange.second - currentRange.first >
                                    index - subarrayStart) {
                                Pair(subarrayStart, index)
                            } else currentRange
                        }
                        null
                    }
                }
                else -> return@forEachIndexed
            }
            nextWordToSubarrayStartPair?.let { (word, start) ->
                if (!nextWordToSubarrayStart.contains(word) ||
                        nextWordToSubarrayStart[word]!! < start) {
                    nextWordToSubarrayStart[word] = start
                }
            }
        }
        return rangeOfSmallestSubarray
    }

}