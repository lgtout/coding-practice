package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/* Problem 17.4 page 319 */

@Suppress("NAME_SHADOWING")
object BinomialCoefficient {

    fun computeWithRecursionAndBruteForce(n: Int, k: Int): Int {
        return if (n < k) 0
        else {
            fun compute(n: Int, k: Int): Int {
                return if (n == k || k == 0) 1
                else compute(n - 1, k) + compute(n - 1, k - 1)
            }
            compute(n,k)
        }
    }

    fun computeWithRecursionAndMemoization(n: Int, k: Int): Int {
        return if (n < k) 0
        else {
            val cache = List(n + 1) { n ->
                MutableList(k + 1) { k ->
                   if (n == k || k == 0) 1 else null
                }
            }
            fun compute(n: Int, k: Int): Int {
                return cache[n][k] ?: run {
                    compute(n - 1, k) + compute(n - 1, k - 1)
                }.also {
                    cache[n][k] = it
                }
            }
            compute(n,k)
        }
    }

    fun computeBottomUpWithMemoization(n: Int, k: Int): Int {
        val cache = List(n + 1) { n ->
            MutableList(k + 1) { k ->
                if (k == 0) 1 else if (n == 0) 0 else null
            }
        }
        (1..n).forEach { n ->
            (1..k).forEach { k ->
                cache[n][k] = cache[n-1][k]!! + cache[n-1][k-1]!!
            }
        }
        return cache[n][k]!!
    }

}
