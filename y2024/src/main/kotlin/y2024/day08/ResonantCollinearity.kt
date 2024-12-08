package y2024.day08

import util.collections.MultiMap
import util.grid.Grid
import util.grid.ScreenCoordinate
import util.grid.parseToGrid
import util.grid.plotGrid
import util.puzzle.AdventOfCodePuzzle
import java.util.*
import kotlin.jvm.optionals.getOrElse

class ResonantCollinearity(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val mapOfTheCity: Grid<Optional<Char>> by lazy {
        parseToGrid(input) {
            if (it == '.') Optional.empty() else Optional.of(it) }
    }


    override fun solvePartOne(): Int {
        val locationPerFrequency = MultiMap<Char, ScreenCoordinate>()
        mapOfTheCity.entries
            .filter { it.value.isPresent }
            .forEach {
                locationPerFrequency.put(it.value.get(), it.key)
            }

        val flatten = locationPerFrequency
            .getKeys()
            .map { determineLocationOfAntinodes(it, locationPerFrequency.get(it)) }
            .flatten()

        return flatten.toSet()
            .count()

    }

    private fun determineLocationOfAntinodes(frequency: Char, locationPerFrequency: Set<ScreenCoordinate>) : Set<ScreenCoordinate> {
        val foundAntinodes = mutableSetOf<ScreenCoordinate>()
        val locationsToCombine = locationPerFrequency.toList().sorted()
        for (i in 0 until locationsToCombine.size - 1) {
            for (j in i + 1 until locationsToCombine.size) {
                val firstLocation = locationsToCombine[i]
                val secondLocation = locationsToCombine[j]
                val vector = firstLocation.vectorTo(secondLocation)
                val firstPotentialAntiNode = secondLocation.next(vector)

                if (mapOfTheCity.containsKey(firstPotentialAntiNode)) {
                    foundAntinodes.add(firstPotentialAntiNode)
                }
                val reverse = vector.reverse()
                val secondPotentialAntiNode = firstLocation.next(reverse)
                if (mapOfTheCity.containsKey(secondPotentialAntiNode)) {
                    foundAntinodes.add(secondPotentialAntiNode)
                }
            }

        }

        return foundAntinodes

    }

    override fun solvePartTwo(): Int {
        val locationPerFrequency = MultiMap<Char, ScreenCoordinate>()
        val gridEntriesOfAntennas = mapOfTheCity.entries
            .filter { it.value.isPresent }
        gridEntriesOfAntennas
            .forEach {
                locationPerFrequency.put(it.value.get(), it.key)
            }

        val antiNodes = locationPerFrequency
            .getKeys()
            .map { determineLocationOfAntinodesWithHarmonics(it, locationPerFrequency.get(it)) }
            .flatten()

        val result = mapOfTheCity
        antiNodes.forEach {
            if (result[it]!!.isEmpty()) {
                 result.put(it, Optional.of('#'))
            }
        }

        plotGrid(result) {
            it.getOrElse { '.' }
        }

        val locationOfAntennas = gridEntriesOfAntennas.map { it.key }
        return antiNodes
            .plus(locationOfAntennas)
            .toSet()
            .count()
    }

    private fun determineLocationOfAntinodesWithHarmonics(frequency: Char, locationPerFrequency: Set<ScreenCoordinate>) : Set<ScreenCoordinate> {
        val foundAntinodes = mutableSetOf<ScreenCoordinate>()
        val locationsToCombine = locationPerFrequency.toList().sorted()
        for (i in 0 until locationsToCombine.size - 1) {
            for (j in i + 1 until locationsToCombine.size) {
                val firstLocation = locationsToCombine[i]
                val secondLocation = locationsToCombine[j]
                val vector = firstLocation.vectorTo(secondLocation)
                var firstPotentialAntiNode = secondLocation.next(vector)

                while (mapOfTheCity.containsKey(firstPotentialAntiNode)) {
                    foundAntinodes.add(firstPotentialAntiNode)
                    firstPotentialAntiNode = firstPotentialAntiNode.next(vector)
                }
                val reverse = vector.reverse()
                var secondPotentialAntiNode = firstLocation.next(reverse)
                while (mapOfTheCity.containsKey(secondPotentialAntiNode)) {
                    foundAntinodes.add(secondPotentialAntiNode)
                    secondPotentialAntiNode = secondPotentialAntiNode.next(reverse)
                }
            }

        }

        println("Frequence '${frequency}' resonates at ${foundAntinodes.sorted()}")
        return foundAntinodes

    }
}

fun main() {
    ResonantCollinearity().getAnswers()
}