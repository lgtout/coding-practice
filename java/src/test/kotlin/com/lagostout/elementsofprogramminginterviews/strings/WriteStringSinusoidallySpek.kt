package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object WriteStringSinusoidallySpek : Spek({

    @Suppress("SpellCheckingInspection")
    val data = listOfNotNull(
        data("Hello", "eHlol"),
        data("Hello ", "e Hlol"),
        data("Hello World!", "e lHloWrdlo!"),
        null
    ).toTypedArray()

    describe("writeStringSinusoidally") {
        on("string: %s", with = *data) { string, expected ->
            it("should return $expected") {
                assertThat(writeStringSinusoidally(string))
                        .isEqualTo(expected)
            }
        }
    }

})
