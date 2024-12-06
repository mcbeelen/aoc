package util.grid

import java.util.*


open class GridWalker(private var currentPosition: ScreenCoordinate = ORIGIN,
                      private var direction: Direction = Direction.UP) {



    protected fun turnAndMove(turn: Turn, callback: (ScreenCoordinate) -> Unit = {}) {
        turn(turn)
        move(callback)
    }

    fun turn(turn: Turn) {
        direction = direction.turn(turn)
    }
    fun turn(turns: Iterable<Turn>) {
        turns.forEach {
            direction = direction.turn(it)
        }
    }
    fun move(callback: (ScreenCoordinate) -> Unit = {}) {
        currentPosition = currentPosition.next(direction)
        callback.invoke(currentPosition)
    }

    fun move(vector: Vector) {
        currentPosition = currentPosition.next(vector)
    }

    fun move(distance: Int) {
        currentPosition = currentPosition.next(direction, distance)
    }

    fun getCurrentPosition(): ScreenCoordinate = currentPosition

    fun getNextPosition(distance: Int = 1) = currentPosition.next(direction, distance)

    override fun toString() = "At ${currentPosition} facing ${direction}"

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (this === other) return true
        if (javaClass != other.javaClass) return false
        other as GridWalker
        return this.currentPosition == other.currentPosition && this.direction == other.direction
    }

    override fun hashCode() = Objects.hash(currentPosition, direction)
}
