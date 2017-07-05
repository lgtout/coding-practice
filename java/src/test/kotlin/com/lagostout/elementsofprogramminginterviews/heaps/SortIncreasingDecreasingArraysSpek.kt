package com.lagostout.elementsofprogramminginterviews.heaps

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

class SortIncreasingDecreasingArraysSpek : Spek({
    describe("sortIncreasingDecreasingArrays") {

    }
}) {
    companion object {
        data class TestCase(val array: List<Int>) {
            val expected = array.sorted()
            operator fun component2() = expected
        }
        private val testCases = listOf(
                TestCase(listOf()),
                TestCase(listOf(1)),
                TestCase(listOf(1,2,1)),
                TestCase(listOf(1,2,1,4,5,2,2)),
                null).filterNotNull()
    }
}