package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestForOverlappingListsWithCyclesPossibleSpek : Spek({
    describe("listsOverlapWithCyclesPossible()") {
        data class TestCase(val firstListPrefix: List<Node>,
                            val secondListPrefix: List<Node>,
                            val listsSuffix: List<Node>?,
                            val firstListIntersectionIndex: Int?,
                            val secondListIntersectionIndex: Int?)
        val link = { rawNodes:List<Node> ->
            toLinkedListWithExplicitLinkage(rawNodes) }
        @Suppress("RemoveExplicitTypeArguments")
        val data = listOfNotNull(
                TestCase(emptyList<Node>(),
                        emptyList<Node>(),
                        listOf(Node('A')),
                        0, 0),
                TestCase(emptyList<Node>(),
                        listOf(Node('A')),
                        listOf(Node('C')),
                        0, 0),
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C')),
                        0, 0),
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C', nextIndex = 1),
                                Node('D', nextIndex = 0)),
                        0, 0),
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C', nextIndex = 1),
                                Node('D', nextIndex = 0)),
                        0, 1),
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C', nextIndex = 1),
                                Node('D', nextIndex = 0)),
                        1, 0),
                null
        ).map {
            it.run {
                val firstLinkedList = link(firstListPrefix)
                val secondLinkedList = link(secondListPrefix)
                val suffixList = listsSuffix?.let { link(it) }
                suffixList?.let {
                    firstListIntersectionIndex?.let {
                        firstLinkedList?.last?.next = suffixList.advance(it)
                    } ?: suffixList
                    secondListIntersectionIndex?.let {
                        secondLinkedList?.last?.next = suffixList.advance(it)
                    } ?: suffixList
                }
                data(firstLinkedList!!, secondLinkedList!!,
                        firstListIntersectionIndex,
                        secondListIntersectionIndex)
            }
        }.toTypedArray()
        on("first list: %s, secondList: %s", with = *data) {
            firstList, secondList,
            expectedFirstListIntersection,
            expectedSecondListIntersection ->
            assertThat(listsOverlapWithCyclesPossible(
                    firstList, secondList))
                    .isIn(expectedFirstListIntersection,
                            expectedSecondListIntersection)
        }
    }
})