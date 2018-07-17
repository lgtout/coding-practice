package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/* Problem 17.11.3 page 335 */

fun minimizeMessiness(words: List<String>, lineCapacity: Int): Int? {
    if (words.isEmpty()) return 0
    var messiness: Int? = lineCapacity
    var wordsLength = 0
    for (word in words) {
        val wordLength = word.length
        if (wordLength > lineCapacity) {
            messiness = null
            break
        }
        val spaceAndWordLength = wordLength + if (wordsLength == 0) 0 else 1
        val nextWordsLength = wordsLength + spaceAndWordLength
        if (nextWordsLength <= lineCapacity) {
            messiness = messiness?.minus(spaceAndWordLength)
            wordsLength = nextWordsLength
        } else {
            messiness = messiness?.plus(lineCapacity - wordLength)
            wordsLength = wordLength
        }
    }
    return messiness
}