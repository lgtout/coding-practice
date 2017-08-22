package com.lagostout.elementsofprogramminginterviews.recursion

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class TowersOfHanoiSpek : Spek({
    describe("") {
        testCases.forEach {
            (ringCount) ->
            given("") {
                it("") {
                    // TODO Pass Pegs to both verification and solution, so they don't make assumptions about them?
                    assertTrue(operationSequenceIsValid(
                            TowersOfHanoi.transferRingsFromOnePegToAnother(ringCount)))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val ringCount: Int = 0)
        val testCases = listOf(TestCase(),
                null).filterNotNull()
        fun operationSequenceIsValid(operations: List<TowersOfHanoi.RingMove>): Boolean {
            // TODO Perform operations and reach goal
            return false
        }
    }
}