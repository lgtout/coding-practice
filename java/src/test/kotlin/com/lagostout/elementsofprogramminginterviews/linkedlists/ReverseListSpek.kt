package com.lagostout.elementsofprogramminginterviews.linkedlists

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

class ReverseListSpek : Spek({
    describe("reverseSingleSublist()") {
        val data = listOfNotNull(data(toLinkedList(listOf(1)),
                expected = toLinkedList(listOf(1))), data(toLinkedList(listOf(1, 2)),
                expected = toLinkedList(listOf(2,1))), data(toLinkedList(listOf(1, 2, 3)),
                expected = toLinkedList(listOf(3, 2, 1))), null).toTypedArray()
        on("list: %s", with = *data) { list, expected ->
            it("returns $expected") {
                val head = reverseList(list)
                assertEquals(expected, head)
            }
        }
    }
})
