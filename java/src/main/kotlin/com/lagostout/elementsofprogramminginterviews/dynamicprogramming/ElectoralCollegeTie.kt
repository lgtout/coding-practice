package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.takeFrom

/* Problem 17.6.7 page 325 */

object ElectoralCollegeTie {

    fun computeWithRecursionAndBruteForce(electoralCounts: List<Int>): Boolean {
        fun compute(electoralCountsIndex: Int, firstCandidateCount: Int,
                    secondCandidateCount: Int): Boolean {
            return if (electoralCountsIndex > electoralCounts.lastIndex)
                firstCandidateCount == secondCandidateCount
            else {
                val nextElectoralCountsIndex = electoralCountsIndex + 1
                val electoralCount = electoralCounts[electoralCountsIndex]
                listOf(
                    compute(nextElectoralCountsIndex,
                        firstCandidateCount + electoralCount,
                        secondCandidateCount),
                    compute(nextElectoralCountsIndex, firstCandidateCount,
                        secondCandidateCount + electoralCount)).any { it }
            }
        }
        return compute(0, 0, 0)
    }

    fun computeWithRecursionAndMemoization(electoralCounts: List<Int>): Boolean {
        data class Key(val electoralCountsIndex: Int = 0,
                       val firstCandidateCount: Int = 0,
                       val secondCandidateCount: Int = 0)
        val cache = mutableMapOf<Key, Boolean>()
        fun compute(electoralCountsIndex: Int, firstCandidateCount: Int,
                    secondCandidateCount: Int): Boolean {
            val key = Key(electoralCountsIndex, firstCandidateCount,
                secondCandidateCount)
            return cache[key]?.also {
                println("hit: $key, value: $it")
            } ?: run {
                if (electoralCountsIndex > electoralCounts.lastIndex)
                    firstCandidateCount == secondCandidateCount
                else {
                    val nextElectoralCountsIndex = electoralCountsIndex + 1
                    val electoralCount = electoralCounts[electoralCountsIndex]
                    listOf(
                        compute(nextElectoralCountsIndex,
                            firstCandidateCount + electoralCount,
                            secondCandidateCount),
                        compute(nextElectoralCountsIndex, firstCandidateCount,
                            secondCandidateCount + electoralCount)).any { it }
                }
            }.also {
                cache[key] = it
            }
        }
        return compute(0, 0, 0)
    }

    fun computeBottomUpWithMemoization(electoralCounts: List<Int>): Boolean {
        val cache = MutableList(electoralCounts.size) {
            if (it == 0) {
                electoralCounts[it].let {
                    setOf(Pair(it, 0), Pair(0, it))
                }
            } else emptySet()
        }
        electoralCounts.withIndex().takeFrom(1).map { (index, count) ->
            cache[index] = cache[index - 1].flatMap {
                setOf(it.copy(first = it.first + count),
                    it.copy(second = it.second + count))
            }.toSet()
        }
        return cache[electoralCounts.lastIndex].any {
            it.first == it.second
        }
    }

}