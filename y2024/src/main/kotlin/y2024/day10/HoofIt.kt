package y2024.day10

import util.collections.MultiMap
import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.puzzle.AdventOfCodePuzzle

class HoofIt(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val topographicMap: Grid<Int> by lazy {
        parseToGrid(input) { parseToHeight(it)}
    }

    private fun parseToHeight(it: Char)= when (it) {
            '.' -> Int.MIN_VALUE
            else -> it.digitToInt()
        }


    private val elevationMap: MultiMap<Int, ScreenCoordinate> by lazy { mapToElevation()  }

    private fun mapToElevation() : MultiMap<Int, ScreenCoordinate> {
        val mappedPositions = MultiMap<Int, ScreenCoordinate>()

        topographicMap.forEach {
            mappedPositions.put(it.value, it.key)
        }
        return mappedPositions
    }

    override fun solvePartOne(): Int {

        val scoredMap = elevationMap
            .get(0)
            .map { findPeaks(it) }


        return scoredMap
            .map { it.second.size }
            .sum()

    }

    private val scoredPositions = MultiMap<ScreenCoordinate, ScreenCoordinate>()

    private fun findPeaks(position: ScreenCoordinate) : Pair<ScreenCoordinate, Set<ScreenCoordinate>> {

        val heightOfCurrentPosition = topographicMap.getValue(position)
        if (scoredPositions.containsKey(position)) {
            return Pair(position, scoredPositions.get(position))
        } else {
            val peaksOfTailhead = if (heightOfCurrentPosition == 9) {
                setOf(position)
            } else {
                position.allNeighbors()
                    .filter { topographicMap.containsKey(it) }
                    .filter { topographicMap.getValue(it) == heightOfCurrentPosition + 1 }
                    .map { findPeaks(it).second }
                    .flatten()
            }
            scoredPositions.putAll(position, peaksOfTailhead)

            return Pair(position, peaksOfTailhead.toSet())
        }
    }

}
