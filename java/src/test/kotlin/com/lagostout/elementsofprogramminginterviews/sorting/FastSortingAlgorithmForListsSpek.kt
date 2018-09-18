package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.linkedlists.LinkedListNode
import com.lagostout.elementsofprogramminginterviews.linkedlists.toList
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FastSortingAlgorithmForListsSpek : Spek({

    val data = listOfNotNull(
        Pair(listOf(A), listOf(0)),
        Pair(listOf(A,A), listOf(0,1)),
        Pair(listOf(A,B,A), listOf(0,2,1)),
        Pair(listOf(A,B,A,B), listOf(0,2,1,3)),
        Pair(listOf(C,A,B,A,C,B), listOf(1,3,2,5,0,4)),
        null
    ).map { (list, expectedIndices) ->
        val head = LinkedListNode.from(list)
        val nodes = head.toList()
        val expected = expectedIndices.map { nodes[it] }
        data(head, expected)
    }.toTypedArray()

    describe("fastSort") {
        on("list %s", with = *data) { head, expected ->
            it("should return $expected") {
                assertThat(fastSort(head).toList())
                        .containsExactlyElementsOf(expected)
            }
        }
    }

})