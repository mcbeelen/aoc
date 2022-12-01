package y2021.day17

import util.puzzle.AdventOfCodePuzzle
import java.lang.Math.abs
import kotlin.math.floor
import kotlin.math.sqrt

class TrickShot(testInput: String = "") : AdventOfCodePuzzle(testInput) {
    // Example: target area: x=20..30, y=-10..-5
    // Actual:  target area: x=32..65, y=-225..-177
    val minX = input[0].substringAfter("x=").substringBefore("..").toInt()
    val maxX = input[0].substringAfter("..").substringBefore(",").toInt()

    val maxY = input[0].substringAfter("y=").substringAfter("..").trim().toInt()
    val minY = input[0].substringAfter("y=").substringBefore("..").toInt()

    override fun solvePartOne(): Int {
        return (abs(minY) * (abs(minY) - 1)) / 2
    }

    override fun solvePartTwo(): Int {

        val minVx = calculateMinimumVelocityToReach(minX) - 1
        val maxVx = maxX
        val minVy = minY
        val maxVy = abs(minY)

        val candidates = (minVx .. maxVx).flatMap { x ->
            (minVy..maxVy).map { y -> Pair(x, y ) }
        }

        return candidates.count { hitsTargetArea(it) }
    }

    fun hitsTargetArea(candidate: Pair<Int, Int>) : Boolean {
        val (cx, cy) = candidate
        val minT = 1
        val maxT = 2 * (abs(minY)) + 1
        return (minT .. maxT)
            .map { Triple(cx, cy, it) }
            .any { hitsTargetArea(it) }
    }

    fun hitsTargetArea(candidate: Triple<Int, Int, Int>) : Boolean {
        val (cx, cy, ct) = candidate
        val xt = xPosAfterT(cx, ct)
        val yt = yPosAfterT(cy, ct)
        return xt in minX .. maxX &&
                yt in minY .. maxY
    }
}

internal fun xPosAfterT(cx: Int, ct: Int) = if(ct >= cx) {
    ((cx + 1 ) * cx) / 2
} else {
    (cx * ct) - ((ct - 1) * ct) / 2
}

internal fun yPosAfterT(cy: Int, ct: Int) = (ct  * cy) - ((ct - 1) * ct) / 2

fun calculateMinimumVelocityToReach(distance: Int): Int {
    val candidate = floor(sqrt((distance * 2).toDouble())).toInt()
    val reachedDistance = ((candidate * (candidate + 1)) / 2)
    if (reachedDistance == distance) {
        return candidate
    }
    return candidate + 1
}

fun main() {
    TrickShot().getAnswers()
}