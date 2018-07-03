package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.data_driven.data

class RemoveEntriesFromListWhenMoreThanMDuplicates : Spek({

    fun <T : Comparable<T>> removeEntriesByBruteForce(
            list: List<T>, m: Int): LinkedListNode<T>? {
        return list
                .groupingBy { it }.eachCount()
                .filterValues { it <= m }
                .flatMap { (entry, count) ->
                    List(count) { entry }
                }
                .let { toLinkedListOrNull(it) }
    }

    val randomData by memoized {
        val caseCount = 100
        val entryRange = Pair(-5,5)
        val maxListCount = 10
        val random = RandomDataGenerator().apply { reSeed(1) }
        (0 until caseCount).map {
            (0..maxListCount).map {
                random.nextInt(entryRange)
            }.sorted().let {
                val maxEntryCount = it.groupingBy { it }
                        .eachCount().maxBy { it.value }?.value ?: 0
                val m = random.nextInt(0, maxEntryCount + 1)
                data(toLinkedList(it), m, removeEntriesByBruteForce(it, m))
            }
        }.toTypedArray()
    }

    val data = listOfNotNull(
        Pair(listOf(1), 0),
        Pair(listOf(1), 1),
        Pair(listOf(1), 2),
        Pair(listOf(1,2), 0),
        Pair(listOf(1,2), 1),
        Pair(listOf(1,2), 2),
        Pair(listOf(1,1,2), 1),
        Pair(listOf(1,2,2), 1),
        Pair(listOf(1,1,2,2), 1),
        Pair(listOf(1,1,2,3,3), 1),
        Pair(listOf(1,1,2,3,3), 2),
        Pair(listOf(1,1,2,3,3,3,4), 2),
        null
    ).map {
        data(toLinkedList(it.first), it.second, removeEntriesByBruteForce(it.first, it.second))
    }.toTypedArray()

    describe("removeEntriesFromListWhenMoreThanMDuplicates") {
        randomData.forEach { (list, m, expected) ->
            on("list: ${ list.toList().map { it.data } }, m: $m") {
                it("returns ${ expected?.toList()?.map { it.data } }") {
                    val result = removeEntriesFromListWhenMoreThanMDuplicates(list, m)
                    expected?.let {
                        assertThat(result).isEqualToIgnoringGivenFields(expected, "id", "next")
                    } ?: assertThat(result).isNull()
                }
            }
        }
    }

})