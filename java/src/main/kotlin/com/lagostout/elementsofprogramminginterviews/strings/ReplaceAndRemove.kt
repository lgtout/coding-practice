package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.4.1 page 98
 */
fun replaceAndRemove(chars: MutableList<Char>) {
    var aCount = 0
    var shiftLeftCount = 0
    var charCount = 0
    chars.forEachIndexed { index, char ->
        if (char != 'b') {
            if (char == 'a') aCount++
            chars[index - shiftLeftCount]
            charCount++
        } else {
            shiftLeftCount++
        }
    }
    var sourceIndex = charCount - 1
    var destinationIndex = sourceIndex + aCount
    (destinationIndex..0).forEach {
        val char = chars[sourceIndex--].let {
            if (it == 'a') {
                'd'
            } else it
        }
        chars[destinationIndex--] = char
        if (char == 'd') {
            chars[destinationIndex--] = char
        }
    }
}
