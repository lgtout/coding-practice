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
                            val firstListIntersectionIndex: Int?,
                            val secondListIntersectionIndex: Int?)
        val link = { rawNodes:List<Node> ->
            toLinkedListWithExplicitLinkage(rawNodes) }
        @Suppress("RemoveExplicitTypeArguments")
        val data = listOfNotNull(
//                TestCase(emptyList<Node>(),
//                        emptyList<Node>(),
//                        listOf(Node('A')),
//                        0, 0),
//                TestCase(emptyList<Node>(),
//                        listOf(Node('A')),
//                        listOf(Node('C')),
//                        0, 0),
//                TestCase(listOf(Node('A')),
//                        listOf(Node('B')),
//                        listOf(Node('C')),
//                        0, 0),
//
//                // Lists are non-cyclic and non-intersecting
//                TestCase(listOf(Node('A')),
//                        listOf(Node('B')),
//                        emptyList(),
//                        null, null),
//                TestCase(listOf(Node('A'), Node('B')),
//                        listOf(Node('C')),
//                        emptyList(),
//                        null, null),
//                TestCase(listOf(Node('A'), Node('B')),
//                        listOf(Node('C'), Node('D')),
//                        emptyList(),
//                        null, null),
//
//                // One list has a cycle, the other doesn't
//                TestCase(listOf(Node('A'), Node('B'), Node('C', nextIndex = 1)),
//                        listOf(Node('C'), Node('D')),
//                        emptyList(),
//                        null, null),
//
//                // Both lists have cycles, but no intersection
//                TestCase(listOf(Node('A'), Node('B'), Node('C', nextIndex = 1)),
//                        listOf(Node('C'), Node('D'), Node('E', nextIndex = 1)),
//                        emptyList(),
//                        null, null),

                // TODO Cover more cases. Continue grouping cases.

                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C', nextIndex = 1),
                                Node('D', nextIndex = 0)),
                        0, 0),
//                TestCase(listOf(Node('A')),
//                        listOf(Node('B')),
//                        listOf(Node('C', nextIndex = 1),
//                                Node('D', nextIndex = 0)),
//                        0, 1),
//                TestCase(listOf(Node('A')),
//                        listOf(Node('B')),
//                        listOf(Node('C', nextIndex = 1),
//                                Node('D', nextIndex = 0)),
//                        1, 0),
                null
        ).map {
            it.run {
                var firstLinkedList = link(firstListPrefix)
                var secondLinkedList = link(secondListPrefix)
                val suffixList = listsSuffix?.let { link(it) }
                suffixList?.let {
                    firstListIntersectionIndex?.let {
                        val intersectionNode = suffixList.advance(it)
                        firstLinkedList?.let {
                            it.last.next = intersectionNode
                        } ?: run {
                            firstLinkedList = intersectionNode
                        }
                    }
                    secondListIntersectionIndex?.let {
                        val intersectionNode = suffixList.advance(it)
                        secondLinkedList?.let {
                            it.last.next = intersectionNode
                        } ?: run {
                            secondLinkedList = intersectionNode
                        }
                    }
                }
                data(firstLinkedList!!, secondLinkedList!!,
                        firstListIntersectionIndex?.let {
                            suffixList?.advance(it)
                        },
                        secondListIntersectionIndex?.let {
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