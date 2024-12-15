package y2024.day14

import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.Vector
import util.grid.plotGrid
import util.puzzle.AdventOfCodePuzzle
import y2024.day14.Quadrant.*
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

fun String.toRobot() : Robot {
    //p=0,4 v=3,-3
    val p = this.substringAfter("p=").substringBefore(" v")
    val x = p.substringBefore(",").toInt()
    val y = p.substringAfter(",").toInt()
    val position = ScreenCoordinate(x, y)

    val v = this.substringAfter("v=")
    val vx = v.substringBefore(",").toInt()
    val vy = v.substringAfter(",").toInt()
    val vector = Vector(vx, vy)

    return Robot(position, vector)
}

enum class Quadrant {
    TOP_LEFT, 
    TOP_RIGHT,
    BOTTOM_LEFT, 
    BOTTOM_RIGHT
}

class RestroomRedoubt(testInput: String = "", val width: Int = 101, val height: Int = 103) : AdventOfCodePuzzle(testInput) {

    fun determineQuadrant(position: ScreenCoordinate) : Optional<Quadrant> {
        val verticalMiddle = (width - 1) / 2
        val horizonalMiddle = (height - 1) / 2
        if (position.left < verticalMiddle) {
            // LEFT
            if (position.top < horizonalMiddle) {
                return of(TOP_LEFT)
            }
            if (position.top > horizonalMiddle) {
                return of(BOTTOM_LEFT)
            }
        } 
        if (position.left > verticalMiddle) {
            //RIGHT
            if (position.top < horizonalMiddle) {
                return of(TOP_RIGHT)
            }
            if (position.top > horizonalMiddle) {
                return of(BOTTOM_RIGHT)
            }
        }
        return empty()
    }

    override fun solvePartOne(): Int {
        val teleportedRobots = input
            .map { it.toRobot() }
            .map { it.teleport(100, width, height) }
            .sortedBy { it.position }

        return calculateSafetyFactor(teleportedRobots)
    }

    private fun calculateSafetyFactor(teleportedRobots: List<Robot>): Int {
        val robotsPerQuadrant = teleportedRobots
            .map { determineQuadrant(it.position) }
            .filter { it.isPresent }
            .map { it.get() }
            .groupingBy { it }
            .eachCount()

        val safetyFactor = robotsPerQuadrant.values.fold(1) { result, next -> result * next }
        return safetyFactor
    }

    override fun solvePartTwo(): Long {
        /*
        var robots = input.map { it.toRobot() }
        var safestConfig = Pair(0L, Int.MAX_VALUE)
        var counter = 0L
        while (robots.map { it.position }.toSet().size < 500) {
            robots = robots.map { it.teleport(1, width, height) }
            counter++
            val safetyFactor = calculateSafetyFactor(robots)
            if (safetyFactor < safestConfig.second) {
                println("New safely found after ${counter} @ ${safetyFactor} with ${robots.map { it.position }.toSet().size}")
                safestConfig = Pair(counter, safetyFactor)
            }
        }
*/

        var robots = input.map { it.toRobot() }
        robots = robots.map { it.teleport(7038, width, height) }

        val floor = Grid<Robot>()
        robots.forEach { floor.put(it.position, it) }
        plotGrid(floor) { 'X' }

        println()
        println()

        return Long.MIN_VALUE

    }
}

fun main() {
    RestroomRedoubt().getAnswers()
}