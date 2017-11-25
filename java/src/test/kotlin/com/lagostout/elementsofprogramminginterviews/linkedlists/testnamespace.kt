package com.lagostout.elementsofprogramminginterviews.linkedlists

data class RawLinkedListNode<out T>(val value: T, val nextIndex: Int? = null)

fun <T> toLinkedList(vararg values: T): LinkedListNode<T> {
    return toLinkedList(values.asList())
}

fun <T> toLinkedList(values: List<T>): LinkedListNode<T> {
    return values.map {
        LinkedListNode(it)
    }.apply {
        forEachIndexed { index, listNode ->
            listNode.next = if (index < lastIndex) get(index + 1)
            else null
        }
    }.first()
}

fun <T> toLinkedListWithExplicitLinkage(
        values: List<RawLinkedListNode<T>>): LinkedListNode<T> {
    return values.map {
        LinkedListNode(it.value)
    }.apply {
        forEachIndexed { index, listNode ->
            listNode.next = (values[index].nextIndex ?: index + 1).let {
                if (it >= size) null else get(it)
            }
        }
    }.first()
}
