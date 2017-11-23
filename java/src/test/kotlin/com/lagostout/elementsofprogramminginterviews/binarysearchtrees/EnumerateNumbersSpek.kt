package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import org.paukov.combinatorics3.Generator

fun generateSample() {
    Generator.permutation((0..15).toList())
            .withRepetitions(2)
            .map { (first, second) ->
                Triple(first, second, first + second * Math.sqrt(2.0))
            }.sortedBy { it.third }
            .forEach {
                println(it)
            }
}
