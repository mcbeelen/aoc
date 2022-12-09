package y2022.day09

import com.google.common.eventbus.EventBus
import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.parseDirection
import util.grid.plotGrid
import util.puzzle.AdventOfCodePuzzle
import y2022.day09.KnotIdentifier.Head
import y2022.day09.KnotIdentifier.Tail

class RopeBridge(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val eventBus = EventBus()

    override fun solvePartOne(): Int {

        val tail = Knot(eventBus, Tail, Head)
        eventBus.register(tail)
        processInstructions()
        return tail.numberOfVisitedPositions()
    }


    private var knotMapping = HashMap<KnotIdentifier, Knot>()

    override fun solvePartTwo(): Int {
        KnotIdentifier.values().asList().windowed(2)
            .forEach {
                val knot = Knot(eventBus, it[1], it[0])
                eventBus.register(knot)
                knotMapping[it[1]] = knot
            }

        processInstructions()

        return knotMapping[Tail]!!.numberOfVisitedPositions()
    }

    private fun processInstructions() {
        input.forEach {

            // Uncomment for visual inspection
            // printTrailInGrid()
            val direction = parseDirection(it[0])
            val quantity = it.substringAfter(" ").toInt()
            (0 until quantity).forEach {
                eventBus.post(KnotMovedEvent(Head, direction.vector))
            }

        }
    }

    private fun printTrailInGrid() {
        val grid = Grid<KnotIdentifier>(5)
        KnotIdentifier.values().asList().reversed().forEach { id ->
            knotMapping[id]?.let {
                grid.put(it.position, id)
            }
        }
        plotGrid(grid, '.') { it.marker }
    }
}

fun main() {
    RopeBridge().getAnswers()
}