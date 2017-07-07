package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.elementsofprogramminginterviews.graphs.SearchMaze.Point
import com.lagostout.elementsofprogramminginterviews.graphs.SearchMaze.findPathThroughMaze
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
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
                    if (path == null || graph == null) {
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
                path: List<SearchMaze.Point>, from: Point, to: Point,
                adjacencies: Map<Point, Set<Point>>): Boolean {
            if (path.first() != from || path.last() != to) return false
            @Suppress("LoopToCallChain")
            var isValidPath = true
            for ((index, point) in path.withIndex()) {
                isValidPath = if (adjacencies.containsKey(point)) {
                    val isFirstPointInPath = index == 0
                    isFirstPointInPath
                } else {
                    val edgeExistsFromPreviousPointInPath =
                            adjacencies[path[index - 1]]?.contains(point) ?: false
                    !edgeExistsFromPreviousPointInPath
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
//                TestCase(listOf(
//                        listOf(T)
//                ), Point(0,0), Point(0,0), true),
//                TestCase(listOf(
//                        listOf(F)
//                ), Point(0,0), Point(0,0), false),
//                TestCase(listOf(
//                        listOf(F,T)
//                ), Point(0,0), Point(0,1), false),
//                TestCase(listOf(
//                        listOf(F,T)
//                ), Point(1,0), Point(1,0), true),
                TestCase(listOf(
                        listOf(T,T)
                ), Point(0,0), Point(1,0), true),
//                TestCase(listOf(
//                        listOf(T,T)
//                ), Point(1,0), Point(0,0), true),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(F,F)
//                ), Point(1,1), Point(0,0), false),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(F,F)
//                ), Point(0,0), Point(1,1), false),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(T,F)
//                ), Point(0,0), Point(0,1), true),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(T,T)
//                ), Point(0,1), Point(1,1), true),
                // TODO More!!
                null
        ).filterNotNull()
    }
}