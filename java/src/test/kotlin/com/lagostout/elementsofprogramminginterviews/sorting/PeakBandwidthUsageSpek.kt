package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.Usage
import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.computePeakBandwidthUsage
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object PeakBandwidthUsageSpek : Spek({
   describe("computePeakBandwidthUsage()") {
       val data = listOfNotNull(
               data(listOf(Usage(0, 0, 0)), Usage(0, 0, 0)),
               data(listOf(Usage(0, 1, 0)), Usage(0, 1, 0)),
               data(listOf(Usage(0, 1, 1)), Usage(0, 1, 1)),
               data(listOf(Usage(2, 3, 1)), Usage(2, 3, 1)),
               data(listOf(Usage(0, 1, 0), Usage(1, 2, 0)), Usage(0, 2, 0)),
               data(listOf(Usage(0, 1, 1), Usage(1, 2, 2)), Usage(1, 1, 3)),
               data(listOf(Usage(0, 1, 1), Usage(1, 3, 2),
                       Usage(2, 6, 0), Usage(4, 5, 4)), Usage(4, 5, 4)),
               data(listOf(Usage(0, 1, 1), Usage(1, 3, 2), Usage(2, 6, 0),
                       Usage(4, 5, 4)), Usage(4, 5, 4)),
               data(listOf(Usage(0, 1, 1), Usage(1, 3, 2), Usage(2, 6, 0),
                       Usage(5, 8, 2), Usage(6, 9, 2), Usage(7, 10, 2)),
                       Usage(7, 8, 6)),
               null
       ).toTypedArray()
       on("usages %s", with = *data) { usages, expected ->
            it("returns $expected") {
                assertThat(computePeakBandwidthUsage(usages))
                        .isEqualTo(expected)
            }
       }
   }
})