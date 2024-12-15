package y2024.day14

import util.grid.ScreenCoordinate
import util.grid.Vector

data class Robot(val position: ScreenCoordinate, val velocity: Vector) {


    fun teleport(times: Int, width: Int, height: Int) : Robot {
        val next = position.next(velocity.times(times))
        val shifted = position.copy(
            left = next.left.mod(width),
            top = next.top.mod(height)
        )

        return this.copy(position = shifted)
    }

}