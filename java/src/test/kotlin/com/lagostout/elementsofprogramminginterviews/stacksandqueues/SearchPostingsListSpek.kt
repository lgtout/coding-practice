package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.Entry
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.RawEntry
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.iterativeAssignJumpFirstOrder
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.recursiveAssignJumpFirstOrder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

typealias RE = RawEntry<Char>

object SearchPostingsListSpek : Spek({
    describe("assign jump order") {
        val data = listOf(
                Pair(listOf(RE('a')), listOf(0)),
                Pair(listOf(RE('a', 0)), listOf(0)),
                Pair(listOf(RE('a', 2), RE('b', 3), RE('c', 1), RE('d', 3)),
                        listOf(0, 2, 1, 3)),
                Pair(listOf(RE('a', 2), RE('b', 3), RE('c'), RE('d')),
                        listOf(0, 3, 1, 2)),
                Pair(listOf(RE('a', 3), RE('b', 3), RE('c'), RE('d')),
                        listOf(0, 2, 3, 1)),
                null).filterNotNull().map { (rawEntries, expectedOrder) ->
            val entries = rawEntries.map { Entry(it.value) }.let { entries ->
                entries.forEachIndexed { index, entry ->
                    with (entry) {
                        if (index < rawEntries.lastIndex) {
                            next = entries[index + 1]
                        }
                        rawEntries[index].jumpNext?.let {
                            jumpNext = entries[it]
                        }
                    }
                }
                entries
            }
            Pair(entries, expectedOrder)
        }
        describe("iterativeAssignJumpFirstOrder()") {
            var count = 0
            data.forEach { (entries, expectedOrder) ->
                on("#${++count} $entries") {
                    it("returns assigned order: $expectedOrder") {
                        iterativeAssignJumpFirstOrder(entries[0])
                        assertEquals(expectedOrder, entries.map { it.order })
                    }
                }
            }
        }
        describe("recursiveAssignJumpFirstOrder()") {
            var count = 0
            data.forEach { (entries, expectedOrder) ->
                on("#${++count} $entries") {
                    it("[${++count}] returns assigned order: $expectedOrder") {
                        recursiveAssignJumpFirstOrder(entries[0])
                        assertEquals(expectedOrder, entries.map { it.order })
                    }
                }
            }
        }
    }
})