package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object NormalizePathnamesSpek : Spek({
    describe("shortestEquivalentPath()") {
        val data = listOf(
                data("", expected = ""),
                data(".", expected = "."),
                data("/", expected = "/"),
                data("//", expected = "/"),
                data("/a", expected = "/a"),
                data("/a/b", expected = "/a/b"),
                data("a", expected = "a"),
                data("a/b", expected = "a/b"),
                data("..", expected = ".."),
                data("./a", expected = "a"),
                data("././a", expected = "a"),
                data("./a/././b", expected = "a/b"),
                data("/.", expected = "/"),
                data("../", expected = ".."),
                data("../a", expected = "../a"),
                data("./../a", expected = "../a"),
                data("/a/b/../../", expected = "/"),
                data("a/b/../../", expected = "."),
//                data("a/b/c../../", expected = "a"),
//                data("a/b/.//./c///../../", expected = "a"),
                null
        ).filterNotNull().toTypedArray()
        on("path = %s", with = *data) { path, expected ->
            it("returns $expected") {
                assertEquals(expected, shortestEquivalentPath(path))
            }
        }
        // Exception cases
//        val data2 = listOf(
//                data("/./../a", expected = "../a"),
//                data("/..", expected = "/.."),
//                null
//        ).filterNotNull().toTypedArray()
    }
})