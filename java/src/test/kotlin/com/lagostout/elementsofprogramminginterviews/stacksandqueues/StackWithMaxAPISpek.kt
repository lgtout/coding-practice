package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class StackWithMaxAPISpek : Spek({
    describe("max") {
        testCases.forEach {
            (stack, expectedMaxItem) ->
            given("stack: $stack") {
                it("returns $expectedMaxItem") {
                    assertEquals(expectedMaxItem, stack.max())
                }
            }
        }
    }
}) {
    companion object {
        class TestCase(items: List<Int>,
                       private val expectedMaxItem: Int?) {
            private val stack = Stack<Int>().apply {
                items.reversed().forEach {
                    push(it)
                }
            }
            operator fun component1() = stack
            operator fun component2() = expectedMaxItem
        }
        val testCases = listOf(
                TestCase(listOf(), null),
                TestCase(listOf(5,4,3,2), 5),
                TestCase(listOf(2,3,4,5), 5),
        null).filterNotNull()
    }
}