package com.lagostout.elementsofprogramminginterviews.strings

object TelexEncoding {

    // Not bothering to include all punctuation.
    // This is sufficient.
    val punctuationToEncodingMap = mapOf(
            '.' to "DOT",
            ',' to "COMMA",
            '?' to "QUESTION MARK",
            '!' to "EXCLAMATION MARK").map {
        it.key to it.value.toCharArray().toList()
    }.toMap()

    fun encodeAsTelex(chars: MutableList<Char?>) {
        // To simplify things, we'll drop characters pushed
        // beyond the collection's right edge.

        // Count extra space needed for encoded punctuation.
        var extraSpace = 0
        chars.forEach { char ->
            punctuationToEncodingMap[char]?.let {
                extraSpace += it.size - 1
            }
        }
        // Find the last character
        var index = chars.lastIndex
        while (index >= 0) {
            val char = chars[index--]
            (punctuationToEncodingMap[char] ?: listOf(char)).let {
                it.forEach { char ->
                    val offsetIndex = index + extraSpace--
                    if (offsetIndex <= chars.lastIndex) {
                        chars[offsetIndex] = char
                    }
                }
            }
        }
    }
}