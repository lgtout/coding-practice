package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CharGroupType.OneAny
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.Distances.Position
import java.util.regex.Pattern

/* Problem 17.2.5 page 316 */
// See http://bit.ly/2j5sU1C for explanation of the problem.

private class Dimensions(val columns: Int, val rows: Int)

private enum class CharGroupType(val regex: String) {
    OneAny("""(\.(?![\+\*\?])).*"""),
    ZeroOrOneAny("""(\.\?).*"""),
    OneOrMoreAny("""(\.\+).*"""),
    ZeroOrMoreAny("""(\.\*).*"""),
    ZeroOrOne("""(\w\?).*"""),
    OneOrMore("""(\w\+).*"""),
    ZeroOrMore("""(\w\*).*"""),
    NonMetaChar("""(\w(?![+*?])).*""")
}

private data class CharGroup(val value: String, val type: CharGroupType)

private class Distances(private val cache: List<List<Int>>) {
    data class Position(val row: Int, val col: Int)

    lateinit var position: Position

    val above: Int
        get() = cache[position.row - 1][position.col]

    val aboveLeft: Int
        get() = cache[position.row - 1][position.col - 1]

    val left: Int
        get() = cache[position.row - 1][position.col]

    val minOfAll: Int
        get() = listOf(above, aboveLeft, left).min()!!
}

fun levenshteinDistanceOfBestRegexMatch(string: String, regex: String): Int {

    val dimensions = Dimensions(regex.length + 1, string.length + 1)

    // Split string into meta- and non-meta-character groups.
    var matchStartIndex = 0
    val capturingGroupName = "charGroup"
    val charGroups = mutableListOf<CharGroup>()
    while (matchStartIndex <= string.length) {
        CharGroupType.values()
                .map { Pair(it, Pattern.compile(
                        """.{$matchStartIndex}?<$capturingGroupName>${it.regex}""")) }
                .map { Pair(it.first, it.second.matcher(string)) }
                .firstOrNull { it.second.matches() }.also { it }
                ?.let {
                    val match = it.second.group(capturingGroupName)
                    charGroups.add(CharGroup(match, it.first))
                    matchStartIndex += match.length
                }
    }

    // Set up the cache
    val cache = MutableList(dimensions.rows) {
        MutableList(dimensions.columns) { 0 }
    }.apply {
        val distances = Distances(this)
        forEachIndexed {rowIndex, row ->
            (0..row.lastIndex).forEach { colIndex ->
                distances.position = Position(rowIndex, colIndex)
                row[colIndex] = if (rowIndex == 0) colIndex
                else {
                    if (colIndex > 0) {
                        when (charGroups[rowIndex].type) {
                            // TODO Is this right?
                            OneAny -> distances.minOfAll
                            else -> 0
                        }
                    } else 0
                }
            }
        }
    }
    return 0
}
