package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek

object ReverseWordsInSentenceSpek : Spek({
    val data = listOf(
            "",
            null
    ).filterNotNull().map {
        it.reversed().split(Regex("(?=\\s)|(?<=\\s)"))
                .map {
//                    if (it.matches(Regex("^\\s*$")))
                }
    }.map {}
})