package y2024.day12

import util.collections.MultiMap
import util.collections.Stack
import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.puzzle.AdventOfCodePuzzle



class GardenGroups(testInput: String = "") : AdventOfCodePuzzle(testInput) {

     private val garden: Grid<Char> by lazy {
         parseToGrid(input) { it }
     }

    override fun solvePartOne(): Number {
        val foundRegions = discoverRegionsInTheGarden()

        return foundRegions
            .map { it.calculateArea() * it.calculatePerimeter() }
            .sum()

    }

    private fun discoverRegionsInTheGarden() : List<Region> {
        val foundRegions = mutableListOf<Region>()

        val processedPlots = mutableSetOf<ScreenCoordinate>()
        garden.entries.forEach { plot ->
            val currentPlot = plot.key
            if (!processedPlots.contains(currentPlot)) {
                val newRegion = Region(plot.value)
                newRegion.addPlot(currentPlot)
                foundRegions.add(newRegion)
                processedPlots.add(currentPlot)

                val plotsToScan = Stack<ScreenCoordinate>()
                plotsToScan.push(currentPlot)

                while (!plotsToScan.isEmpty()) {

                    val plotToScan = plotsToScan.pop()
                    val neighboursInSameRegion = plotToScan.allNeighbors()
                        .filter { garden.containsKey(it) }
                        .filter { garden.getValue(it) == newRegion.identifier }
                        .filter { !processedPlots.contains(it) }
                    newRegion.addPlots(neighboursInSameRegion)
                    processedPlots.addAll(neighboursInSameRegion)

                    plotsToScan.pushAll(neighboursInSameRegion)
                }
            }

        }
        return foundRegions
    }

    override fun solvePartTwo(): Long {
        val foundRegions = discoverRegionsInTheGarden()

        return foundRegions
            .map { it.calculateArea() * it.countSides() }
            .sum()
    }
}

fun main() {
    GardenGroups().getAnswers()
}