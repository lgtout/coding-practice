package com.lagostout.elementsofprogramminginterviews.honors

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ProductsOfAllEntriesButOneSpek : Spek({

    fun computeByBruteForce(list: List<Int>): List<Int> {
        return list
                .fold(1) { acc, i -> acc * i }
                .let { product ->
                    list.map { product / it }
                }.also {
                    println(it)
                }
    }

    val data = listOfNotNull(
        listOf(1,1),
        listOf(1,2),
        listOf(1,2,3,4,5),
        listOf(5,4,3,2,1),
        null
    ).map {
        data(it, computeByBruteForce(it))
    }.toTypedArray()

    describe("productsOfAllEntriesButOne") {
        on("list %s", with = *data) { list, expected ->
            val products = productsOfAllEntriesButOne(list)
            it("should return $expected") {
                assertThat(products).isEqualTo(expected)
            }
        }
    }

})