package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2.4 page 316
 */
fun computeMinimumCharsToDeleteToMakePalindrome(string: String): Int {
    if (string.isEmpty()) return 0
    val indices = (0..string.lastIndex)
    // Initialize the cache
    val cache = mutableListOf<MutableList<Int>>().apply {
        indices.forEach {
            add(mutableListOf<Int>().apply {
                indices.forEach {
                    add(0)
                }
            })
        }
    }
    // Build the cache
    indices.take(indices.count() - 1).reversed().forEach { left ->
        val leftChar = string[left]
        ((left + 1)..indices.last()).forEach { right ->
            val rightChar = string[right]
            val deletionCountByComparingOuterChars = (if (leftChar == rightChar) 0
            else 2) + Pair(left+1, right-1).let { (left, right) ->
                if (left <= indices.last() && right >= 0) cache[left][right] else 0 }
            val deletionCountByDeletingLeftChar = 1 + Pair(left+1, right).let { (left, right) ->
                if (left <= indices.last()) cache[left][right] else 0 }
            val deletionCountByDeletingRightChar = 1 + Pair(left, right - 1).let { (left, right) ->
                if (right < 0) 0 else cache[left][right] }
            cache[left][right] = minOf(deletionCountByComparingOuterChars,
                    deletionCountByDeletingLeftChar, deletionCountByDeletingRightChar)
        }
    }
    return cache[0][indices.last()]
}

// Recursive solution

fun computeMinimumCharsToDeleteToMakePalindromeByRecursion(string: String): Int {
    calls = LinkedHashMap(0, 0.75F, false)
    return computeMinimumCharsToDeleteToMakePalindromeByRecursion3(
            string.toCharArray().toList(), 0, string.lastIndex).also {
        println("size ${calls.size}")
        val repeatCallCount = calls.values.filter { it.first > 1 }
                .map { it.first }.sum()
        val totalCallCount = calls.values.map { it.first }.sum()
        println("total call count: $totalCallCount")
        println("repeat call count: $repeatCallCount")
        println("all calls:")
        calls.asIterable().toList().forEach {
            println(it)
        }
        println()
    }
}

// Call tracking

var calls = mutableMapOf<Triple<List<Char>, Int, Int>, Pair<Int, List<Int>>>()

fun addCall(chars: List<Char>, left: Int, right: Int, deletions: Int?) {
    calls.compute(Triple(chars, left, right)) {
        _, pair ->
        val deletionsList = (deletions?.let { listOf(it) } ?: emptyList())
        pair?.let {
            Pair(pair.first + 1, pair.second + deletionsList)
        } ?: Pair(1, deletionsList)
    }
}

// Recursive solution.  No caching.  Tracks calls.
// Similar to computeMinimumCharsToDeleteToMakePalindromeByRecursion2, but finds
// right char matching left char by recursion, not looping.
fun computeMinimumCharsToDeleteToMakePalindromeByRecursion3(
        chars: List<Char>, left: Int, right: Int): Int {
    addCall(chars, left, right, null)
    if (left >= right) return 0
    val deletionCountWhenFirstCharIncluded: Int =
            (if (chars[right] == chars[left]) Pair(1, 0) else Pair(0, 1))
                    .let { (leftOffset, deletedCharCount) ->
                        computeMinimumCharsToDeleteToMakePalindromeByRecursion3(
                                chars, left + leftOffset, right - 1) + deletedCharCount
                    }
    val deletionCountWhenFirstCharNotIncluded =
        computeMinimumCharsToDeleteToMakePalindromeByRecursion3(
                chars, left + 1, right) + 1
    val deletions = listOf(deletionCountWhenFirstCharIncluded,
            deletionCountWhenFirstCharNotIncluded).min()!!
    addCall(chars, left, right, deletions)
    return deletions
}

// Recursive solution.  No caching.  Tracks calls.
// Searches for a right char matching leftmost char by looping, not recursion.
// Compares deletion counts to make a palindrome when (1) the first char is
// excluded, or (2) we move left from the rightmost char until one matching
// the left char is found, incrementing the deletion count each time.
@Suppress("NAME_SHADOWING")
fun computeMinimumCharsToDeleteToMakePalindromeByRecursion2(
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
                computeMinimumCharsToDeleteToMakePalindromeByRecursion2(
                        chars, left + 1, right - 1)
    }
    val deletionCountWhenFirstCharNotIncluded =
            computeMinimumCharsToDeleteToMakePalindromeByRecursion2(
                    chars, left + 1, right) + 1
    val deletions = listOf(deletionCountWhenFirstCharIncluded,
            deletionCountWhenFirstCharNotIncluded).min()!!
    addCall(chars, left, right, deletions)
    return deletions
}

// Recursive solution.  No caching.
// Compares deletion counts to make a palindrome when (1) the first char
// is excluded from the substring examined, or (2) the last char is excluded,
// or (3) the first and last chars are compared, possibly affecting the deletion count.
fun computeMinimumCharsToDeleteToMakePalindromeByRecursion1(
        chars: List<Char>, left: Int, right: Int): Int {
    val deletionCountWhenFirstCharDeleted = 1 +
            computeMinimumCharsToDeleteToMakePalindromeByRecursion1(chars, left + 1, right)
    val deletionCountWhenComparingFirstAndLastChar = if (chars[left] != chars[right]) 0 else 2 +
            computeMinimumCharsToDeleteToMakePalindromeByRecursion1(chars, left + 1, right - 1)
    val deletionCountWhenLastCharDeleted = 1 +
            computeMinimumCharsToDeleteToMakePalindromeByRecursion1(chars, left, right - 1)
    return minOf(deletionCountWhenFirstCharDeleted,
            deletionCountWhenComparingFirstAndLastChar,
            deletionCountWhenLastCharDeleted)
}