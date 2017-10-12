package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

typealias RawNode = RawDigraphNode<Char>

object DeadlockDetectionSpek : Spek({
    describe("detectDeadlock()") {
        val data = listOf(
                data(toDigraph(listOf(RawNode('A'))), expected = false),
                data(toDigraph(listOf(RawNode('A', listOf(1)), RawNode('B'))), expected = false),
                data(toDigraph(listOf(RawNode('A', listOf(1)), RawNode('B', listOf(0)))), expected = true),
                data(toDigraph(listOf(RawNode('A', listOf(1,2)), RawNode('B', listOf(2)),
                        RawNode('C', listOf(1,3)), RawNode('D'))), expected = true),
                null
        ).filterNotNull().toTypedArray()
        on("digraph: %s", with = *data) { digraph, expected ->
            it("should find deadlock: $expected") {
                assertEquals(expected, detectDeadlock(digraph))
            }
        }
    }
})