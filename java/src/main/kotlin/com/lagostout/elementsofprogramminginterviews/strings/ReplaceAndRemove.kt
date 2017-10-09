package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.4.1 page 98
 */
fun replaceAndRemove(chars: MutableList<Char?>) {
    // We'll assume all empty positions are contiguous and
    // at the right end of the list.
    var aCount = 0
    var shiftLeftCount = 0
    var charCount = 0
    chars.forEachIndexed { index, char ->
        val shiftedIndex = index - shiftLeftCount
        if (char == 'b') {
            // In case all b's, for example.
            chars[index] = null
            shiftLeftCount++
        } else if (char != null) {
            if (char == 'a') aCount++
            chars[shiftedIndex] = char
            charCount++
        }
    }
    var sourceIndex = charCount - 1
    var destinationIndex = sourceIndex + aCount
    while (sourceIndex >= 0) {
        chars[sourceIndex--].let {
            var charsToWrite = listOf(it)
            if (it == 'a') {
                charsToWrite = listOf('d', 'd')
            }
            charsToWrite.forEach {
                if (destinationIndex <= chars.lastIndex) {
                    chars[destinationIndex] = it
                }
                --destinationIndex
            }
        }
    }
}
