package com.lagostout.elementsofprogramminginterviews.strings

import com.lagostout.elementsofprogramminginterviews.strings.TelexEncoding.encodeAsTelex
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object TelexEncodingSpek : Spek({

    describe("encodeAsTelex()") {
        val data = listOf(
//                "",
//                " ",
//                "A",
//                " A",
//                "A ",
//                "A A",
                "A. AB;?  C!",
                null
        ).filterNotNull().map { string ->
            var encodedString = string
            TelexEncoding.punctuationToEncodingMap.forEach {
                encodedString = encodedString.replace(
                        Regex("\\" + it.key.toString()), it.value.joinToString(""))
            }
            println(encodedString)
            val chars: MutableList<Char?> =
                    List(encodedString.length, { string[it] }).toMutableList()
            data(chars, encodedString.toList())
        }.toTypedArray()
        on("chars: %s", with = *data) { chars, expected ->
//            println(chars)
//            println(expected)
            it("returns $expected") {
                encodeAsTelex(chars)
                assertEquals(expected, chars.toList())
            }
        }
    }

})