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
                // (node values, k, expected node indices when
                // iterated after reversal)
                Triple(listOf(1), 0, listOf(0)),
                Triple(listOf(1), 1, listOf(0)),
                // We handle the case of k > len(list)
                // same as when (len(list) % k) > 0,
                // i.e. we ignore the excess.
                Triple(listOf(1), 2, listOf(0)),
                Triple(listOf(1,2), 1, listOf(1,0)),
                Triple(listOf(1,2), 2, listOf(1,0)),
                Triple(listOf(1,2,3,4), 2, listOf(3,2,1,0)),
                Triple(listOf(1,2,3,4,5), 2, listOf(3,2,1,0,4)),
                Triple(listOf(1,2,3,4,5), 3, listOf(2,1,0,3,4)),
                null
        ).filterNotNull().map {
            val linkedList = toLinkedList(it.first)
            val list = linkedList.toList()
            val expected = it.third.map { list[it] }
            data(linkedList, it.second, expected)
        }.toTypedArray()
        on("list: %s, k: %s", with = *data) { list, k, expected ->
            it("modifies list to $expected") {
                assertThat(reverseSingleSublistKNodesAtATime(list, k)
                        .toList()).isEqualTo(expected)
            }
        }
    }

})
