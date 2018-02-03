package com.lagostout.bytebybyte

/*
Given an integer representing a given amount of change, write a function to
compute the minimum number of coins required to make that amount of change.

eg. (assuming American coins: 1, 5, 10, and 25 cents)
minCoins(1) = 1 (1)
minCoins(6) = 2 (5 + 1)
minCoins(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
*/

object MinimumNumberOfCoinsToMakeChange {

    private val coins = listOf(1,5,10,25)

    fun computeWithRecursion(amount: Int): Int? {
        return when {
            amount < 0 -> null
            amount == 0 -> 0
            else -> coins.mapNotNull {
                (amount - it).let {
                    computeWithRecursion(it)
                }
            }.min()?.let { it + 1 }
        }
    }

    fun computeWithRecursionAndMemoization(amount: Int): Int {
        val dp = MutableList<Int?>(amount + 1) { null }
        computeWithRecursionAndMemoization(amount, dp)
        return dp[amount]!!
    }

    private fun computeWithRecursionAndMemoization(
            amount: Int, dp: MutableList<Int?>): Int? {
        return when {
            amount < 0 -> null
            amount == 0 -> 0.also {
                dp[amount] = it
            }
            else -> dp[amount] ?: coins.mapNotNull {
                (amount - it).let {
                    computeWithRecursionAndMemoization(it, dp)
                }
            }.min()?.let { (it + 1) }
                    .also { dp[amount] = it }
                    .also { println("computed $amount") }
        }
    }

    fun computeWithMemoizationBottomUp(
            amount: Int): Int {
        val dp = MutableList<Int?>(amount + 1) { null }
        dp[0] = 0
        (1..amount).forEach { currentAmount ->
            coins.mapNotNull {
                (currentAmount - it).let {
                    if (it < 0) null
                    else dp[it]
                }
            }.min()?.let { it + 1 }
                    .also { dp[currentAmount] = it }
        }
        return dp[amount]!!
    }
}

