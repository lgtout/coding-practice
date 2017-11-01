package com.lagostout.elementsofprogramminginterviews.hashtables

fun computeAllStringDecompositions(sentence: String, words: List<String>): List<Int> {
    if (words.isEmpty()) return emptyList()

    val wordToCountMap = mutableMapOf<String, Int>().apply {
        words.forEach {
            put(it, getOrPut(it, { 0 }) + 1)
        }
    }
    val missingWordsSet = mutableSetOf<String>().apply {
        addAll(words)
    }
    val wordLength = words.first().length
    val currentWordToCountMap = mutableMapOf<String, Int>()
    val wordEndOffset = wordLength - 1
    var currentIndex = 0
    var previousFirstWord: String?
    val startingIndicesOfConcatenations = mutableListOf<Int>()

    while (true) {
        val wordEndIndex = currentIndex + wordEndOffset
        if (wordEndIndex > sentence.lastIndex) break

        // Remove the first word-length substring in the current
        // sentence substring.
        previousFirstWord = (currentIndex - wordLength * words.size).let {
            previousWordIndex ->
            if (previousWordIndex >= 0)
                sentence.substring(previousWordIndex,
                        previousWordIndex + wordEndOffset)
            else null
        }
        previousFirstWord?.let { word ->
            currentWordToCountMap.computeIfPresent(
                    word, { _, count -> count - 1 })?.let {
                missingWordsSet.add(word)
            }
        }

        // Examine the next word-length substring in the sentence
        sentence.substring(currentIndex, wordEndIndex).let {
            currentWord ->
            if (currentWord in missingWordsSet) {
                with(currentWordToCountMap) {
                    put(currentWord, getOrPut(currentWord, { 0 }) + 1)?.let {
                        if (it == wordToCountMap[currentWord])
                            with (missingWordsSet) {
                                remove(currentWord)
                                if (isEmpty())
                                    startingIndicesOfConcatenations.add(currentIndex)
                            }
                    }
                }
            }
        }

        // Next word in sentence
        currentIndex += wordEndOffset
    }

    return startingIndicesOfConcatenations
}