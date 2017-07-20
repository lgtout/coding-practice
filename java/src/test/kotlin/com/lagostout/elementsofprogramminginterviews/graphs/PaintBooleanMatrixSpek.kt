package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

class PaintBooleanMatrixSpek : Spek({
    describe("flipRegionColor") {
        // TODO
        // If flip is white to black, verify by comparing components of white
        // in the result to those of white in the input matrix.  They should
        // be identical, except for the component in the first that contains
        // the start point.  And the graph should contain no additional
        // cells e.g. cells of the starting color.

        // Cases:
        // Empty grid
        // Point beyond grid bounds - positive/negative
    }
}) {
    companion object {

        data class TestCase(val grid: List<List<Boolean>>)


    }
}