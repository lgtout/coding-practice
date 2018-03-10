package com.lagostout.bytebybyte.dynamicprogramming

import kotlin.math.max

/*
Consider dropping eggs off of a building. If you drop an egg from the Xth
floor and it breaks, then you know for all eggs dropped from floor X or above,
they will break. Similarly, if you drop an egg from floor X and it doesn't
break, no egg will break when dropped from floors 1 through X. Given a building
with N floors and E eggs, find the minimum number of drops needed to determine
the maximum floor that you can drop an egg from without it breaking.

eg.

eggs = 1
floors = 10
eggDrop(eggs, floors) = 10
If you only have one egg, you need to try every floor from 1 to floors.

eggs = 2
floors = 10
eggDrop(eggs, floors) = 4
Drop the egg from the 4th floor, then the 7th, then the 10th. Each time, if
the egg breaks, go to one above the previous test and progress up linearly.
*/

/*
Figuring out what this problem is asking us to provide may be the most
difficult part of solving it.

It's not asking for a single optimal algorithm for dropping eggs at
specific floors that will yield the minimum number of drops to determine the
maximum floor an egg can be dropped from without breaking in all cases.
E.g. Always drop from floors 4, 8, 10. If the egg breaks drop from such and
such floors. And so on.

Instead, imagine that there are classes of algorithms, where algorithms in
the same class use the same number of drops to find the maximum safe floor.
If the minimum number of drops is 4, then there exists _some_ algorithm in
the class, i.e. some set of 4 floors from which to drop the eggs that will
discover the maximum safe floor, wherever it is.  However, the set of floors
for a given maximum safe floor might be different from that of some other
maximum safe floor.
*/
object EggDrop {

    fun computeWithRecursionByBruteForce(eggs: Int, floors: Int): Int {
        fun compute(eggs: Int, floors: Int): Int {
            return when {
                floors <= 0 -> 0
                floors == 1 -> 1
                eggs == 1 -> compute(eggs, floors - 1) + 1
                else -> {
                    (1..floors).map {
                        max(compute(eggs - 1, it - 1),
                            compute(eggs, floors - it)) + 1
                    }.min()!!
                }
            }
        }
        return compute(eggs, floors)
    }

    fun computeWithRecursionAndMemoization(eggs: Int, floors: Int): Int {
        val cache = mutableMapOf<Pair<Int, Int>, Int>()
        fun compute(eggs: Int, floors: Int): Int {
            val key = Pair(eggs, floors)
            return cache[key] ?: run {
                when {
                    floors <= 0 -> 0
                    floors == 1 -> 1
                    eggs == 1 -> compute(eggs, floors - 1) + 1
                    else -> {
                        (1..floors).map {
                            max(compute(eggs - 1, it - 1),
                                compute(eggs, floors - it)) + 1
                        }.min()!!
                    }
                }.also {
                    cache[key] = it
                }
            }
        }
        return compute(eggs, floors).also { println(cache) }
    }

    fun computeBottomUpWithMemoization(eggs: Int, floors: Int): Int {
        val cache = mutableMapOf<Pair<Int, Int>, Int>().apply {
            putAll((1..floors).map {
                (1 to it) to it
            })
//            putAll((2..eggs).flatMap {
//                (it to floors) to 1
//            }
        }
        (2..eggs).forEach { eggCount ->
            (floors..1).forEach { floorCount ->
                val key = Pair(eggCount, floorCount)
//                cache[key] = max(cache[key.copy(key.first - 1, key.second - 1)],
//                    cache[key.copy(key.first - 1, floorCount - key.second)])!!

            }
            cache
        }
        return 0
    }

}