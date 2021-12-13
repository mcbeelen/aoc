package y2021.day11

import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.puzzle.AdventOfCodePuzzle
import kotlin.Int.Companion.MIN_VALUE

class BioluminescentDumboOctopus(
    testInput: String = "",
    private val numberOfSteps : Int = 100) : AdventOfCodePuzzle(testInput) {

    private val initialEnergyLevelsOfOctopuses = parseToGrid(input) { it.toString().toInt() }


    override fun solvePartOne(): Int {

        var eneryLevels = initialEnergyLevelsOfOctopuses.toSortedMap().toMutableMap()
        var totalNumberOfFlashes = 0

        (1 .. numberOfSteps).forEach {
            val pair = performStep(eneryLevels)
            eneryLevels = pair.first
            totalNumberOfFlashes += pair.second
        }
        println(totalNumberOfFlashes)

        return totalNumberOfFlashes;
    }

    private fun performStep(eneryLevels: Map<ScreenCoordinate, Int>): Pair<MutableMap<ScreenCoordinate, Int>, Int> {
        val newEnergyLevelsOfOctopuses = eneryLevels
            .mapValues { it.value + 1 }
            .toMutableMap()

        var numberOfFlashesInCurrentStep = 0

        while (newEnergyLevelsOfOctopuses.any { it.value >= 10 }) {
            newEnergyLevelsOfOctopuses.filter { it.value >= 10 }
                .forEach { flashingOctopus ->
                    numberOfFlashesInCurrentStep++
                    newEnergyLevelsOfOctopuses[flashingOctopus.key] = MIN_VALUE
                    flashingOctopus.key.allAdjacentCoordinates()
                        .filter { newEnergyLevelsOfOctopuses.containsKey(it) }
                        .forEach { newEnergyLevelsOfOctopuses[it] = newEnergyLevelsOfOctopuses.getValue(it) + 1 }
                }
        }
        newEnergyLevelsOfOctopuses.filter { it.value < 0 }.forEach {
            newEnergyLevelsOfOctopuses[it.key] = 0
        }
        return Pair(newEnergyLevelsOfOctopuses.toSortedMap(), numberOfFlashesInCurrentStep)
    }

    override fun solvePartTwo(): Int {

        var eneryLevels = initialEnergyLevelsOfOctopuses.toSortedMap().toMutableMap()
        val totalNumberOfOctopuses = eneryLevels.size
        var stepCounter = 0
        while (true) {
            stepCounter ++
            val pair = performStep(eneryLevels)
            eneryLevels = pair.first
            if (pair.second == totalNumberOfOctopuses) {
                return stepCounter
            }
        }
    }
}

fun main() {
    BioluminescentDumboOctopus().getAnswers()
}