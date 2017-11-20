package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.lagostout.common.toBinaryString
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReverseBitsSpek : Spek({
    describe("reverseBits()") {
        val data = listOf<Long?>(
               0b0,
                0b1,
                0b10,
                0b101,
                0b110,
                0b110101100,
                null
        ).filterNotNull().map { number ->
            listOf(number ushr 32, number shl 32 ushr 32).map {
                Integer.reverse(it.toInt()).toLong()
            }.reversed().let {
                it[0] shl 32 or it[1]
            }.let { reversed ->
                data(number, reversed)
            }
        }.toTypedArray()
        on("number: %s", with = *data) { number, expected ->
            it ("returns ${expected.toBinaryString()}") {
                assertThat(reverseBits(number))
                        .inBinary()
                        .isEqualTo(expected)
            }
        }
    }
})
