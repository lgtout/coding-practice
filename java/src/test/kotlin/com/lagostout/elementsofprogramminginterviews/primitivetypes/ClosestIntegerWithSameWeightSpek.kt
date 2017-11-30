package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.paukov.combinatorics3.Generator
import java.math.BigInteger

fun findClosestNeighborsWithSameBitCount(v: String = "111100"): List<Pair<String, BigInteger>> {
    val bv = BigInteger(v, 2)
    return Generator.permutation(*(v.toCharArray().toTypedArray())).simple()
            .map {
                val s = it.joinToString(separator = "")
                val n = BigInteger(s, 2)
                Pair(s, n)
            }.sortedBy{ it.second }.withIndex().let { all ->
        all.find {
            it.value.second == bv
        }!!.let {
            mutableListOf<Pair<String, BigInteger>>().apply {
                if (it.index > 0) add(all.elementAt(it.index - 1).value)
                if (it.index < all.last().index)
                    add(all.elementAt(it.index + 1).value)
            }
        }
    }
}
