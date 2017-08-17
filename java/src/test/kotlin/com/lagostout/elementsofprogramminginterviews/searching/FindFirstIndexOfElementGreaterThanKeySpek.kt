package com.lagostout.elementsofprogramminginterviews.searching

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class FindFirstIndexOfElementGreaterThanKeySpek : Spek({
    describe("findFirstIndexOfElementGreaterThanKey") {
        testCases.forEach {
            (items, key, expectedFirstIndexOfElementGreaterThanKey) ->
            given("items: $items, key: $key") {
                it("returns $expectedFirstIndexOfElementGreaterThanKey") {
                    assertEquals(expectedFirstIndexOfElementGreaterThanKey,
                            findFirstIndexOfElementGreaterThanKey(items, key))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val items: List<Int>, val key: Int,
                            val expectedFirstIndexOfElementGreaterThanKey: Int?)
        val testCases = run {
            val randomCases = mutableListOf<TestCase>()
            val testCaseCount = 10
            val listSizeRange = Pair(0,10)
            val itemRange = Pair(-10,10)
            val random = RandomDataGenerator().apply { reSeed(1) }
            (1..testCaseCount).forEach {
                val items = mutableListOf<Int>()
                val listSize = listSizeRange.run {
                    random.nextInt(first, second)
                }
                (1..listSize).forEach {
                    itemRange.apply {
                        items.add(random.nextInt(first, second))
                    }
                }
                items.sort()
                randomCases.apply {
                    val key = items[random.nextInt(0, listSize - 1)]
                    add(TestCase(items, key, items.find {
                        it > key
                    }))
                }
            }
            val manualCases = listOf(
                TestCase(listOf(), 0, null),
                TestCase(listOf(1), 0, 1),
                TestCase(listOf(0), 1, null),
                TestCase(listOf(0), 1, null),
                null).filterNotNull()

            randomCases + manualCases
        }
    }
}