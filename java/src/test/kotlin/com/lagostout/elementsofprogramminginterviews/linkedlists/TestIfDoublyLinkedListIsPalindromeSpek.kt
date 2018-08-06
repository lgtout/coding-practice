package com.lagostout.elementsofprogramminginterviews.linkedlists

import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TestIfDoublyLinkedListIsPalindromeSpek : Spek({

    val data: Array<Data2<LinkedListNode<Alphabet>, LinkedListNode<Alphabet>, Boolean>> = listOfNotNull(
        Pair(listOf(A), true),
        Pair(listOf(A,B), false),
        Pair(listOf(A,B,A), true),
        Pair(listOf(A,B,B,A), true),
        Pair(listOf(A,B,B,A,C), false),
        Pair(listOf(A,C,B,B,A), false),
        null
    ).map { (rawList, expected) ->
        val list = toDoublyLinkedList(*rawList.toTypedArray())
       data(list, list.tail, expected)
    }.toTypedArray()

    describe("testIfDoublyLinkedListIsPalindrome") {
        on("list %s", with = *data) { list, tail, expected ->
            val isPalindromic = doublyLinkedListIsPalindrome(list, tail)
            it("should return $expected") {
                assertThat(isPalindromic).isEqualTo(expected)
            }
        }
    }

})