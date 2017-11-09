package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.6 page 10
 */
@Suppress("NAME_SHADOWING")
fun reverseWordsInSentence(sentence: String): String {
    fun reverseChars(sentence: StringBuilder, left: Int,
                     right: Int) {
        var left = left
        var right = right
        while (left < right) {
            val char = sentence[left]
            sentence[left] = sentence[right]
            sentence[right] = char
            ++left
            --right
        }
    }
    val builder = StringBuilder(sentence)
    reverseChars(builder, 0, builder.lastIndex)
    var left = 0
    var right: Int
    while (left <= builder.lastIndex) {
        while (left <= builder.lastIndex && builder[left] == ' ') {
            ++left
        }
        right = left
        while (right + 1 <= builder.lastIndex && builder[right + 1] != ' ') {
            ++right
        }
        if (listOf(left, right).all { it <= builder.lastIndex }) {
            reverseChars(builder, left, right)
        }
        left = right + 1
    }
    return builder.toString()
}