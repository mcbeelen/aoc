package y2022.day09

import util.grid.ScreenCoordinate
import java.util.HashSet

class TrailTracker(startingPosition: ScreenCoordinate) {

    private val visitedPositions: MutableSet<ScreenCoordinate> = HashSet()

    init {
        visitedPositions.add(startingPosition)
    }

    fun trackMovement(newPosition: ScreenCoordinate) {
        visitedPositions.add(newPosition)
    }

    fun numberOfVisitedPositions(): Int {
        return visitedPositions.size
    }

}