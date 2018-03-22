package com.lagostout.elementsofprogramminginterviews.dynamicprogramming.knapsackproblem

import com.lagostout.common.nextInt
import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.data_driven.data
import org.paukov.combinatorics3.Generator

val randomData = run {
    val caseCount = 100
    val itemCountRange = (0..5)
    val valueRange = (0..5)
    val weightRange = (0..10)
    val random = RandomDataGenerator().apply { reSeed(1) }
    (0..caseCount).map {
        (0..random.nextInt(itemCountRange)).map {
            Clock(random.nextInt(valueRange),
                random.nextInt(weightRange))
        }.let {
            val maxWeight = random.nextInt(0,
                (it.sumBy { it.weight } * 1.25).toInt())
            Triple(it, maxWeight,
                Generator.subset(it).simple().flatMap {
                    Generator.permutation(it).simple()
                }.filter {
                    it.sumBy { it.weight } <= maxWeight
                }.map {
                    it.sumBy { it.value }
                }.max() ?: 0)
        }
    }.map {
        data(it.first, it.second, it.third)
    }.toTypedArray()
}

