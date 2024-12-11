package y2024.day11

import util.puzzle.AdventOfCodePuzzle

fun parseLineOfStones(lineOfStones: String): List<Long> {
    return lineOfStones.split(" ").map { it.toLong() }
}

class PlutonianPebbles(testInput: String = "") : AdventOfCodePuzzle(testInput) {


    override fun solvePartOne(): Int {
        val stones = parseLineOfStones(input[0])
        val transformedStones = blink(stones, 25)
        return transformedStones.count()
    }

    override fun getAnswerForPartTwo(): String {
        val stones = parseLineOfStones(input[0])
        val frequencyPerValue = stones
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }

        var updatedLineOfStones = frequencyPerValue
        for (i in 0 until 75) {
            updatedLineOfStones = blinkWithFrequencies(updatedLineOfStones)
        }

        return updatedLineOfStones
            .map { it.value }
            .sum()
            .toString()
    }

    private fun blinkWithFrequencies(frequencyPerValue: Map<Long, Long>): Map<Long, Long> {
        val groupBy = frequencyPerValue.keys
            .map { transform(it) }
            .map { transformed -> transformed.second.map { Pair(transformed.first, it) } }
            .flatten()
            .map { Pair(it.second, frequencyPerValue[it.first]) }
            .groupingBy { it.first }
            .fold(0L) { acc, next -> acc + next.second!! }
        return groupBy
    }
}

fun blink(listOfStones : Iterable<Long>, times: Int = 1) : Iterable<Long> {
    val transformedStones = blinkOnce(listOfStones)
    if (times == 1) {
        return transformedStones
    }
    return blink(transformedStones, times - 1)

}

fun blinkOnce(listOfStones: Iterable<Long>): Iterable<Long> {
    return listOfStones
        .map { transform(it) }
        .map { it.second }
        .flatten()
}


fun transform(stone: Long) : Pair<Long, List<Long>> {
    val result = when {
        stone == 0L -> listOf(1L)
        hasEvenNumberOfDigits(stone) -> splitStone(stone)
        else -> listOf(stone * 2024)
    }
    return Pair(stone, result)
}

fun splitStone(stone: Long): List<Long> {
    val digits = stone.toString()
    val numberOfDigits = digits.length
    val first = digits.take(numberOfDigits / 2).toLong()
    val second = digits.takeLast(numberOfDigits / 2).toLong()
    return listOf(first, second)

}

fun hasEvenNumberOfDigits(stone : Long) = stone.toString().length.mod(2) == 0

fun main() {
    PlutonianPebbles().getAnswers()
}