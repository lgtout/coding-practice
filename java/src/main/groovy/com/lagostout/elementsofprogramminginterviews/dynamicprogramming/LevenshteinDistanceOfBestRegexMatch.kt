package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import java.util.regex.Pattern

/**
 * Problem 17.2.5 page 316
 */
// See http://bit.ly/2j5sU1C for explanation of the problem.
object LevenshteinDistanceOfBestRegexMatch {
    class Dimensions(val columns: Int, val rows: Int)
    enum class CharGroup(val regex: String) {
        OneAny("""(\.(?![\+\*\?])).*"""),
        ZeroOrOneAny("""(\.\?).*"""),
        OneOrMoreAny("""(\.\+).*"""),
        ZeroOrMoreAny("""(\.\*).*"""),
        ZeroOrOne("""(\w\?).*"""),
        OneOrMore("""(\w\+).*"""),
        ZeroOrMore("""(\w\*).*"""),
        NonMetaChar("""(\w(?![+*?])).*""")
    }
    fun levenshteinDistanceOfBestRegexMatch(string: String, regex: String): Int {

        val dimensions = Dimensions(regex.length + 1, string.length + 1)

        // Split string into meta- and non-meta-character groups.
        var matchStartIndex = 0
        val capturingGroupName = "charGroup"
        val charGroups = mutableListOf<String>()
        while (matchStartIndex < string.length) {
            CharGroup.values()
                    .map { Pattern.compile(""".{$matchStartIndex}?<$capturingGroupName>${it.regex}""") }
                    .map { it.matcher(string) }
                    .firstOrNull { it.matches() }
                    ?.group(capturingGroupName)
                    ?.also {
                        charGroups.add(it)
                        matchStartIndex += it.length
                    }
        }

        // TODO Is this still correct?
        // Set up the cache
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
