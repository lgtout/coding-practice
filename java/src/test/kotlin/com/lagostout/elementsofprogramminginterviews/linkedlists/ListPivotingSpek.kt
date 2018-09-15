package com.lagostout.elementsofprogramminginterviews.linkedlists;

import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ListPivotingSpek : Spek({

    val data = listOfNotNull(
        Triple(listOf(A), 0, listOf(0)),
        Triple(listOf(A, B), 1, listOf(0,1)),
        Triple(listOf(A, B), 0, listOf(0,1)),
        Triple(listOf(A, B, C), 0, listOf(0,1,2)),
        Triple(listOf(A, B, C), 1, listOf(0,1,2)),
        Triple(listOf(A, B, C), 2, listOf(0,1,2)),
        Triple(listOf(A, C, B), 1, listOf(0,2,1)),
        Triple(listOf(B, C, A), 0, listOf(2,0,1)),
        Triple(listOf(B, C, A), 0, listOf(0,0,2)),
        Triple(listOf(B, C, A, A), 0, listOf(2,3,0,1)),
        Triple(listOf(B, C, A, A, B), 0, listOf(1,2,3,0,4)),
        Triple(listOf(B, C, A, C, A, C, B, C, D), 1, listOf(0,2,4,6,1,3,5,7,8)),
        null
    ).map { (list, pivot, expected) ->
        val nodes = list.map {
            LinkedListNode(it)
        }
        val head = nodes.first()
        list.drop(1).fold(head) { previous, current ->
            LinkedListNode(current).also {
                previous.next = it
            }
        }
        val expectedNodes = expected.map { nodes[it] }
        data(head, list[pivot], expectedNodes)
    }.toTypedArray()

    fun nodesToString(nodes: List<LinkedListNode<Alphabet>>) {
        nodes.map {
            ReflectionToStringBuilder.toStringExclude(this, "next")
        }
    }

    describe("listPivoting") {
        on("nodes: %s, k: %s", with = *data) { head, pivotValue, expected ->
            it("should modify nodes to ${nodesToString(expected)}") {
                listPivoting(head, pivotValue)
                val list = head.toList()
                assertThat(list).isEqualTo(expected)
            }
        }
    }

})
