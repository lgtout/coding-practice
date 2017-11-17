package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.RenderACalendar.EventTimeType.END
import com.lagostout.elementsofprogramminginterviews.sorting.RenderACalendar.EventTimeType.START
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * Problem 14.4.1 page 244
 */
object RenderACalendar {
    enum class EventTimeType {
        START, END
    }
    data class Event(var start: Int = 0, var end: Int = 0)
    data class EventTime(val type: EventTimeType, val time: Int)
    fun computeMaximumNumberOfEventsThatCanTakePlaceConcurrently(
            events: List<Event>): Int {
        return events.flatMap {
            listOf(EventTime(START, it.start), EventTime(END, it.end))
        }.sortedBy { it.time }.let {
            var maxLevel = 0
            var level = 0
            it.forEach {
                when (it.type) {
                    START -> ++level
                    END -> --level
                }
                maxLevel = maxOf(level, maxLevel)
            }
            maxLevel
        }
    }
}

object RenderACalendar_PriorityQueue {
    data class Event(val start: Int = 0, val end: Int = 0)
    class Level : Comparable<Level> {
        private val events = mutableListOf<Event>()
        val end: Int
            get() = if (events.isEmpty()) 0
            else events.last().end
        fun add(event: Event) {
            events.add(event)
        }
        override fun compareTo(other: Level): Int {
            return end.compareTo(other.end)
        }
        override fun toString(): String {
            return ReflectionToStringBuilder.reflectionToString(
                    this, ToStringStyle.SHORT_PREFIX_STYLE)
        }
    }
    fun computeMaximumNumberOfEventsThatCanTakePlaceConcurrently(
            events: List<Event>): Int {
        val levels = PriorityQueue<Level>(Comparator { l1, l2 ->
            l1.compareTo(l2)
        }).apply { if (events.isNotEmpty()) add(Level()) }
        events.sortedBy {
            it.start
        }.forEach { event ->
            levels.peek()?.let { levelWithEarliestEnd ->
                if (levelWithEarliestEnd.end > event.start) {
                    levels.add(Level().apply { add(event) })
                } else {
                    levelWithEarliestEnd.add(event)
                }
            }
        }
        return levels.size
    }
}
