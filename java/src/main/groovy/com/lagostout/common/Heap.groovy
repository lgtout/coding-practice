package com.lagostout.common

import com.google.common.annotations.VisibleForTesting

class Heap<T> {

    private static enum Type {
        MIN, MAX
    }

    private Type type
    private List<Comparable<T>> state

    Type getType() {
        type
    }

    int size() {
        0
    }

    Heap(Type type) {
        this.type = type
        state = []
    }

    @VisibleForTesting
    List<Comparable<T>> getState() {
        return new ArrayList<Comparable<T>>(state)
    }

    void add(Comparable<T> comparable) {
        state.add(comparable)
    }

    Comparable<T> remove() {
        null
    }

    void addAll(List<Comparable<T>> comparables) {
        comparables.each { this.add(it) }
    }

    static void sortAscending(List<Comparable<T>> items) {
        null
    }

    static void sortDescending(List<Comparable<T>> items) {
        null
    }

    static Heap createMinHeap() {
        new Heap(Type.MIN)
    }

    static Heap createMaxHeap() {
        new Heap(Type.MAX)
    }

}
