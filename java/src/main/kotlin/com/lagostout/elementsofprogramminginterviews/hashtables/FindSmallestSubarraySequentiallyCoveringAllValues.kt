package com.lagostout.elementsofprogramminginterviews.hashtables

import org.apache.commons.lang3.Range

fun smallestSubarraySequentiallyCoveringAllValues(
        paragraph: List<String>, keywords: LinkedHashSet<String>): Range<Int>? {
    var rangeOfSmallestSubarray: Range<Int>? = null
    var nextStringSoughtToStartStringIndexMap = mutableMapOf<String, Int?>()
    var nextStringSoughtSet = mutableSetOf(keywords.first())
    paragraph.forEachIndexed {
        index, word ->
        var entry: Pair<String, Int>? = when (word) {
            keywords.first() -> word to index
            in nextStringSoughtToStartStringIndexMap ->
                nextStringSoughtToStartStringIndexMap[word]?.let {
                    word to (it + 1)
                }
            else -> null
        }
    }
    return rangeOfSmallestSubarray
}