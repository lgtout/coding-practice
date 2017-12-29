package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestForOverlappingListsWithCyclesPossibleSpek : Spek({
    describe("listsOverlapWithCyclesPossible()") {
        data class TestCase(val firstListPrefix: List<Node>,
                            val secondListPrefix: List<Node>,
                            val listsSuffix: List<Node>?,
                            // Intersection indices are indices of listsSuffix
                            val firstListSuffixIntersectionIndex: Int?,
                            val secondListSuffixIntersectionIndex: Int?)
        val link = { rawNodes:List<Node> ->
            toLinkedListWithExplicitLinkage(rawNodes) }
        @Suppress("RemoveExplicitTypeArguments")
        val data = listOfNotNull(

                // Lists don't have cycles and don't overlap
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        emptyList(),
                        null, null),
                TestCase(listOf(Node('A'), Node('B')),
                        listOf(Node('C')),
                        emptyList(),
                        null, null),
                TestCase(listOf(Node('A'), Node('B')),
                        listOf(Node('C'), Node('D')),
                        emptyList(),
                        null, null),

                // Lists don't have cycles but do overlap.
                // One or both lists contain only overlapping nodes.
                TestCase(emptyList<Node>(),
                        emptyList<Node>(),
                        listOf(Node('A')),
                        0, 0),
                TestCase(emptyList<Node>(),
                        emptyList<Node>(),
                        listOf(Node('A'), Node('B')),
                        0, 0),
                TestCase(emptyList<Node>(),
                        listOf(Node('A')),
                        listOf(Node('C')),
                        0, 0),
                TestCase(emptyList<Node>(),
                        listOf(Node('A'), Node('B')),
                        listOf(Node('C')),
                        0, 0),
                TestCase(listOf(Node('A'), Node('B')),
                        emptyList<Node>(),
                        listOf(Node('C'), Node('D')),
                        0, 0),

                // Lists don't have cycles but do overlap
                // They both contain nodes outside of the overlap.
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C')),
                        0, 0),
                TestCase(listOf(Node('A'), Node('B')),
                        listOf(Node('C'), Node('D'), Node('E')),
                        listOf(Node('F'), Node('G')),
                        0, 0),

                // One list has a cycle, the other doesn't.
                // They can't possibly overlap.
                TestCase(listOf(Node('A'), Node('B'), Node('C', nextIndex = 1)),
                        listOf(Node('D'), Node('E')),
                        emptyList(),
                        null, null),
                // Doesn't matter which list we provide as first or second.
                TestCase(listOf(Node('D'), Node('E')),
                        listOf(Node('A'), Node('B'), Node('C', nextIndex = 1)),
                        emptyList(),
                        null, null),

                // Lists have cycles, but no intersection
                TestCase(listOf(Node('A'), Node('B'), Node('C', nextIndex = 1)),
                        listOf(Node('D'), Node('E'), Node('F', nextIndex = 1)),
                        emptyList(),
                        null, null),
                TestCase(listOf(Node('A'), Node('B'), Node('C'), Node('D', nextIndex = 1)),
                        listOf(Node('E'), Node('F'), Node('G', nextIndex = 1)),
                        emptyList(),
                        null, null),
                // Second list has a single-node cycle.
                TestCase(listOf(Node('A'), Node('B'), Node('C'), Node('D', nextIndex = 1)),
                        listOf(Node('E'), Node('F'), Node('G', nextIndex = 2)),
                        emptyList(),
                        null, null),

                // TODO Cover more cases. Continue grouping cases.

                // Lists have cycles.
                // Lists intersect before the cycle starts.
                TestCase(listOf(Node('A'), Node('B'), Node('C'), Node('D')),
                        listOf(Node('E'), Node('F'), Node('G')),
                        listOf(Node('H'), Node('I'), Node('J'), Node('K', nextIndex = 1)),
                        0, 0),
                // Lists intersect at the same node in the cycle.
                TestCase(listOf(Node('A'), Node('B'), Node('C'), Node('D')),
                        listOf(Node('E'), Node('F'), Node('G')),
                        listOf(Node('H'), Node('I'), Node('J', nextIndex = 0)),
                        0, 0),
                // Lists intersect at different nodes in the cycle.
                TestCase(listOf(Node('A'), Node('B'), Node('C'), Node('D')),
                        listOf(Node('E'), Node('F'), Node('G')),
                        listOf(Node('H'), Node('I'), Node('J'), Node('K', nextIndex = 0)),
                        1, 3),
                TestCase(listOf(Node('A'), Node('B'), Node('C'), Node('D')),
                        listOf(Node('E'), Node('F'), Node('G')),
                        listOf(Node('H'), Node('I'), Node('J'), Node('K', nextIndex = 1)),
                        0, 2),

                // Lists have cycles.
                // All of a list's nodes that are shared.
                TestCase(emptyList(), emptyList(),
                        listOf(Node('C'), Node('D', nextIndex = 0)),
                        0, 0),
                TestCase(emptyList(), emptyList(),
                        listOf(Node('C', nextIndex = 0)),
                        0, 0),

                null
        ).map {
            it.run {
                var firstLinkedList = link(firstListPrefix)
                var secondLinkedList = link(secondListPrefix)
                val suffixList = listsSuffix?.let { link(it) }
                suffixList?.let {
                    firstListSuffixIntersectionIndex?.let {
                        val intersectionNode = suffixList.advance(it)
                        firstLinkedList?.let {
                            it.last.next = intersectionNode
                        } ?: run {
                            firstLinkedList = intersectionNode
                        }
                    }
                    secondListSuffixIntersectionIndex?.let {
                        val intersectionNode = suffixList.advance(it)
                        secondLinkedList?.let {
                            it.last.next = intersectionNode
                        } ?: run {
                            secondLinkedList = intersectionNode
                        }
                    }
                }
                data(firstLinkedList!!, secondLinkedList!!,
                        firstListSuffixIntersectionIndex?.let {
                            suffixList?.advance(it)
                        },
                        secondListSuffixIntersectionIndex?.let {
                            suffixList?.advance(it)
                        })
            }
        }.toTypedArray()
        on("first list: %s, secondList: %s", with = *data) {
            firstList, secondList,
            expectedFirstListIntersection,
            expectedSecondListIntersection ->
            it("returns $expectedFirstListIntersection or " +
                    "$expectedSecondListIntersection") {
                assertThat(listsOverlapWithCyclesPossible(
                        firstList, secondList))
                        .isIn(expectedFirstListIntersection,
                                expectedSecondListIntersection)
            }
        }
    }
})