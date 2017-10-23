package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.anyOf
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindArrayEntryEqualToItsIndexWhenThereMayBeDuplicatesSpek : Spek({
    describe("findArrayEntryEqualToItsIndexWhenThereMayBeDuplicates()") {
        val data = listOf<Data1<List<Int>, List<Int>>?>(
//                data(emptyList(), emptyList()),
                data(listOf(1), emptyList()),
//                data(listOf(-1), emptyList()),
//                data(listOf(-1,0), emptyList()),
//                data(listOf(0), listOf(0)),
//                data(listOf(0,1), listOf(0,1)),
//                data(listOf(0,0,2), listOf(0)),
//                data(listOf(-1,1), listOf(1)),
//                data(listOf(-1,-1,1), emptyList()),
//                data(listOf(-1,1,1,1,1,1,1), listOf(1)),
//                data(listOf(-1,1,1,2,3,4,6,7,8,9,10), listOf(1)),
//                data(listOf(-1,0,1,2,3,4,5,7,8,9,10), listOf(7,8,9,10)),
//                data(listOf(-1,0,1,2,3,4,5,6,6,9,11), listOf(9)),
                // TODO More cases - especially those that examine both sides of mid
//                data(listOf(), listOf()),
                null
        ).filterNotNull().toTypedArray()
        on("array %s", with = *data) { array, expected ->
            it("returns one of $expected") {
                assertThat(findArrayEntryEqualToItsIndexWhenThereMayBeDuplicates(array))
                        .`is`(anyOf(object: Condition<Int?>(){
                            override fun matches(value: Int?): Boolean {
                                return value == null && expected.isEmpty()
                            }
                        }, object: Condition<Int?>() {
                            override fun matches(value: Int?): Boolean {
                                return expected.contains(value)
                            }
                        }))
            }
        }
    }
})