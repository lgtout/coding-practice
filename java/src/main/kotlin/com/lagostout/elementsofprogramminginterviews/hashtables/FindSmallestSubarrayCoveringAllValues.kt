package com.lagostout.elementsofprogramminginterviews.hashtables

import java.util.*

/**
 * Problem 13.7 page 222
 */
fun smallestSubarrayCoveringSearchWords(words: List<String>, searchWords: Set<String>): IntRange? {
    val wordToIndexMap = LinkedHashMap<String, Int>()
    var rangeContainingSearchWords: IntRange? = null
    words.forEachIndexed {
        index, word ->
        if (word in searchWords) {
            // Removing it then putting it ensures that
            // the word will change position within the
            // map's backing list.
            if (wordToIndexMap.containsKey(word)) {
                wordToIndexMap.remove(word)
            }
            wordToIndexMap.put(word, index)
        }
        if (wordToIndexMap.size == searchWords.size) {
            val range = (wordToIndexMap.values.first()..wordToIndexMap.values.last())
            if (rangeContainingSearchWords == null) {
                rangeContainingSearchWords = range
            } else {
                rangeContainingSearchWords =
                        if (rangeContainingSearchWords!!.count() > range.count())
                            range else rangeContainingSearchWords
            }
        }
    }
    return rangeContainingSearchWords
}