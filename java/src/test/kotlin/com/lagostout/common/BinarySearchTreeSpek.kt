package com.lagostout.common

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BinarySearchTreeSpek : Spek({
    describe("BinarySearchTree") {

        val tree = BinarySearchTree<Int>()

        context("tree is not empty") {
            on("find") {
                context("key is in tree") {
                    val key = 1
                    tree.insert(key)
                    it("should return BinaryTreeNode containing the key") {
                        assertEquals(key, tree.find(key).value)
                    }
                }
            }
        }

        context("tree is empty") {
            on("find") {
                it("should return null") {
                    assertNull(tree.find(1))
                }
            }
            on("insert") {
                val key = 1
                tree.insert(key)
                it("should contain the inserted key") {
                    assertTrue(tree.contains(key))
                }
            }
            on("contains") {

            }
        }

    }
})