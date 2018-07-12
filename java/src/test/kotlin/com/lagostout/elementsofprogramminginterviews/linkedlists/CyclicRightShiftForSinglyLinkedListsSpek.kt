package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object CyclicRightShiftForSinglyLinkedListsSpek : Spek({

    val data = listOfNotNull(
        data(toLinkedList(1), 0, toLinkedList(1)),
        data(toLinkedList(1), 1, toLinkedList(1)),
        data(toLinkedList(1), 2, toLinkedList(1)),
        data(toLinkedList(1,2), 1, toLinkedList(2,1)),
        data(toLinkedList(1,2,3,4,5), 1, toLinkedList(5,4,2,3,4)),
        data(toLinkedList(1,2,3,4,5), 3, toLinkedList(3,4,5,1,2)),
        null
    ).toTypedArray()

    describe("cyclicRightShiftForSinglyLinkedLists") {
        on("list %s", with = *data) { list, k, expected ->
            it("should return ${ expected.toList().map { it.data } }") {
                assertThat(cyclicRightShiftForSinglyLinkedLists(list, k))
                        .isEqualToIgnoringGivenFields(expected, "id", "next")
            }
        }
    }

})