package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.BoundaryType.END
import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.BoundaryType.START
import java.util.*

object PeakBandwidthUsage {
    data class Usage(val bandwidth: Int, val start: Int, val end: Int)
    enum class BoundaryType {
        START, END
    }
    data class Boundary(val type: BoundaryType, val time: Int, val bandwidth: Int) : Comparable<Boundary> {
        override fun compareTo(other: Boundary): Int = time.compareTo(other.time)
    }
    fun computePeakBandwidthUsage(usages: List<Usage>): Usage {
        return usages.flatMap { (start, end, bandwidth) ->
            listOf(START to start, END to end).map {
                Boundary(it.first,  it.second, bandwidth)
            }
        }.sortedBy { it.time }.let {
            val boundaryStack = LinkedList<Boundary>()
            var maxUsage: Usage? = null
            var bandwidthUsage = 0
            it.forEach {
                when (it.type) {
                    START -> {
                        boundaryStack.push(it)
                        bandwidthUsage += it.bandwidth
                    }
                    END -> {
                        val start = boundaryStack.pop()
                        // TODO
                        // Figure out how to deal with coincident start/end times.
                        // Does it matter if the bandwidths at such times are the
                        // same or different?
//                        if (start.time != it.time) {
                            maxUsage = maxUsage?.let {
                                if (bandwidthUsage <= it.bandwidth)
                                    maxUsage else null
                            } ?: Usage(bandwidthUsage, start.time, it.time)
//                        }
                        bandwidthUsage -= it.bandwidth
                    }
                }
            }
            // This is safe as long as there's at least one entry in
            // the usages parameter.
            maxUsage!!
        }

    }
}