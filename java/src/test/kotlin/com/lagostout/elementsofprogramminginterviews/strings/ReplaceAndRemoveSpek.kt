package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

@Suppress("NAME_SHADOWING")
object ReplaceAndRemoveSpek : Spek({
    describe("replaceAndRemove()") {
        // Interesting cases: empty; all b's; all a's;
        // replacement d's spill outside list;
        // nulls at end of list; no nulls at end of list.
        val data = listOf<Data1<List<Char?>, List<Char?>>?>(
                data(listOf(), expected = listOf()),
                data(listOf(null), expected = listOf(null)),
                data(listOf('c'), expected = listOf('c')),
                data(listOf('d'), expected = listOf('d')),
                data(listOf('d','d'), expected = listOf('d','d')),
                data(listOf('e','d'), expected = listOf('e','d')),
                data(listOf('a', null), expected = listOf('d', 'd')),
                data(listOf('a','a'), expected = listOf('d','d')),
                data(listOf('e','d','a'), expected = listOf('e','d','d')),
                data(listOf('e','d','a', null), expected = listOf('e','d','d','d')),
                data(listOf('e','d','a', null, null), expected = listOf('e','d','d','d', null)),
                data(listOf('b'), expected = listOf(null)),
                data(listOf('b','b'), expected = listOf(null, null)),
                data(listOf('a','b','b'), expected = listOf('d','d', null)),
                data(listOf('a','b','b','a','a','a'), expected = listOf('d','d','d','d','d','d')),
                data(listOf('a','b','b','a','a','c', null, null),
                        expected = listOf('d','d','d','d','d','d','c', null)),
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
