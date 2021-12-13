package util.grid

data class Line(
    override val start: ScreenCoordinate,
    override val endInclusive:ScreenCoordinate) : ClosedRange<ScreenCoordinate>,
                                                  Iterable<ScreenCoordinate> {

    override fun iterator() = ScreenCoordinateIterator(start, endInclusive)
}



class ScreenCoordinateIterator(
    start: ScreenCoordinate,
    private val endInclusive: ScreenCoordinate) : Iterator<ScreenCoordinate> {

    private val progression = simplify(Vector(endInclusive.left - start.left, endInclusive.top - start.top))

    private var hasNext: Boolean = true
    private var next : ScreenCoordinate = if (hasNext) start else endInclusive

    override fun hasNext(): Boolean = hasNext

    override fun next(): ScreenCoordinate {
        val value = next
        if (value == endInclusive) {
            if (!hasNext) throw kotlin.NoSuchElementException()
            hasNext = false
        } else {
            next = next.next(progression)
        }
        return value
    }
}
