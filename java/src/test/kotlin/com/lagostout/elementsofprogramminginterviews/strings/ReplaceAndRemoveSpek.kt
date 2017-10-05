package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

@Suppress("NAME_SHADOWING")
object ReplaceAndRemoveSpek : Spek({
    describe("replaceAndRemove()") {
        val data = listOf(
                data(listOf('c'), expected = listOf('c')),
                data(listOf('d'), expected = listOf('d')),
                data(listOf('d','d'), expected = listOf('d','d')),
                data(listOf('e','d'), expected = listOf('e','d')),
                null
        ).filterNotNull().toTypedArray()
        on("replace and remove operations on %s", with = *data) {
            array, expected ->
            it("modifies array to $expected") {
                val array = array.toMutableList()
                replaceAndRemove(array)
                assertEquals(expected, array)
            }
        }
    }
})
