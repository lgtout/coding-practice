package com.lagostout.elementsofprogramminginterviews.graphs

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

class PaintBooleanMatrixSpek : Spek({
    describe("") {
        // TODO
        // Verify solution by checking that all unchanged cells are
        // unreachable from changed ones.
    }
}) {
    companion object {

        data class TestCase(val grid: List<List<PaintBooleanMatrix.PointColor>>)

    }
}