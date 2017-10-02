package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data

object NormalizePathnamesSpek : Spek({
    describe("shortestEquivalentPath") {
        val data = listOf(
                data("", expected = ""),
                data(".", expected = ""),
                data("/", expected = "/"),
                data("..", expected = ".."),
                data("//", expected = "//"),
                data("/a", expected = "/a"),
                data("/a/b", expected = "/a/b"),
                data("a", expected = "a"),
                data("a/b", expected = "a/b"),
                data("./a", expected = "./a"),
                data("././a", expected = "a"),
                data("./a/././b", expected = "a/b"),
                data("/.", expected = "/"),
                data("/..", expected = "/.."),
                data("../", expected = ".."),
                data("../a", expected = "../a"),
                data("./../a", expected = "../a"),
                data("/./../a", expected = "../a"),
                data("/a/b/../../", expected = "/"),
                data("a/b/../../", expected = "."),
                data("a/b/c../../", expected = "a"),
                data("a/b/.//./c///../../", expected = "a"),
                null
        ).filterNotNull().toTypedArray()
    }
})