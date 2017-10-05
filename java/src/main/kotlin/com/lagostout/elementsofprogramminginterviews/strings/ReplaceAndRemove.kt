package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.4.1 page 98
 */
fun replaceAndRemove(chars: MutableList<Char?>) {
    var aCount = 0
    var shiftLeftCount = 0
    var charCount = 0
    chars.forEachIndexed { index, char ->
        if (char == 'b') {
            shiftLeftCount++
        } else if (char != null) {
            if (char == 'a') aCount++
            chars[index - shiftLeftCount] = char
            charCount++
        }
    }
    var sourceIndex = charCount - 1
    var destinationIndex = sourceIndex + aCount
//    println(destinationIndex)
    (destinationIndex downTo 0).forEach {
        println(it)
        chars[sourceIndex--].let {
//            println(it)
            var charsToWrite = listOf(it)
            if (it == 'a') {
                charsToWrite = listOf('d','d')
            }
            charsToWrite.forEach {
                chars[destinationIndex--] = it
            }
        }
    }
//    println(chars)
}
