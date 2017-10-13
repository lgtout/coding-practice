package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.elementsofprogramminginterviews.arrays.DutchNationalFlagPreservingRelativeOrdering.Element
import com.lagostout.elementsofprogramminginterviews.arrays.DutchNationalFlagPreservingRelativeOrdering.arrangeAsDutchNationalFlag
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

object DutchNationalFlagPreservingRelativeOrderingSpek : Spek({
    describe("arrangeAsDutchNationalFlag()") {
        fun toElements(list: List<Boolean>): List<Element> {
            var trueCount = 1
            return list.map {
                // All false elements will have order 0.
                // True elements will start at 1 for the
                // leftmost, and increment for each one
                // rightwards.
                if (it) Element(trueCount++, it)
                else Element(0, it)
            }
        }
        fun expectedOrder(array: List<Element>): List<Int> {
            return array.run { filter { !it.value }.map { 0 } +
                    filter { it.value }.map { it.order } }
        }
        val data = listOf(
                toElements(mutableListOf(false)),
                toElements(mutableListOf(true)),
                toElements(mutableListOf(false, true)),
                toElements(mutableListOf(true, false)),
                toElements(mutableListOf(true, true, true)),
                toElements(mutableListOf(false, true, false)),
                toElements(mutableListOf(true, false, false, true, false, true)),
                null
        ).filterNotNull()
        data.forEach {
            given("array: $it") {
                it("arranges array, preserving relative order of 'false' elements") {
                    val expected = expectedOrder(it)
                    arrangeAsDutchNationalFlag(it)
                    assertEquals(expected, it.map { it.order })
                }
            }
        }
    }
})
