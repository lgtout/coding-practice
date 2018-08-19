package com.lagostout.elementsofprogramminginterviews.searching

import com.lagostout.common.nextInt
import com.lagostout.common.rdg
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

/**
 * Our list may contain duplicates.  [Element] allows us to track the distinct
 * position of each element while allowing elements with the same value but
 * different positions to be considered equal.
 **/
private data class Element<T : Comparable<T>>(val value: T, private val position: Int) : Comparable<Element<T>> {

    override fun compareTo(other: Element<T>): Int {
        return value.compareTo(other.value)
    }

    override fun equals(other: Any?): Boolean {
        return when {
            other === this -> true
            else -> value == (other as? Element<*>)?.value
        }
    }

    fun position(): Int {
        return position
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

object FindKthLargestElementWhenDuplicatesPresentSpek : Spek({

    fun computeByBruteForce(list: List<Element<Int>>, k: Int): Element<Int>?  {
        // sorted() uses Collections.sort(), which is a stable sort.
        return if (k > list.size) null else list.toList().sorted()[k-1]
    }

    val randomData = run {
        val rdg = rdg()
        val caseCount = 100
        val elementRange = Pair(0, 5)
        val listSizeRange = Pair(0, 10)
        (0 until caseCount).map { _ ->
            val listSize = rdg.nextInt(listSizeRange)
            val k = rdg.nextInt(1, maxOf(listSize, 1))
            (0 until listSize).map { index ->
                Element(index, rdg.nextInt(elementRange))
            }.let { list ->
                data(list, k, computeByBruteForce(list, k))
            }
        }.toTypedArray()
    }

    val data = listOfNotNull(
        Pair(emptyList(), 1),
        Pair(listOf(1), 1),
        Pair(listOf(1), 2),
        Pair(listOf(1,2), 1),
        Pair(listOf(1,2), 2),
        Pair(listOf(2,1), 2),
        Pair(listOf(5,3), 1),
        Pair(listOf(5,2,3), 3),
        Pair(listOf(2,0,2,1), 2),
        null
    ).map { (list, k) ->
        val elements = list.mapIndexed { index, value ->
            Element(index, value)
        }
        data(elements, k, computeByBruteForce(elements, k))
    }.toTypedArray()

    describe("findKthLargestElementWhenDuplicatesPresent") {
        on("list: %s k: %s", with = *randomData) { list, k, expected ->
            it("should return $expected") {
                val kthLargestElement = findKthLargestElementWhenDuplicatesPresent(list, k)
                assertThat(kthLargestElement?.position()).isEqualTo(expected?.position())
            }
        }
    }

})