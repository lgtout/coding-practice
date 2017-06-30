package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import com.lagostout.elementsofprogramminginterviews.graphs.SearchAMaze.Companion.Point

class SearchAMazeSpek : Spek({
    describe("") {
        testCases.forEach {
            (_, entry, exit, pathExists, adjacencies) ->
            given("entry $entry exit $exit adjacencies $adjacencies") {
                it("${if (pathExists) "finds" else "doesn't find"} " +
                        "a path from entry to exit") {

                }
            }
        }
    }
}){
    companion object {
        val T = true
        val F = false
        data class TestCase(val grid: List<List<Boolean>>,
                            val entry: Point, val exit: Point,
                            val pathExists: Boolean) {
            val adjacencies = (fun(grid: List<List<Boolean>>): Map<Point, List<Point>> {
                val result: MutableMap<Point, List<Point>> = mutableMapOf()
                grid.forEachIndexed { index, list ->
                    list.forEachIndexed {
                        index2, isOpen ->
                        if (isOpen) {
//                            val point = result.get(Point())
//                            result.putIfAbsent(, mutableListOf<Point>())
                        }
                    }
                }
                return result
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
                null
        ).filterNotNull()
    }
}