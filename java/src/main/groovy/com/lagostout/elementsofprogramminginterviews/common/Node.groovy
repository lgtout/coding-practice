package com.lagostout.elementsofprogramminginterviews.common

import com.google.gson.GsonBuilder
import com.google.gson.graph.GraphAdapterBuilder
import org.apache.commons.lang3.builder.RecursiveToStringStyle
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class Node<T> {

    Node parent
    Node left
    Node right
    T value

    Node(T value) {
        this.value = value
    }

    List<Node> children() {
        [left, right]
    }

    boolean isNull() {
        this.value == null
    }

    @Override
    String toString() {
        GsonBuilder gb = new GsonBuilder()
        def gab = new GraphAdapterBuilder()
        gab.addType(this.class)
        gab.registerOn(gb)
        gb.create().toJson(this)
    }

    static Node<T> createNullNode() {
        new Node(null)
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null) return false
        if (obj.class != Node) return false
        obj = (Node) obj
        if (obj.value != value) return false
        true
    }

    @Override
    int hashCode() {
        return super.hashCode()
    }

    static <T> void build(int index, List<List> rawTree, List<Node> tree) {
        List rawNode = rawTree.get(index)
        Node node
        if (rawNode.size() == 4) { // Configure parent
            def parentIndex = rawNode[2] as Integer
            Node parentNode = parentIndex != null ?
                    tree[parentIndex] : null
            node = new Node(rawNode[3] as T)
            node.parent = parentNode
        } else { // No parent
            node = new Node(rawNode[2] as T)
        }
        tree[index] = node
        def leftIndex = rawNode[0] as Integer
        if (leftIndex != null) {
            build(leftIndex, rawTree, tree)
            node.left = tree[leftIndex]
        }
        def rightIndex = rawNode[1] as Integer
        if (rightIndex != null) {
            build(rightIndex, rawTree, tree)
            node.right = tree[rightIndex]
        }
    }

}

