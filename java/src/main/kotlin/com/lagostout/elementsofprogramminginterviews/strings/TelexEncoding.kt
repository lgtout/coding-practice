package com.lagostout.elementsofprogramminginterviews.strings

import com.lagostout.common.takeExceptLast
import com.lagostout.common.takeFrom

object TelexEncoding {

    // Not bothering to include all punctuation.
    // This is sufficient.
    val punctuationToEncodingMap = mapOf(
            '.' to " DOT",
            ',' to " COMMA",
            '?' to " QUESTION MARK",
            ';' to " SEMICOLON",
            '!' to " EXCLAMATION MARK")
            .mapValues {
                it.value.toCharArray().toList()
            }
    private val reversedPunctuationToEncodingMap =
            punctuationToEncodingMap.mapValues { it.value.reversed() }

    fun encodeAsTelex(chars: MutableList<Char?>) {
        // To simplify things, we'll drop characters pushed
        // beyond the collection's right edge.

        // Count extra space needed for encoded punctuation.
        var extraSpace = 0
        chars.forEach { char ->
            punctuationToEncodingMap[char]?.let {
                // Need extra single space to separate encoded
                // punctuation from preceding word.
                extraSpace += it.size - 1
            }
        }
        // Find the last character
        var index = chars.lastIndex
        var destinationIndex = index + extraSpace
        while (index >= 0) {
            val char = chars[index]
            (reversedPunctuationToEncodingMap[char] ?: listOf(char)).let {
                println(it)
                it.forEach { char ->
                    if (destinationIndex <= chars.lastIndex) {
                        chars[destinationIndex] = char
                    }
                    --destinationIndex
                }
            }
            --index
        }
    }
}