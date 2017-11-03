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
        val data = listOf(
//                data(0b0, 0b0),
                data(0b1, 0b1.toLong() shl 63),
//                data(0b10, 0b01),
//                data(0b101, 0b101),
//                data(0b110, 0b011),
//                data(0b110101100, 0b001101011),
                null
        ).filterNotNull().toTypedArray()
        on("number: %s", with = *data) { number, expected ->
            it ("returns ${expected.toBinaryString()}") {
                assertThat(reverseBits(number.toLong()))
                        .inBinary()
                        .isEqualTo(expected)
            }
        }
    }
})
