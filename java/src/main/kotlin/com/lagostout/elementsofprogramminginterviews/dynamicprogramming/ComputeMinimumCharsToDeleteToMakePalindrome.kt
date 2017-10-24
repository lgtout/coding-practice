package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

fun computeMinimumCharsToDeleteToMakePalindrome(
        chars: List<Char>, left: Int, right: Int): Int {
    val deletionCountWhenFirstCharDeleted = 1 +
            computeMinimumCharsToDeleteToMakePalindrome(chars, left + 1, right)
    val deletionCountWhenComparingFirstAndLastChar = if (chars[left] != chars[right]) 0 else 2 +
            computeMinimumCharsToDeleteToMakePalindrome(chars, left + 1, right - 1)
    val deletionCountWhenLastCharDeleted = 1 +
            computeMinimumCharsToDeleteToMakePalindrome(chars, left, right - 1)
    // TODO
    // Figure out a way to track what's being deleted, for debugging.
    // Cache results.
    return minOf(deletionCountWhenFirstCharDeleted,
            deletionCountWhenComparingFirstAndLastChar,
            deletionCountWhenLastCharDeleted)
}