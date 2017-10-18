package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.RawEntry
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.Entry
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.iterativeAssignJumpFirstOrder
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.SearchPostingsList.recursiveAssignJumpFirstOrder
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

typealias RE = RawEntry<Char>
object SearchPostingsListSpek : Spek({
    describe("") {
        val data = listOf(
                Pair(listOf(RE('a')), listOf(0)),
                Pair(listOf(RE('a', 2), RE('b', 3), RE('c', 1), RE('d', 3)),
                        listOf(0, 2, 1, 3)),
                null).filterNotNull().map { (rawEntries, expectedOrder) ->
            val entries = rawEntries.map { Entry(it.value) }.let { entries ->
                entries.forEachIndexed { index, entry ->
                    with (entry) {
                        if (index <= rawEntries.lastIndex) {
                            next = entries[index + 1]
                        }
                        rawEntries[index].jumpNext?.let {
                            jumpNext = entries[it]
                        }
                    }
                }
                entries
            }
            data(entries, expectedOrder)
        }.toTypedArray()
        on("", with = *data) { entries, expectedOrder ->
            // TODO
        }
    }
})