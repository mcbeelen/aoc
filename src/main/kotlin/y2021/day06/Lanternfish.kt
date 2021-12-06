package y2021.day06

import arrow.core.extensions.list.foldable.fold
import util.input.splitInputIntoInts
import util.puzzle.AdventOfCodePuzzle

class Lanternfish(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    private val quantityOfFishInSchoolByInternalTimer = splitInputIntoInts(input[0])
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }

    private val evolutionOfSixteenDays = (0 .. 8 ).map {
        val school = listOf(it)
        val ticked = tick(school, 16)
        val countPerTimer = ticked.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        return@map Pair(it, countPerTimer)
    }.toMap()
    private fun evolve(it: Int) = if (it == 0 ) listOf(6, 8) else listOf(it - 1)
    private fun tick(schoolOfLanternfish: List<Int>, times: Int): List<Int> {
        var evolutions = schoolOfLanternfish
        (0 until times).forEach { count ->
            evolutions = evolutions.flatMap { evolve(it) }
        }
        return evolutions
    }

    override fun getAnswerForPartOne(): String {
        var evolutions = SchoolOfLanternfish(quantityOfFishInSchoolByInternalTimer)
        (0 until 5).forEach {  _ ->
            val generatedPairs = evolutions.quantityPerInternalTimer.flatMap<Int, Long, Pair<Int, Long>> { quantityPerTimer ->
                val internalTimer = quantityPerTimer.key
                val initialQuantity = quantityPerTimer.value
                val evolutionForCurrentTimer = evolutionOfSixteenDays.getValue(internalTimer)
                evolutionForCurrentTimer.map { Pair(it.key, initialQuantity * it.value) }
            }

             evolutions = generatedPairs.fold(SchoolOfLanternfish()) { school, pair ->
                school.withAdditional(pair)
            }
        }

        return evolutions.quantityPerInternalTimer.map { it.value }.sum().toString()
    }




    override fun getAnswerForPartTwo(): String {
        var evolutions = SchoolOfLanternfish(quantityOfFishInSchoolByInternalTimer)
        (0 until 16).forEach {  _ ->
            val generatedPairs = evolutions.quantityPerInternalTimer.flatMap<Int, Long, Pair<Int, Long>> { quantityPerTimer ->
                val internalTimer = quantityPerTimer.key
                val initialQuantity = quantityPerTimer.value
                val evolutionForCurrentTimer = evolutionOfSixteenDays.getValue(internalTimer)
                evolutionForCurrentTimer.map { Pair(it.key, initialQuantity * it.value) }
            }

            evolutions = generatedPairs.fold(SchoolOfLanternfish()) { school, pair ->
                school.withAdditional(pair)
            }
        }

        return evolutions.quantityPerInternalTimer.map { it.value }.sum().toString()
    }

}


data class SchoolOfLanternfish(val quantityPerInternalTimer : Map<Int, Long> = emptyMap()) {
    fun withAdditional(pair: Pair<Int, Long>) : SchoolOfLanternfish{
        val originalQuantity = quantityPerInternalTimer.getOrDefault(pair.first, 0)
        val newQuantity = originalQuantity + pair.second
        val newMap = quantityPerInternalTimer.toMutableMap()
        newMap.put(pair.first, newQuantity)
        return SchoolOfLanternfish(newMap)
    }
}

fun main() {
    Lanternfish().getAnswers()
}