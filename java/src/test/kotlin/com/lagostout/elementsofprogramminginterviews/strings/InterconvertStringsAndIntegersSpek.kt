package com.lagostout.elementsofprogramminginterviews.strings

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals
import com.lagostout.elementsofprogramminginterviews.strings.InterconvertStringsAndIntegers.stringToInteger
import com.lagostout.elementsofprogramminginterviews.strings.InterconvertStringsAndIntegers.integerToString
import kotlin.test.assertFailsWith

class InterconvertStringsAndIntegersSpek : Spek({
    describe("stringToInteger") {
        stringToIntegerTestCases.forEach {
            (number, string) ->
            given("string $string") {
                it("returns $number") {
                    println("string: $string number: $number")
                    assertEquals(number, stringToInteger(string))
                }
            }
        }
        describe("string longer than will fit in 1 32 bit Int") {
            describe("string represents negative number") {
                val stringNumber = Int.MIN_VALUE.toString() + "0"
                given("string $stringNumber") {
                    it("throws an IllegalArgumentException") {
                        assertFailsWith<IllegalArgumentException> {
                            stringToInteger(stringNumber)
                        }
                    }
                }
            }
            describe("string represents positive number") {
                val stringNumber = Int.MAX_VALUE.toString() + "0"
                given("string $stringNumber") {
                    it("throws an IllegalArgumentException") {
                        assertFailsWith<IllegalArgumentException> {
                            stringToInteger(stringNumber)
                        }
                    }
                }
            }
        }
    }
    describe("integerToString") {
        integerToStringTestCases.forEach {
            (number, string) ->
            given("number $number") {
                it("returns $string") {
                    assertEquals(string, integerToString(number))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val integer: Int? = null) {
            val string: String = integer?.toString() ?: ""
            operator fun component2() = string
        }
        private val randomTestCases = run {
            val random = RandomDataGenerator().apply { reSeed(1) }
            val testCaseCount = 20
//            val range = Pair(-5, 5)
            val range = Pair(Int.MIN_VALUE, Int.MAX_VALUE)
            (1..testCaseCount).map {
                val number = random.nextInt(range.first, range.second)
                TestCase(number)
            }
        }
        val stringToIntegerTestCases = randomTestCases.toMutableList().apply {
            add(TestCase())
        }
        val integerToStringTestCases = randomTestCases
    }
}