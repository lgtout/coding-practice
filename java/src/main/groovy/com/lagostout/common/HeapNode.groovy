package com.lagostout.common

class HeapNode<T> {

    int nodeIndex
    List<T> heap

    HeapNode(int nodeIndex, List<T> heap) {
        this.nodeIndex = nodeIndex
        this.heap = heap
    }

    int leftNodeIndex() {
        (nodeIndex * 2) + 1
    }

    int rightNodeIndex() {
        leftNodeIndex() + 1
    }

    int parentNodeIndex() {
        nodeIndex / 2
    }

    T getValue() {
        isOutsideHeap() ? null : heap[nodeIndex]
    }

    void setValue(T value) {
        if (!isOutsideHeap()) {
            heap[nodeIndex] = value
        }
    }

    boolean isOutsideHeap() {
        return nodeIndex >= heap.size() || nodeIndex < 0
    }

    HeapNode<T> left() {
        new HeapNode<T>(leftNodeIndex(), heap)
    }

    HeapNode<T> right() {
        new HeapNode<T>(rightNodeIndex(), heap)
    }

    HeapNode<T> parent() {
        new HeapNode<T>(parentNodeIndex(), heap)
    }

    void swapValue(HeapNode<T> otherNode) {
        T otherValue = value
        this.value = otherNode.value
        otherNode.value = otherValue
    }

}
