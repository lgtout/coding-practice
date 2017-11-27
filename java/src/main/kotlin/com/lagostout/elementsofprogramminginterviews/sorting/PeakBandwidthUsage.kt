package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.BoundaryType.END
import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.BoundaryType.START
import java.util.*

/**
 * Problem 14.4.2 page 246
 */
object PeakBandwidthUsage {
    data class Usage(val start: Int, val end: Int, val bandwidth: Int)
    enum class BoundaryType {
        START, END
    }
    data class Boundary(val type: BoundaryType, val time: Int, val bandwidth: Int)
        : Comparable<Boundary> {
        override fun compareTo(other: Boundary): Int {
            return (time.compareTo(other.time)).let {
                when(it) {
                    // This should make START appear before END
                    // when there's no difference in boundary time.
                    0 -> type.compareTo(other.type)
                    else -> it
                }
            }
        }
    }
    fun computePeakBandwidthUsage(usages: List<Usage>): Usage {
        return usages.flatMap { (start, end, bandwidth) ->
            listOf(START to start, END to end).map {
                Boundary(it.first,  it.second, bandwidth)
            }
        }.sorted().let {
            val boundaryStack = LinkedList<Boundary>()
            var maxUsage = Usage(0, 0, -1)
            var bandwidthUsage = 0
            // We'll select the widest range with the max
            // bandwidth usage when ranges with that bandwidth
            // are nested.
            it.forEach {
                when (it.type) {
                    START -> {
                        boundaryStack.push(it)
                        bandwidthUsage += it.bandwidth
                    }
                    END -> {
                        val start = boundaryStack.pop()
                        maxUsage = if (bandwidthUsage < maxUsage.bandwidth) maxUsage
                        else Usage(start.time, it.time, bandwidthUsage)
                        bandwidthUsage -= it.bandwidth
                    }
                }
            }
            maxUsage
        }

    }
}