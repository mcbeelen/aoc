package y2021.day07

import util.input.splitInputIntoInts
import util.puzzle.AdventOfCodePuzzle
import java.lang.Integer.MIN_VALUE
import java.lang.Integer.MAX_VALUE
import kotlin.math.abs

class TreacheryOfWhales(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val positionsOfCrab = splitInputIntoInts(input[0])
        .groupingBy { it }
        .eachCount()
        .toSortedMap()

    override fun solvePartOne(): Int {
        val bestSoFar = findLeastFuelConsumingPosition {
            positionsOfCrab, candidatePosition -> calculateTotalFuelCostAtConstantRate(positionsOfCrab, candidatePosition)
        }
        return bestSoFar.second
    }

    override fun solvePartTwo(): Int {
        val bestSoFar = findLeastFuelConsumingPosition {
                positionsOfCrab, candidatePosition -> calculateTotalFuelCostAtIncreasingRate(positionsOfCrab, candidatePosition)
        }
        return bestSoFar.second
    }


    private fun findLeastFuelConsumingPosition(calculator: (positionsOfCrab: Map<Int, Int>, candidate: Int) -> Int) : LeastFuelConsumingPosition {
        var bestSoFar = LeastFuelConsumingPosition(MIN_VALUE, MAX_VALUE)
        (positionsOfCrab.firstKey() .. positionsOfCrab.lastKey()).forEach { candidatePosition ->
            val totalFuelCosts = calculator.invoke(positionsOfCrab, candidatePosition)
            if (totalFuelCosts < bestSoFar.second) {
                bestSoFar = Pair(candidatePosition, totalFuelCosts)
            }
        }
        return bestSoFar
    }

    private fun calculateTotalFuelCostAtConstantRate(positionsOfCrab: Map<Int, Int>, candidate: Int): Int {
        return positionsOfCrab.entries.fold(0) {
                acc, entry -> acc + abs(entry.key - candidate) * entry.value
        }
    }

    private fun calculateTotalFuelCostAtIncreasingRate(positionsOfCrab: Map<Int, Int>, candidate: Int): Int {
        return positionsOfCrab.entries.fold(0) {
                acc, entry ->
            acc + calculateFuelCostAtIncreasingRate(entry.key, candidate) * entry.value
        }
    }

    private fun calculateFuelCostAtIncreasingRate(origin: Int, destination: Int): Int {
        val distanceToCandidatePosition = abs(origin - destination)
        return (distanceToCandidatePosition * (distanceToCandidatePosition + 1)) / 2
    }

}

fun main() {
    TreacheryOfWhales().getAnswers()
}

typealias LeastFuelConsumingPosition = Pair<Int, Int>