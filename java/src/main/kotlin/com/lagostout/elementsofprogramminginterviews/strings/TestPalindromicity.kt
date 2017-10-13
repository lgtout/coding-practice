package com.lagostout.elementsofprogramminginterviews.strings

/**
 * Problem 7.5 page 100
 */
fun testPalindromicity(string: String): Boolean {
    var left = 0
    var right = string.lastIndex
    var isPalindrome = true
    val alphanumericPattern = Regex("[a-z0-9]", RegexOption.IGNORE_CASE)
    while (left <= right) {
        val leftChar = string[left]
        val rightChar = string[right]
        println(leftChar)
        println(rightChar)
        if (!alphanumericPattern.matches(leftChar.toString())) {
            ++left
            continue
        }
        if (!alphanumericPattern.matches(rightChar.toString())) {
            --right
            continue
        }
        if (leftChar.toLowerCase() != rightChar.toLowerCase()) {
            isPalindrome = false
            break
        }
        ++left
        --right
    }
    return isPalindrome
}