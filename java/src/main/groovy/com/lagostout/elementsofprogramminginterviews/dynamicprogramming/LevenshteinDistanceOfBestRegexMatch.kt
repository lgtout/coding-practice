package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.2.5 page 316
 */
// See http://bit.ly/2j5sU1C for explanation of the problem.
object LevenshteinDistanceOfBestRegexMatch {
    sealed class RegexPosition(val char: String) {
        class AnyChar() : RegexPosition(".")
//        class OneOrMoreChar(nonMetaChar: Char) : RegexPosition(char + )
    }
    fun levenshteinDistanceOfBestRegexMatch(string: String, regex: String): Int {
        return 0
    }
}
