package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.elementsofprogramminginterviews.graphs.SearchMaze.findPathThroughMaze
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchAMazeSpek : Spek({
    describe("") {
        testCases.forEach {
            (grid, entry, exit, pathExists) ->
            given("entry $entry exit $exit adjacencies $grid") {
                val (path, graph) = findPathThroughMaze(grid, entry, exit)
                it("${if (pathExists) "finds" else "doesn't find"} " +
                        "a path $path from entry to exit") {
                    if (path.isEmpty()) {
                        assertFalse(pathExists)
                    } else {
                        assertTrue(isPathFromEntryToExit(
                                path, entry, exit, graph))
                    }
                }
            }
        }
    }
}){
    companion object {

        fun isPathFromEntryToExit(
                path: List<Point>, from: Point, to: Point,
                adjacencies: Map<Point, Set<Point>>): Boolean {
            if (path.isEmpty() || path.first() != from || path.last() != to) return false
            @Suppress("LoopToCallChain")
            var isValidPath = true
            for ((index, point) in path.withIndex()) {
                val isFirstPointInPath = index == 0
                isValidPath = adjacencies.containsKey(point) && if (isFirstPointInPath) {
                    true
                } else {
                    // We can force (!!) here because we already checked for the
                    // presence in the adjacencies of the previous point in the path
                    // on the previous iteration.
                    adjacencies[path[index - 1]]!!.contains(point)
                }
                if (!isValidPath) break
            }
            return isValidPath
        }

        val T = true
        val F = false

        data class TestCase(val grid: List<List<Boolean>>,
                            val entry: Point, val exit: Point,
                            val pathExists: Boolean)

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
                TestCase(listOf(
                        listOf(T,T,T),
                        listOf(T,F,F),
                        listOf(T,T,T),
                        listOf(F,F,T),
                        listOf(T,T,T)),
                        entry = Point(0,4),
                        exit = Point(2,0),
                        pathExists = true),
                TestCase(listOf(
                        listOf(T,T,T),
                        listOf(F,F,T),
                        listOf(T,T,T),
                        listOf(F,F,T),
                        listOf(T,T,T)),
                        entry = Point(0,4),
                        exit = Point(2,0),
                        pathExists = true),
                TestCase(listOf(
                        listOf(T,F,T),
                        listOf(T,T,F),
                        listOf(T,T,T),
                        listOf(T,T,T),
                        listOf(T,T,T)),
                        entry = Point(0,4),
                        exit = Point(2,0),
                        pathExists = false),
                TestCase(listOf(
                        listOf(F,T,T),
                        listOf(F,T,F),
                        listOf(F,T,F),
                        listOf(F,T,F),
                        listOf(T,T,F)),
                        entry = Point(0,4),
                        exit = Point(2,0),
                        pathExists = true),
                TestCase(listOf(
                        listOf(F,F,F),
                        listOf(F,T,F),
                        listOf(T,T,T),
                        listOf(F,T,F),
                        listOf(T,T,T)),
                        entry = Point(1,2),
                        exit = Point(2,0),
                        pathExists = false),
                TestCase(listOf(
                        listOf(F,F,F),
                        listOf(F,T,F),
                        listOf(T,T,T),
                        listOf(F,T,F),
                        listOf(T,T,T)),
                        entry = Point(0,3),
                        exit = Point(1,0),
                        pathExists = false),
                null
        ).filterNotNull()
    }
}