package util.collections

class Queue<E> : Collection<E> {

    private val items: MutableList<E> = emptyList<E>().toMutableList()

    override fun toString() = items.toString()

    fun enqueue(element: E) {
        items.add(element)
    }

    fun addAll(elements: Iterable<E>) {
        elements.forEach { this.enqueue(it) }
    }

    fun dequeue(): E {
        if (this.isEmpty()) {
            throw IllegalStateException("Did not check isEmpty() before calling dequeue")
        }
        return items.removeAt(0)
    }

    fun dequeue(n: Int): List<E> {
        if (this.isEmpty()) {
            throw IllegalStateException("Did not check isEmpty() before calling dequeue")
        }
        return (0 until  n).map { items.removeAt(0) }
    }

    fun peek(): E? {
        if (this.isEmpty()) {
            return null
        } else {
            return items.get(0)
        }
    }


    override val size = items.size

    override fun contains(element: E) = items.contains(element)

    override fun containsAll(elements: Collection<E>) = items.containsAll(elements)

    override fun isEmpty() = items.isEmpty()

    override fun iterator() = items.iterator()
}
