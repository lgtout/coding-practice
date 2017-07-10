package com.lagostout.elementsofprogramminginterviews.graphs

import com.lagostout.elementsofprogramminginterviews.graphs.SearchMaze.Pixel
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
                path: List<SearchMaze.Pixel>, from: Pixel, to: Pixel,
                adjacencies: Map<Pixel, Set<Pixel>>): Boolean {
            if (path.first() != from || path.last() != to) return false
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
                            val entry: Pixel, val exit: Pixel,
                            val pathExists: Boolean)

        val testCases = listOf(
//                TestCase(listOf(
//                        listOf(T)
//                ), Pixel(0,0), Pixel(0,0), true),
//                TestCase(listOf(
//                        listOf(F)
//                ), Pixel(0,0), Pixel(0,0), false),
//                TestCase(listOf(
//                        listOf(F,T)
//                ), Pixel(0,0), Pixel(0,1), false),
//                TestCase(listOf(
//                        listOf(F,T)
//                ), Pixel(1,0), Pixel(1,0), true),
//                TestCase(listOf(
//                        listOf(T,T)
//                ), Pixel(0,0), Pixel(1,0), true),
//                TestCase(listOf(
//                        listOf(T,T)
//                ), Pixel(1,0), Pixel(0,0), true),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(F,F)
//                ), Pixel(1,1), Pixel(0,0), false),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(F,F)
//                ), Pixel(0,0), Pixel(1,1), false),
//                TestCase(listOf(
//                        listOf(T,F),
//                        listOf(T,F)
//                ), Pixel(0,0), Pixel(0,1), true),
                TestCase(listOf(
                        listOf(T,F),
                        listOf(T,T)
                ), Pixel(0,1), Pixel(1,1), true),
                // TODO More cases!
                null
        ).filterNotNull()
    }
}