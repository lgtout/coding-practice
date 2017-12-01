package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestForOverlappingListsSpek : Spek({
    data class TestCase(val firstListPrefix: LinkedListNode<Char>,
                        val secondListPrefix: LinkedListNode<Char>,
                        val listSuffix: LinkedListNode<Char>?,
                        val expected: Boolean)
    describe("listsAreOverlapping()") {
        val toLinkedList = { rawNodes:List<Node> ->
            toLinkedListWithExplicitLinkage(rawNodes) }
        val data = listOfNotNull(
                TestCase(toLinkedList(listOf(Node('A'))),
                        toLinkedList(listOf(Node('B'))),
                        toLinkedList(listOf(Node('C'))),
                        true),
                TestCase(toLinkedList(listOf(Node('A'))),
                        toLinkedList(listOf(Node('B'))),
                        null,
                        false),
                TestCase(toLinkedList(listOf(Node('A'), Node('B'))),
                        toLinkedList(listOf(Node('C'))),
                        null,
                        false),
                TestCase(toLinkedList(listOf(Node('A'))),
                        toLinkedList(listOf(Node('B'), Node('C'))),
                        null,
                        false),
                TestCase(toLinkedList(listOf(Node('A'), Node('B'))),
                        toLinkedList(listOf(Node('C'), Node('D'))),
                        null,
                        false),
                TestCase(toLinkedList(listOf(Node('A'), Node('B'))),
                        toLinkedList(listOf(Node('C'))),
                        toLinkedList(listOf(Node('D'))),
                        true),
                TestCase(toLinkedList(listOf(Node('A'))),
                        toLinkedList(listOf(Node('B'), Node('C'))),
                        toLinkedList(listOf(Node('D'))),
                        true),
                TestCase(toLinkedList(listOf(Node('A'))),
                        toLinkedList(listOf(Node('B'), Node('C'))),
                        toLinkedList(listOf(Node('D'), Node('E'))),
                        true),
                null
        ).map {
            it.run {
                listSuffix?.let {
                    firstListPrefix.last.next = listSuffix
                    secondListPrefix.last.next = listSuffix
                }
                data(firstListPrefix, secondListPrefix, expected)
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
