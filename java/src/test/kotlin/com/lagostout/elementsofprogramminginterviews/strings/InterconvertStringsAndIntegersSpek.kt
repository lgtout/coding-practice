package com.lagostout.elementsofprogramminginterviews.strings

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import com.lagostout.elementsofprogramminginterviews.strings.InterconvertStringsAndIntegers.stringToInteger
import com.lagostout.elementsofprogramminginterviews.strings.InterconvertStringsAndIntegers.integerToString

class InterconvertStringsAndIntegersSpek : Spek({
    describe("stringToInteger") {
        testCases.forEach {
            (number, string) ->
            given("string $string") {
                it("returns $number") {
                    assertEquals(stringToInteger(string), number)
                }
            }
        }
    }
    describe("integerToString") {
        testCases.forEach {
            (number, string) ->
            given("number $number") {
                it("returns $string") {
                    assertEquals(integerToString(number), string)
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val integer: Int) {
            val string: String = integer.toString()
            operator fun component2() = string
        }
        val testCases = run {
            val random = RandomDataGenerator().apply { reSeed(1) }
            val testCaseCount = 20
            (1..testCaseCount).map {
                val number = random.nextInt(Int.MIN_VALUE, Int.MAX_VALUE)
                TestCase(number)
            }
        }
    }
}