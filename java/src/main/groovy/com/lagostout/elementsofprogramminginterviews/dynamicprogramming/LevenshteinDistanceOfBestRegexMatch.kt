package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.LevenshteinDistanceOfBestRegexMatch.MetaChar.AnyChar
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.LevenshteinDistanceOfBestRegexMatch.MetaChar.Companion.cardinalities
import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 17.2.5 page 316
 */
// See http://bit.ly/2j5sU1C for explanation of the problem.
object LevenshteinDistanceOfBestRegexMatch {
    sealed class RegexPosition(val char: String) {
        class Dot() : RegexPosition(".")
//        class NonMetaChar(): RegexPosition()
//        class Plus(nonMetaChar: Char) : RegexPosition(char + )
        fun regexPosition(token: String): RegexPosition {
            when (token) {
                "." -> Dot()
                else -> Unit
            }
            return Dot()
        }
    }
    class Dimensions(val columns: Int, val rows: Int)
    enum class MetaChar(val symbol: Char? = null) {
        ZeroOrOne('?'), OneOrMore('+'), ZeroOrMore('*'), AnyChar('.'), NotMetaChar();
        companion object {
            fun from(char: Char): MetaChar? {
                return values().find { it.symbol == char } ?: NotMetaChar
            }
            val cardinalities = setOf(ZeroOrOne, OneOrMore, ZeroOrMore)
        }
    }
    enum class CharGroup(regex: String) {
        OneAny("""^\.(?![\+\*\?])"""),
        ZeroOrOneAny("""^\.\?"""),
        OneOrMoreAny("""^\.\+"""),
        ZeroOrMoreAny("""^\.\*"""),
        ZeroOrOne("""^\w\?"""),
        OneOrMore("""^\w\+"""),
        ZeroOrMore("""^\w\*"""),
        NoMetaChar("""^\w(?![+*?])""")
    }
    fun levenshteinDistanceOfBestRegexMatch(string: String, regex: String): Int {
        // Set up the cache
        val dimensions = Dimensions(regex.length + 1, string.length + 1)
        val stringIterator = PeekingIterator(string.iterator())
        while (stringIterator.hasNext()) {
            val char = stringIterator.next()
            var charGroup = char.toString()
            val metaChar = when (MetaChar.from(char)) {
                AnyChar -> {
                    MetaChar.from(stringIterator.peek())?.let {
                        if (it in cardinalities) {
                            charGroup += it.symbol
                            stringIterator.next()
                        }
                    }
                }
                else -> Unit
//                ZeroOrOne
            }
        }
        val cache = MutableList(dimensions.rows) {
            MutableList(dimensions.columns) { 0 }
        }.apply {
            forEachIndexed {rowIndex, row ->
                (0..row.lastIndex).forEach { colIndex ->
                    if (rowIndex == 0) colIndex
                    else {
                        if (colIndex > 0) 0
                        else {
                            when (regex) {
                                "." -> rowIndex
                                else -> 0
                            }
                        }
                    }
                }
            }
        }
        return 0
    }
}
