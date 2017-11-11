package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data

object FindClosestEntriesSpek : Spek({
    val data = listOf(
            data(Triple(listOf(1), listOf(1), listOf(1)), Triple(1,1,1)),
            data(Triple(listOf(2), listOf(1), listOf(1)), Triple(1,1,2)),
            data(Triple(listOf(1), listOf(2), listOf(1)), Triple(1,1,2)),
            null
    ).filterNotNull().toTypedArray()
    describe("") {

    }
})
