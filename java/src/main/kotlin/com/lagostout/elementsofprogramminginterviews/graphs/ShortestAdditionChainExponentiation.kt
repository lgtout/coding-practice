package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/* Problem 19.7.2 page 373 */

object ShortestAdditionChainExponentiation {

    @Suppress("NAME_SHADOWING")
    fun shortestAdditionChainExponentiation(exponent: Int): Pair<List<Int>, Int> {

        val exponentToDecompositionsMap = mutableMapOf<Int, Set<Set<Int>>>()

        // Construct the graph.

        run {
            val pending = LinkedList<Int>().apply {
                push(exponent)
            }
            while (pending.isNotEmpty()) {
                val exponent = pending.pop()
                if (exponentToDecompositionsMap.containsKey(exponent)) continue
                val middleExponent = exponent / 2
                if (exponent % 2 == 0) {
                    exponentToDecompositionsMap[exponent] = setOf(setOf(middleExponent))
                    if (!exponentToDecompositionsMap.containsKey(middleExponent)) {
                        pending.push(middleExponent)
                    }
                } else {
                    (1..middleExponent).map {
                        setOf(exponent - it, it)
                    }.toSet().let { decompositions ->
                        exponentToDecompositionsMap[exponent] = decompositions
                        decompositions.flatMap { it }.forEach {
                            if (!exponentToDecompositionsMap.containsKey(it)) {
                                pending.push(it)
                            }
                        }
                    }
                }
            }
        }

        // Find the shortest chain.

        val pending = LinkedList<Set<Set<Int>>>()
                .apply { push(setOf(setOf(exponent))) }

        TODO()
    }
}
