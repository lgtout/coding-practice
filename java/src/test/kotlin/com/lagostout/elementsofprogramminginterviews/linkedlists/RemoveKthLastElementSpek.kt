package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data

object RemoveKthLastElementSpek : Spek({
    describe("removeKthLastElement") {
        val data: Array<Data2<LinkedListNode<Alphabet>, Int, out Alphabet?>> = listOfNotNull(
                data(toLinkedList(A), 1, A),
                data(toLinkedList(A,B,C,D), 1, D),
                data(toLinkedList(A,B,C,D), 2, C),
                data(toLinkedList(A,B,C,D), 4, A),
                data(toLinkedList(A,B,C,D), 5, null as Alphabet?),
                null
        ).toTypedArray()
        data.forEach { (list, k, expected) ->
            given("list: $list, k: $k") {
                it("returns $expected") {
                    val node = removeKthLastElement(list, k)
                    assertThat(node?.data).isEqualTo(expected)
                }
            }
        }
    }
})