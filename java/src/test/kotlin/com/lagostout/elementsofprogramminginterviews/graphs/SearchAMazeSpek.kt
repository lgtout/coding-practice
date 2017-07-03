package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import com.lagostout.elementsofprogramminginterviews.graphs.SearchAMaze.Companion.Point
import com.lagostout.elementsofprogramminginterviews.graphs.SearchAMaze.Companion.findPathThroughMaze
import kotlin.test.assertTrue

class SearchAMazeSpek : Spek({
    describe("") {
        testCases.forEach {
            (_, entry, exit, pathExists, adjacencies) ->
            given("entry $entry exit $exit adjacencies $adjacencies") {
                it("${if (pathExists) "finds" else "doesn't find"} " +
                        "a path from entry to exit") {
                    val path = findPathThroughMaze(adjacencies, entry, exit)
                    assertTrue(isValidPathThroughMaze(
                            path, entry, exit, adjacencies))
                }
            }
        }
    }
}){
    companion object {

        fun isValidPathThroughMaze(
                path: List<Point>, from: Point, to: Point,
                adjacencies: Map<Point, Set<Point>>): Boolean {
            if (path.first() != from || path.last() != to) return false
            @Suppress("LoopToCallChain")
            for ((index, point) in path.withIndex()) {
                if (adjacencies.containsKey(point) && (index == 0 ||
                        adjacencies[path[index - 1]]?.contains(point) ?: false)) {
                    return false
                }
            }
            return true
        }

        val T = true
        val F = false

        data class TestCase(val grid: List<List<Boolean>>,
                            val entry: Point, val exit: Point,
                            val pathExists: Boolean) {
            val adjacencies = (fun(grid: List<List<Boolean>>): Map<Point, Set<Point>> {
                val adjacencies: MutableMap<Point, MutableSet<Point>> = mutableMapOf()
                grid.forEachIndexed { columnIndex, list ->
                    list.forEachIndexed {
                        rowIndex, isOpen ->
                        if (isOpen) {
                            val point = Point(columnIndex, rowIndex)
                            val adjacentPoints = mutableSetOf<Point>()
                            adjacencies.put(point, adjacentPoints)
                            val previousRow = rowIndex - 1
                            if (previousRow >= 0) {
                                val previousRowPoint = Point(previousRow, columnIndex)
                                if (adjacencies.containsKey(previousRowPoint)) {
                                    adjacencies[point]?.add(previousRowPoint)
                                    adjacencies[previousRowPoint]?.add(point)
                                }
                            }
                            val previousColumn = columnIndex - 1
                            if (previousColumn >= 0) {
                                val previousColumnPoint = Point(rowIndex, previousColumn)
                                if (adjacencies.containsKey(previousColumnPoint)) {
                                    adjacencies[point]?.add(previousColumnPoint)
                                    adjacencies[previousColumnPoint]?.add(point)
                                }
                            }
                        }
                    }
                }
                return adjacencies
            })(grid)
            operator fun component5() = adjacencies
        }
        val testCases = listOf(
                TestCase(listOf(
                        listOf(T)
                ), Point(0,0), Point(0,0), true),
                TestCase(listOf(
                        listOf(F)
                ), Point(0,0), Point(0,0), false),
                TestCase(listOf(
                        listOf(F,T)
                ), Point(0,0), Point(0,1), false),
                TestCase(listOf(
                        listOf(F,T)
                ), Point(1,0), Point(1,0), true),
                TestCase(listOf(
                        listOf(T,T)
                ), Point(0,0), Point(1,0), true),
                TestCase(listOf(
                        listOf(T,T)
                ), Point(1,0), Point(0,0), true),
                TestCase(listOf(
                        listOf(T,F),
                        listOf(F,F)
                ), Point(1,1), Point(0,0), false),
                TestCase(listOf(
                        listOf(T,F),
                        listOf(F,F)
                ), Point(0,0), Point(1,1), false),
                TestCase(listOf(
                        listOf(T,F),
                        listOf(T,F)
                ), Point(0,0), Point(0,1), true),
                TestCase(listOf(
                        listOf(T,F),
                        listOf(T,T)
                ), Point(0,1), Point(1,1), true),
                // TODO More!!
                null
        ).filterNotNull()
    }
}