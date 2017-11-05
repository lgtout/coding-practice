package com.lagostout.elementsofprogramminginterviews.hashtables

fun computeAllStringDecompositions(sentence: String, words: List<String>): List<Int> {
    if (words.isEmpty() || sentence.isEmpty()) return emptyList()

    val wordToCountMap = mutableMapOf<String, Int>()
    val substringWordToCountMap = mutableMapOf<String, Int>()
    words.forEach {
        with (wordToCountMap) {
            compute(it, {_, count -> (count ?: 0) + 1})
        }
        with (substringWordToCountMap) {
            put(it, 0)
        }
    }
    val missingWordsSet = mutableSetOf<String>().apply {
        addAll(words)
    }
    val wordLength = words.first().length
    var currentIndex = 0
    val startingIndicesOfConcatenations = mutableListOf<Int>()

    while (true) {
        val wordEndIndex = currentIndex + wordLength
        if (wordEndIndex > sentence.length) break

        // Remove the first word-length substring from the current
        // sentence substring.
        (currentIndex - wordLength * words.size).let {
            substringFirstWordIndex ->
            if (substringFirstWordIndex >= 0)
                sentence.substring(substringFirstWordIndex,
                        substringFirstWordIndex + wordLength)
            else null
        }?.let { previousFirstWord ->
            substringWordToCountMap.computeIfPresent(
                    previousFirstWord, { _, count -> count - 1 })?.let {
                missingWordsSet.add(previousFirstWord)
            }
        }

        println(wordToCountMap)
        println(substringWordToCountMap)
        println(missingWordsSet)
        println()
        // Examine the next word-length substring in the sentence
        sentence.substring(currentIndex, wordEndIndex).let {
            currentWord ->
            println(currentWord)
            if (currentWord in missingWordsSet) {
                with(substringWordToCountMap) {
                    println(currentWord)
                    compute(currentWord, { _, count -> count?.let {it  + 1} })?.let {
                        println(it)
                        if (it == wordToCountMap[currentWord])
                            with (missingWordsSet) {
                                remove(currentWord)
                                if (isEmpty()) {
                                    startingIndicesOfConcatenations
                                            .add(currentIndex -
                                                    (wordLength * (words.size - 1)))
                                }
                            }
                    }
                }
            }
        }

        // Next word in sentence
        currentIndex += wordLength
    }

    return startingIndicesOfConcatenations
}