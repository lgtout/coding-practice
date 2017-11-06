package com.lagostout.elementsofprogramminginterviews.sorting

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import com.lagostout.elementsofprogramminginterviews.sorting.RenderACalendar.Event
import com.lagostout.elementsofprogramminginterviews.sorting.RenderACalendar.computeMaximumNumberOfEventsThatCanTakePlaceConcurrently
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.on

object RenderACalendarSpek : Spek({
    val data = listOf(
            data(emptyList(), 0),
            data(listOf(Event(0,0)), 1),
            data(listOf(Event(0,1)), 1),
            data(listOf(Event(0,1), Event(1,2)), 1),
            data(listOf(Event(0,1), Event(2,3)), 1),
            data(listOf(Event(0,1), Event(0,2)), 2),
            data(listOf(Event(0,2), Event(1,3)), 2),
            data(listOf(Event(1,3), Event(0,2)), 2),
            data(listOf(Event(1,3), Event(0,2), Event(2,3)), 2),
            data(listOf(Event(1,3), Event(0,2), Event(2,3)), 2),
            null
    ).filterNotNull().toTypedArray()
    describe("computeMaximumNumberOfEventsThatCanTakePlaceConcurrently()") {
        on("events %s", with = *data) { events, expected ->
            it("returns $expected") {
                assertThat(computeMaximumNumberOfEventsThatCanTakePlaceConcurrently(events))
                        .isEqualTo(expected)
            }
        }
    }
})