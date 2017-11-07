package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

fun computeMinimumCharsToDeleteToMakePalindrome(string: String): Int {
    calls = LinkedHashMap(0, 0.75F, false)
    return computeMinimumCharsToDeleteToMakePalindrome(
            string.toCharArray().toList(), 0, string.lastIndex).also {
        println("size ${calls.size}")
        println("recalls ${calls.filterValues { it.first > 1 }.size}")
        println(calls.asIterable().toList())
        println()
    }
}

var calls = mutableMapOf<Triple<List<Char>, Int, Int>, Pair<Int, List<Int>>>()

fun addCall(chars: List<Char>, left: Int, right: Int, deletions: Int) {
    calls.compute(Triple(chars, left, right)) {
        _, pair ->
        pair?.let {
            Pair(pair.first + 1, pair.second + deletions)
        } ?: Pair(1, listOf(deletions))
    }
}

// TODO How do we translate this top-down recursive solution to DP bottom-up?
// Find right char matching left char by recursion, not looping.
fun computeMinimumCharsToDeleteToMakePalindrome(
        chars: List<Char>, left: Int, right: Int): Int {
    if (left >= right) return 0
    val deletionCountWhenFirstCharIncluded: Int =
            (if (chars[right] == chars[left]) Pair(1, 0) else Pair(0, 1))
                    .let { (leftOffset, deletedCharCount) ->
                        computeMinimumCharsToDeleteToMakePalindrome(
                                chars, left + leftOffset, right - 1) + deletedCharCount
                    }
    val deletionCountWhenFirstCharNotIncluded =
        computeMinimumCharsToDeleteToMakePalindrome(
                chars, left + 1, right) + 1
    val deletions = listOf(deletionCountWhenFirstCharIncluded,
            deletionCountWhenFirstCharNotIncluded).min()!!
    addCall(chars, left, right, deletions)
    return deletions
}

@Suppress("NAME_SHADOWING")
// Find right char matching left char by looping, not recursion.
fun computeMinimumCharsToDeleteToMakePalindrome2(
        chars: List<Char>, left: Int, right: Int): Int {
    if (left >= right) return 0
    var right = right
    val deletionCountWhenFirstCharIncluded = run {
        var deletionCount = 0
        while (true) {
            if (chars[right] == chars[left]) break
            deletionCount++
            --right
        }
        deletionCount +
                computeMinimumCharsToDeleteToMakePalindrome2(
                        chars, left + 1, right - 1)
    }
    val deletionCountWhenFirstCharNotIncluded =
            computeMinimumCharsToDeleteToMakePalindrome2(
                    chars, left + 1, right) + 1
    val deletions = listOf(deletionCountWhenFirstCharIncluded,
            deletionCountWhenFirstCharNotIncluded).min()!!
    addCall(chars, left, right, deletions)
    return deletions
}

fun computeMinimumCharsToDeleteToMakePalindrome1(
        chars: List<Char>, left: Int, right: Int): Int {
    val deletionCountWhenFirstCharDeleted = 1 +
            computeMinimumCharsToDeleteToMakePalindrome1(chars, left + 1, right)
    val deletionCountWhenComparingFirstAndLastChar = if (chars[left] != chars[right]) 0 else 2 +
            computeMinimumCharsToDeleteToMakePalindrome1(chars, left + 1, right - 1)
    val deletionCountWhenLastCharDeleted = 1 +
            computeMinimumCharsToDeleteToMakePalindrome1(chars, left, right - 1)
    // TODO
    // Figure out a way to track what's being deleted, for debugging.
    // Cache results.
    return minOf(deletionCountWhenFirstCharDeleted,
            deletionCountWhenComparingFirstAndLastChar,
            deletionCountWhenLastCharDeleted)
}