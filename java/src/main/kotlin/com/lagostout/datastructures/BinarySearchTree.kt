package com.lagostout.datastructures

import com.google.common.annotations.VisibleForTesting
import com.lagostout.common.BinaryTreeNode

interface BinarySearchTreeable <T : Comparable<T>> {
    fun find(key: T): BinaryTreeNode<T>?
    fun findInsertionPoint(key: T): BinaryTreeNode<T>
    fun next(key: T): BinaryTreeNode<T>
    fun insert(key: T)
    fun delete(key: T)
    fun nearestNeighbors(key: T): List<BinaryTreeNode<T>>
    fun rangeSearch(startKey: T, endKey: T): List<BinaryTreeNode<T>>
    fun contains(key: T): Boolean
    @VisibleForTesting
    val root: BinaryTreeNode<T>
}

class BinarySearchTree<T : Comparable<T>> : BinarySearchTreeable<T> {

    override fun find(key: T): BinaryTreeNode<T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // AKA modified find
    override fun findInsertionPoint(key: T): BinaryTreeNode<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(key: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val root: BinaryTreeNode<T>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun contains(key: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun next(key: T): BinaryTreeNode<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(key: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nearestNeighbors(key: T): List<BinaryTreeNode<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rangeSearch(startKey: T, endKey: T): List<BinaryTreeNode<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}