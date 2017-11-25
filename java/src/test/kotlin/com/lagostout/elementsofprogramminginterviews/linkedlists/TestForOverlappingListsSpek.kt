package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data

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
        }
    }
})
