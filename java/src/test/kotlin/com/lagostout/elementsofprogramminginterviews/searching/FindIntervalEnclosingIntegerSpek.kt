package com.lagostout.elementsofprogramminginterviews.searching

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindIntervalEnclosingIntegerSpek : Spek({
    describe("findIntervalEnclosingInteger") {
        testCases.forEach {
            (list, k, expectedInterval) ->
            given("list: $list, k: $k") {
                it("returns $expectedInterval") {
                   assertEquals(expectedInterval,
                           findIntervalEnclosingInteger(list, k))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val list: List<Int> = emptyList(), val k: Int = 0) {
            private val expectedInterval = list.withIndex().run {
                (find { it.value == k })?.let {
                    Pair(it.index, (this.findLast { it.value == k })?.index)
                } ?: Pair(-1, -1)
            }
            operator fun component3() = expectedInterval
        }
        val testCases = listOf(
                TestCase(),
                TestCase(listOf(1)),
                TestCase(listOf(1), 1),
                TestCase(listOf(1,1), 1),
                TestCase(listOf(1,2,2,3), 2),
                TestCase(listOf(1,2,2), 2),
                null).filterNotNull()
    }
}