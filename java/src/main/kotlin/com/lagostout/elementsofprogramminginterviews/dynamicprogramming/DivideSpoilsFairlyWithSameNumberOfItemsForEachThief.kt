package com.lagostout.elementsofprogramminginterviews.dynamicprogramming
import com.lagostout.common.takeFrom
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairly.Spoils
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairlyWithSameNumberOfItemsForEachThief.SpoilsSplit.EqualSplit
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.DivideSpoilsFairlyWithSameNumberOfItemsForEachThief.SpoilsSplit.UnequalSplit
import kotlin.math.absoluteValue

/* Problem 17.6.6 page 325 */

object DivideSpoilsFairlyWithSameNumberOfItemsForEachThief {

    sealed class SpoilsSplit {

        object UnequalSplit : SpoilsSplit()

        data class EqualSplit(
                val first: Spoils = Spoils(),
                val second: Spoils = Spoils()) : SpoilsSplit()

        fun toList(): List<Spoils> {
            return when (this) {
                UnequalSplit -> emptyList()
                is EqualSplit -> listOf(first, second)
            }
        }

    }

    // TODO
    // To allow the thieves to split the spoils evenly when there's
    // an odd number of items, we run the following method n - 1 times,
    // excluding one of the items each time.  Then we select the split
    // with the least spread between the thieves' portions.

    fun computeWithRecursionAndBruteForce(items: List<Int>):
            SpoilsSplit {
        fun compute(itemIndex: Int,
                    firstThiefSpoilsValue: Int,
                    secondThiefSpoilsValue: Int,
                    firstThiefItemCount: Int,
                    secondThiefItemCount: Int): SpoilsSplit {
            return when {
                listOf(firstThiefItemCount, secondThiefItemCount)
                        .any { it >= items.size / 2 + 1 } -> UnequalSplit
                itemIndex > items.lastIndex ->
                    EqualSplit(Spoils(), Spoils())
                else -> {
                    val item = items[itemIndex]
                    listOf(
                        Triple(compute(itemIndex + 1,
                            firstThiefSpoilsValue + item,
                            secondThiefSpoilsValue,
                            firstThiefItemCount + 1,
                            secondThiefItemCount), {
                                r: EqualSplit ->
                            r.copy(first = r.first.add(item))
                        }, { r: EqualSplit ->
                            (firstThiefSpoilsValue + r.first.value + item -
                                    (secondThiefSpoilsValue + r.second.value))
                                    .absoluteValue
                        }),
                        Triple(compute(itemIndex + 1,
                            firstThiefSpoilsValue,
                            secondThiefSpoilsValue + item,
                            firstThiefItemCount,
                            secondThiefItemCount + 1), {
                                r: EqualSplit ->
                            r.copy(second = r.second.add(item))
                        }, { r: EqualSplit ->
                            (secondThiefSpoilsValue + r.second.value + item -
                                    (firstThiefSpoilsValue + r.first.value))
                                    .absoluteValue
                        })
                    ).mapNotNull {
                        if (it.first === UnequalSplit) null
                        else Triple(it.first as EqualSplit, it.second, it.third)
                    }.sortedBy {
                        it.third(it.first)
                    }.let {
                        if (it.isEmpty()) EqualSplit()
                        else it.first().let {
                            it.second(it.first)
                        }
                    }
                }
            }
        }
        return compute(0, 0, 0, 0, 0).also {
            println(it)
        }
    }

    data class Key(val itemIndex: Int, val firstThiefSpoilsValue: Int,
                   val secondThiefSpoilsValue: Int)

    // TODO
    fun computeWithRecursionAndMemoization(items: List<Int>):
            Pair<Spoils, Spoils> {
        val cache = mutableMapOf<Key, Pair<Spoils, Spoils>>()
        fun compute(itemIndex: Int, firstThiefSpoilsValue: Int,
                    secondThiefSpoilsValue: Int): Pair<Spoils, Spoils> {
            val key = Key(itemIndex, firstThiefSpoilsValue,
                secondThiefSpoilsValue)
            return cache[key] ?: run {
                if (itemIndex > items.lastIndex) Pair(Spoils(), Spoils())
                else {
                    val item = items[itemIndex]
                    listOf(
                        Triple(compute(itemIndex + 1,
                            firstThiefSpoilsValue + item,
                            secondThiefSpoilsValue), {
                                r: Pair<Spoils, Spoils> ->
                            r.copy(first = r.first.add(item))
                        }, { r: Pair<Spoils, Spoils> ->
                            (firstThiefSpoilsValue + r.first.value + item -
                                    (secondThiefSpoilsValue + r.second.value))
                                    .absoluteValue
                        }),
                        Triple(compute(itemIndex + 1,
                            firstThiefSpoilsValue,
                            secondThiefSpoilsValue + item), {
                                r: Pair<Spoils, Spoils> ->
                            r.copy(second = r.second.add(item))
                        }, { r: Pair<Spoils, Spoils> ->
                            (secondThiefSpoilsValue + r.second.value + item -
                                    (firstThiefSpoilsValue + r.first.value))
                                    .absoluteValue
                        })
                    ).sortedBy {
                        it.third(it.first)
                    }.first().let {
                        it.second(it.first)
                    }
                }
            }.also {
                cache[key] = it
            }
        }
        return compute(0, 0, 0)
    }

    // TODO
    fun computeBottomUpWithMemoization(items: List<Int>):
            Pair<Spoils, Spoils> {
        return when {
            items.isEmpty() -> Pair(Spoils(), Spoils())
            items.size == 1 -> Pair(Spoils(items[0]), Spoils())
            else -> {
                val cache = MutableList<Set<Pair<Spoils, Spoils>>>(items.size) {
                    mutableSetOf()
                }
                items[0].let {
                    cache[0] = setOf(Pair(Spoils(it), Spoils()),
                        Pair(Spoils(), Spoils(it)))
                }
                items.withIndex().takeFrom(1).forEach { (index, item) ->
                    cache[index] = cache[index - 1].flatMap {
                        setOf(it.copy(first = it.first.add(item)),
                            it.copy(second = it.second.add(item)))
                    }.toSet()
                }
                cache[items.lastIndex].sortedBy {
                    (it.first.value - it.second.value).absoluteValue
                }.first()
            }
        }
    }

}