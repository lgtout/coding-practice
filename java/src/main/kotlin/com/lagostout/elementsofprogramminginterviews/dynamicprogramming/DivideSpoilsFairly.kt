package com.lagostout.elementsofprogramminginterviews.dynamicprogramming
import com.lagostout.common.takeFrom
import kotlin.math.absoluteValue

/* Problem 17.6.5 page 325 */

object DivideSpoilsFairly {

    data class Spoils (val value: Int = 0, val items: List<Int> = emptyList()) {
        constructor(items: List<Int>) : this(items.sum(), items)
        fun add(item: Int): Spoils = Spoils(
            value = value + item, items = listOf(item) + items)
    }

    fun computeWithRecursionAndBruteForce(items: List<Int>):
            Pair<Spoils, Spoils> {
        fun compute(itemIndex: Int, firstThiefSpoilsValue: Int,
                    secondThiefSpoilsValue: Int): Pair<Spoils, Spoils> {
            return if (itemIndex > items.lastIndex) Pair(Spoils(), Spoils())
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
        }
        return compute(0, 0, 0)
    }

    data class Key(val itemIndex: Int, val firstThiefSpoilsValue: Int,
                   val secondThiefSpoilsValue: Int)

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