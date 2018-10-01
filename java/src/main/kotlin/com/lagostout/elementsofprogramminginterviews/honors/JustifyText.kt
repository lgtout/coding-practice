package com.lagostout.elementsofprogramminginterviews.honors

/* Problem 25.8 page 454 */

/* We'll assume that no word will be so long that it cannot fit on a line */
fun justifyText(words: List<String>, targetLineLength: Int): List<String> {
    var word: String
    var wordIndex = 0
    var lineStartWordIndex = 0
    var lineWordsLength = 0
    val lines = mutableListOf<String>()
    while (wordIndex <= words.lastIndex) {
        word = words[wordIndex]
        val wordLength = word.length
        val remainingSpaceIfWordAdded = targetLineLength -
                (lineWordsLength
                        // Minimum single spaces between words.
                        + (wordIndex - lineStartWordIndex)
                        + wordLength)
        when {
            remainingSpaceIfWordAdded > 0 -> {
                lineWordsLength += wordLength
            }
            remainingSpaceIfWordAdded <= 0 -> {
                val lastWordIndex = wordIndex -
                        if (remainingSpaceIfWordAdded == 0) 0 else 1
                var spacesCount = targetLineLength - lineWordsLength
                // On last line.
                var evenSpaceBetweenWords = 1
                var extraSpaceBetweenWords = 0
                // Not on last line.
                val spacesGroupCount = (lastWordIndex - lineStartWordIndex).let {
                    // Prevent divide-by-zero when value used later.
                    if (it == 0) 1 else it
                }
                if (lastWordIndex < words.lastIndex) {
                    evenSpaceBetweenWords = spacesCount / spacesGroupCount
                    extraSpaceBetweenWords = spacesCount % spacesGroupCount
                }
                // Start line with first word.
                val line = words[lineStartWordIndex]
                // Append spaces and words.
                for (index in (lineStartWordIndex + 1)..wordIndex) {
                    val spaceCount = evenSpaceBetweenWords +
                            if (extraSpaceBetweenWords > 0) {
                                --extraSpaceBetweenWords
                                1
                            } else 0
                    spacesCount -= spaceCount
                    line + " ".repeat(spaceCount) + words[index]
                }
                // Space after last word - last line only.
                line + " ".repeat(spacesCount)
                lines.add(line)
                lineStartWordIndex = wordIndex + 1
                lineWordsLength = 0
            }
        }
        wordIndex++
    }
    return lines
}