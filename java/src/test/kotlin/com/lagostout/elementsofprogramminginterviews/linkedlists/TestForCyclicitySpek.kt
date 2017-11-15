package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

typealias Node = RawLinkedListNode<Char>

object TestForCyclicitySpek : Spek({
    describe("computeCycleStartNode()") {
        val toLinkedList = { rawNodes:List<Node> ->
            toLinkedListWithExplicitLinkage(rawNodes) }
        val data = listOf(
                Pair(toLinkedList(listOf(Node('A'))), Pair(false, null)),
//                Pair(toLinkedList(listOf(Node('A'), Node('B'))), Pair(false, null)),
//                Pair(toLinkedList(listOf(Node('A', 0))), Pair(true, 0)),
//                Pair(toLinkedList(listOf(Node('A'), Node('B', 0))), Pair(true, 0)),
//                Pair(toLinkedList(listOf(Node('A'), Node('B'), Node('C', 0))), Pair(true, 0)),
//                Pair(toLinkedList(listOf(Node('A'), Node('B'), Node('C', 1))), Pair(true, 1)),
//                Pair(toLinkedList(listOf(Node('A'), Node('B'), Node('C', 2))), Pair(true, 2)),
//                Pair(toLinkedList(listOf(Node('A'), Node('B'), Node('C'), Node('D'),
//                        Node('E'), Node('F'), Node('G', 1))), Pair(true, 1)),
                null
        ).filterNotNull().map { row ->
            val list = row.first.toList()
            data(list, Pair(
                    row.second.first, row.second.second?.let { list[it] }))
        }.toTypedArray()
        on("list: %s", with = *data) { list, expected ->
            it("returns: $expected") {
                assertThat(computeCycleStartNode(list.first()))
                        .isEqualTo(expected)
            }
        }
    }
})
