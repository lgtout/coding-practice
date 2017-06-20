package com.lagostout.elementsofprogramminginterviews.hashtables

private data class Entry(val word: String,
                         val lastOccurrence: Int,
                         val shortestDistance: Int)

/**
 * Problem 13.6 page 221
 */
fun nearestRepeatedWord(words: List<String>): String? {
    val entries = mutableMapOf<String, Entry>()
    var nearestRepeatedEntry: Entry? = null
    words.forEachIndexed {
        index: Int, word ->
        var entry = entries[word]
        if (entry != null) {
            val distanceFromLastOccurrence: Int = index - entry.lastOccurrence
            if (distanceFromLastOccurrence < entry.shortestDistance) {
                entry = entry.copy(
                        shortestDistance = distanceFromLastOccurrence,
                        lastOccurrence = index)
                if (nearestRepeatedEntry == null ||
                        (entry.shortestDistance <
                                nearestRepeatedEntry!!.shortestDistance)) {
                    nearestRepeatedEntry = entry
                }
            }
        } else {
            entry = Entry(word, index, Int.MAX_VALUE)
        }
        entries[word] = entry
    }
    return nearestRepeatedEntry?.word
}