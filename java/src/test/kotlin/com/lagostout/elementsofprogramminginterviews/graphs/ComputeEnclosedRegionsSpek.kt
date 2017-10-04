package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals
import kotlin.collections.mutableListOf as mlo

object ComputeEnclosedRegionsSpek : Spek({
    describe("computeEnclosedRegionsAndFill") {
        val x = true
        val o = false
        val data = listOf(
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(o,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(o,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,o,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(x,o,o,x),
                        mlo(x,x,o,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(o,x,x,x),
                        mlo(x,x,x,x),
                        mlo(o,x,x,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(o,x,x,x),
                                mlo(x,x,x,x),
                                mlo(o,x,x,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(x,x,x,x),
                        mlo(o,o,x,x),
                        mlo(x,x,x,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(o,o,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(o,o,o,x),
                        mlo(x,o,o,x),
                        mlo(x,x,o,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(o,o,o,x),
                                mlo(x,o,o,x),
                                mlo(x,x,o,x),
                                mlo(x,x,x,x))),
                data(listOf(
                        mlo(x,x,x,x),
                        mlo(o,o,o,x),
                        mlo(x,x,x,x),
                        mlo(x,o,o,x),
                        mlo(x,o,o,x),
                        mlo(x,x,x,x)),
                        expected = listOf(
                                mlo(x,x,x,x),
                                mlo(o,o,o,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x),
                                mlo(x,x,x,x))),
                null
        ).filterNotNull().toTypedArray()
        on("grid %s", with = *data) {
            grid: List<MutableList<Boolean>>, expected: List<MutableList<Boolean>> ->
            it("fills grid: $expected") {
                computeEnclosedRegionsAndFill(grid)
                assertEquals(expected, grid)
            }
        }
    }
})