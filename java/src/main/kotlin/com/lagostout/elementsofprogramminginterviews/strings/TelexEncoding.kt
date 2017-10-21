package com.lagostout.elementsofprogramminginterviews.strings

object TelexEncoding {

    // Not bothering to include all punctuation.
    // This is sufficient.
    val punctuationToEncodingMap = mapOf(
            '.' to "DOT",
            ',' to "COMMA",
            '?' to "QUESTION MARK",
            ';' to "SEMICOLON",
            '!' to "EXCLAMATION MARK")
            .mapValues {
                it.value.toCharArray().toList()
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
                extraSpace += it.size // - 1
            }
        }
        // Find the last character
        var index = chars.lastIndex
        var destinationIndex = index + extraSpace
        println("extraSpace $extraSpace")
        while (index >= 0) {
            println("index $index")
            val char = chars[index]
            // TODO
            // Figure out how to work in space before encoded
            // punctuation, while maintaining indices, and
            // accounting for no space at beginning of encoded
            // string when first character in string is punctuation.
            (punctuationToEncodingMap[char] ?: listOf(char)).let {
                println("char '$char'")
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