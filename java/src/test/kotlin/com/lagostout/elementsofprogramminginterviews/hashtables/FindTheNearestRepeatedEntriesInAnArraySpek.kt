package com.lagostout.elementsofprogramminginterviews.hashtables

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

private data class DataRow(val words:List<String>, val expected:String?)

class FindTheNearestRepeatedEntriesInAnArraySpek : Spek({
    given("an array of words, where words may repeat") {
        val dataRows = listOf(
                DataRow(listOf(), null),
                DataRow(listOf("foo"), null),
                DataRow(listOf("foo", "foo"), "foo"),
                DataRow(listOf("foo", "bar", "foo"), "foo"),
                DataRow(listOf("foo", "bar", "foo", "bar"), "foo"),
                DataRow(listOf("foo", "bar", "zam", "bar", "foo"), "bar"),
                DataRow(listOf("foo", "bar", "zam", "boom", "bam", "boom"), "boom"),
                DataRow(listOf("foo", "bar", "zam", "boom", "bar", "boom", "zam"), "boom"))
        dataRows.forEach {
            on("finding the nearest repeated word") {
                it("returns the word") {
                    assertEquals(it.expected, nearestRepeatedWord(it.words))
                }
            }
        }
    }
})
