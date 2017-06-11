package com.lagostout.common

interface Heapable<T extends Comparable<?>> {
    T peek()
    T pop()
    void add(T comparable)
    void addAll(Iterable<T> comparables)
    void update(T oldValue, T newValue)
    void remove(T value)
    int getSize()
    List<T> asList()
    boolean isEmpty()
}
