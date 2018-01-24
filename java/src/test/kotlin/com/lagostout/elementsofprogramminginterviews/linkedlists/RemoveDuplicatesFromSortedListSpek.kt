package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object RemoveDuplicatesFromSortedListSpek : Spek({
    describe("removeDuplicatesFromSortedList") {
        val data = listOfNotNull(
                listOf(1),
                listOf(1,2),
                listOf(1,1,2),
                listOf(1,1,2,2,2,3),
                null
        ).map {
            data(toLinkedList(it), it.distinct().let(::toLinkedList))
        }.toTypedArray()
        on("list: %s", with = *data) { list, expected ->
            it("returns $expected") {
                removeDuplicatesFromSortedList(list)
                assertThat(list).isEqualToIgnoringGivenFields(expected, "id", "next")
            }
        }
    }
})