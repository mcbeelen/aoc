package y2021.day05

import com.google.common.collect.HashBasedTable
import util.grid.Line
import util.grid.parseXcommaY
import util.puzzle.AdventOfCodePuzzle
import java.util.concurrent.atomic.AtomicInteger

typealias OceanFloor = HashBasedTable<Int, Int, AtomicInteger>
typealias VentLine = Line

class HydrothermalVenture(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Int {
        val ventLines =
            input.map { it.toVentLine() }
                .filter {it.start.left == it.endInclusive.left ||
                         it.start.top == it.endInclusive.top}

        return countPointsWhereLinesOverlap(ventLines)
    }

    override fun solvePartTwo(): Int {
        val ventLines =
            input.map { it.toVentLine() }

        return countPointsWhereLinesOverlap(ventLines)
    }


    private fun countPointsWhereLinesOverlap(ventLines: List<VentLine>): Int {
        val oceanFloor = OceanFloor.create<Int, Int, AtomicInteger>()

        ventLines.forEach { ventLine ->
            ventLine.forEach {
                if (oceanFloor.contains(it.top, it.left)) {
                    oceanFloor.get(it.top, it.left)!!.incrementAndGet()
                } else {
                    oceanFloor.put(it.top, it.left, AtomicInteger(1))
                }
            }
        }
        return oceanFloor.values().count { it.get() > 1 }
    }
}

private fun String.toVentLine() = VentLine(
    this.substringBefore(" -> ").toScreenCoordinate(),
    this.substringAfter(" -> ").toScreenCoordinate()
    )

private fun String.toScreenCoordinate() = parseXcommaY(this)


fun main() {
    HydrothermalVenture().getAnswers()
}

