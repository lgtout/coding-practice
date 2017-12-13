package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestForOverlappingListsSpek : Spek({
    data class TestCase(val firstListPrefix: List<Node>,
                        val secondListPrefix: List<Node>,
                        val listSuffix: List<Node>?,
                        val expected: Boolean)
    describe("listsAreOverlapping()") {
        val link = { rawNodes:List<Node> ->
            toLinkedListWithExplicitLinkage(rawNodes) }
        val data = listOfNotNull(
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        listOf(Node('C')),
                        true),
                TestCase(listOf(Node('A')),
                        listOf(Node('B')),
                        null,
                        false),
                TestCase(listOf(Node('A'), Node('B')),
                        listOf(Node('C')),
                        null,
                        false),
                TestCase(listOf(Node('A')),
                        listOf(Node('B'), Node('C')),
                        null,
                        false),
                TestCase(listOf(Node('A'), Node('B')),
                        listOf(Node('C'), Node('D')),
                        null,
                        false),
                TestCase(listOf(Node('A'), Node('B')),
                        listOf(Node('C')),
                        listOf(Node('D')),
                        true),
                TestCase(listOf(Node('A')),
                        listOf(Node('B'), Node('C')),
                        listOf(Node('D')),
                        true),
                TestCase(listOf(Node('A')),
                        listOf(Node('B'), Node('C')),
                        listOf(Node('D'), Node('E')),
                        true),
                null
        ).map {
            it.run {
                val firstLinkedList = link(firstListPrefix)!!
                val secondLinkedList = link(secondListPrefix)!!
                val suffixList = listSuffix?.let { link(it) }
                suffixList?.let {
                    firstLinkedList.last.next = suffixList
                    secondLinkedList.last.next = suffixList
                }
                data(firstLinkedList, secondLinkedList, expected)
            }
        }.toTypedArray()
        on("firstList %s, secondList %s", with = *data) {
            firstList, secondList, expected ->
            it("returns $expected") {
                assertThat(listsAreOverlapping(firstList, secondList))
                        .isEqualTo(expected)
            }
        }
    }
})
