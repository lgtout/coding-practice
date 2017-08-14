package com.lagostout.elementsofprogramminginterviews.searching

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

class FindFirstIndexOfElementGreaterThanKeySpek : Spek({
    describe("") {
        testCases.forEach {

        }
    }
}) {
    companion object {
        class TestCase(val items: List<Int>)
        val testCases = run {
            val cases = mutableListOf<TestCase>()
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
                cases.add(TestCase(items))
            }
            cases
        }
    }
}