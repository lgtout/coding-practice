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
    val reversedPunctuationToEncodingMap = punctuationToEncodingMap.mapValues {
        it.value.reversed()
    }

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
//        println("extraSpace $extraSpace")
        var firstNonSpaceCharIndex: Int? = null
        while (index >= 0) {
//            println("index $index")
            val char = chars[index]
            firstNonSpaceCharIndex = firstNonSpaceCharIndex?: run {
                if (char != ' ') index else null
            }
            (reversedPunctuationToEncodingMap[char]?.let {
                if (index == firstNonSpaceCharIndex) it
                // In reversed order, space is last char
                else it.takeExceptLast()
            } ?: listOf(char)).let {
                println(it)
//                println("char '$char'")
                it.forEach { char ->
                    if (destinationIndex <= chars.lastIndex) {
                        chars[destinationIndex] = char
                    }
                    --destinationIndex
                }
            }
            --index
        }
//        println(chars)
    }
}