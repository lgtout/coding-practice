package com.lagostout.elementsofprogramminginterviews.sorting

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data

import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.BoundaryType.START
import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.BoundaryType.END
import com.lagostout.elementsofprogramminginterviews.sorting.PeakBandwidthUsage.Usage

object PeakBandwidthUsageSpek : Spek({
   describe("computePeakBandwidthUsage()") {
       val data = listOfNotNull(
               data(listOf(Usage(0, 0, 0)), Usage(0, 0, 0)),
               data(listOf(Usage(0, 1, 0)), Usage(0, 1, 0)),
               data(listOf(Usage(0, 1, 0)), Usage(0, 1, 0)),
               data(listOf(Usage(0, 1, 1)), Usage(0, 1, 1)),
               data(listOf(Usage(2, 3, 1)), Usage(2, 3, 1)),
               data(listOf(Usage(2, 3, 1)), Usage(2, 3, 1)),
               data(listOf(Usage(0, 1, 0), Usage(1, 2, 0)), Usage(0, 1, 0)),
               data(listOf(Usage(0, 1, 0), Usage(1, 2, 0)), Usage(0, 1, 0)),
               null
       ).toTypedArray()
   }
})