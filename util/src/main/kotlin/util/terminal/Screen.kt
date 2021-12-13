package util.terminal

import util.grid.ScreenCoordinate

interface ScreenOutputStream<T> {
    fun paint(coordinate: ScreenCoordinate, value: T)
    fun stopScreen() {}

}

class NoOpScreenOutputScreen : ScreenOutputStream<Char> {
    override fun paint(coordinate: ScreenCoordinate, value: Char) {
        // Do nothing
    }

}

class InmemoryScreenOutputStream : ScreenOutputStream<Char> {

    private val buffer: MutableMap<ScreenCoordinate, Char> = HashMap()

    override fun paint(coordinate: ScreenCoordinate, value: Char) {
        buffer[coordinate] = value
    }

    fun calculateNumberOfTiles(char: Char): Int = buffer.values.count { it == char }

}