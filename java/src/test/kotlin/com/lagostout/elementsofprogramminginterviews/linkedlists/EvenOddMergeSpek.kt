package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object EvenOddMerge : Spek({

    val data = listOfNotNull(
        data(toLinkedList(A), toLinkedList(A)),
        data(toLinkedList(A,B), toLinkedList(A,B)),
        data(toLinkedList(A,B,C), toLinkedList(A,C,B)),
        data(toLinkedList(A,B,C,D), toLinkedList(A,C,B,D)),
        data(toLinkedList(A,B,C,D,E), toLinkedList(A,C,E,B,D)),
        null
    ).toTypedArray()

    describe("evenOddMerge") {
        on("list %s", with = *data) { list, expected ->
            evenOddMerge(list)
            it("should transform the list to $expected") {
                assertThat(list).isEqualToComparingOnlyGivenFields("value")
            }
        }
    }

})