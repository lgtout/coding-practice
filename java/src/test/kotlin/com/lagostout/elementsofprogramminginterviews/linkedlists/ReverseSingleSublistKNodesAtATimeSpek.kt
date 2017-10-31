package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.elementsofprogramminginterviews.linkedlists.ReverseSingleSublistKNodesAtATime.reverseSingleSublistKNodesAtATime
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReverseSingleSublistKNodesAtATimeSpek : Spek({
    describe("reverseSingleSublistKNodesAtATime()") {
        val data = listOf(
//                Triple(listOf(1), 0, listOf(1)),
//                Triple(listOf(1), 1, listOf(1)),
//                Triple(listOf(1), 2, listOf(1)),
                Triple(listOf(1,2), 1, listOf(2,1)),
                null
        ).filterNotNull().map {
            data(toLinkedList(it.first), it.second, toLinkedList(it.third))
        }.toTypedArray()
        on("list: %s, k: %s", with = *data) { list, k, expected ->
            it("modifies list to $expected") {
                println("list $list")
                assertThat(reverseSingleSublistKNodesAtATime(list, k))
                        .isEqualTo(expected)
            }
        }
    }

})
