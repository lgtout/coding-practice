package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestIfListIsPalindromicSpek : Spek({

    val data = listOfNotNull(
        data(toLinkedList(A), true),
        data(toLinkedList(A,B), false),
        data(toLinkedList(A,B,A), true),
        data(toLinkedList(A,B,B,A), true),
        data(toLinkedList(A,B,B,A,C), false),
        data(toLinkedList(A,C,B,B,A), false),
        null
    ).toTypedArray()

    describe("listIsPalindromic") {
        on("list %s", with = *data) { list, expected ->
            val isPalindromic = listIsPalindromic(list)
            it("should return $expected") {
                assertThat(isPalindromic).isEqualTo(expected)
            }
        }
    }

})