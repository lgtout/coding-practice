package com.lagostout.elementsofprogramminginterviews.graphs

import java.util.*

/* Problem 19.7.2 page 373 */

/* From a complexity perspective, in the case where the graph isn't reused,
I don't see the advantage of constructing a graph, versus using just recursion
with caching. */

fun shortestAdditionChainExponentiation(n: Int): List<Int> {

    // The map value field contains a set of sets of 1 or 2 exponent decompositions.
    // Examples of the inner set: (3,2) - a decomposition of 5;  (2) - a decomposition of 4.
    // We initialize with 1 - the exponent of x^1.
    val exponentToDecompositionsMap = mutableMapOf<Int, Set<Set<Int>>>(Pair(1, emptySet()))

    // Construct a graph of chains.

    run {
        val pending = LinkedList<Int>().apply {
            push(n)
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
                        pending.push(it)
                    }
                }
            }
        }
    }

    // Find the shortest chain.

    val cache = mutableMapOf(Pair(1, setOf(1)))

    fun compute(exponent: Int): Set<Int> {
        return cache[exponent] ?:
        exponentToDecompositionsMap[exponent]?.map { decomposition ->
            val chainExponents = mutableSetOf<Int>()
            decomposition.map {
                listOf(it) + compute(it)
            }.fold(chainExponents) { acc, list ->
                acc.apply { addAll(list) }
            }
            chainExponents
        }?.reduce { shortestChain, chain ->
            if (chain.size < shortestChain.size) chain
            else shortestChain
        }?.also {
            cache[exponent] = it
        }!!
    }

    return (compute(n) + setOf(n)).sorted()
}
