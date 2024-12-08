package y2024.day07

import util.puzzle.AdventOfCodePuzzle
import y2024.day07.Operation.ADD
import y2024.day07.Operation.CONCATENATE
import y2024.day07.Operation.MULTIPLY

enum class Operation {
    ADD,
    MULTIPLY,
    CONCATENATE;

    fun execute(first: Long, second: Long): Long {
        return when (this) {
            ADD -> first + second
            MULTIPLY -> first * second
            CONCATENATE -> (first.toString() + second.toString()).toLong()
        }
    }

}


fun equationCanBeSolved(
    result: Long,
    numbers: List<Long>,
    allowedOperations: List<Operation> = listOf(MULTIPLY, ADD)
): Boolean {

    val first = numbers.first()
    if (numbers.size == 1) {
        return result == first
    }

    if (first > result) {
        return false
    }
    val second = numbers[1]
    if (numbers.size == 2) {
        val finalOperation = allowedOperations.find {
            it.execute(first, second) == result
        }
        return finalOperation != null
    }

    return allowedOperations.any {
        val calculatedValue = it.execute(first, second)
        val updatedValues = listOf(calculatedValue).plus(numbers.drop(2))
        equationCanBeSolved(result, updatedValues, allowedOperations)
    }


}

class BridgeRepair(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun getAnswerForPartOne(): String {

        val allowOperations = listOf(MULTIPLY, ADD)

        return input
            .map { splitIntoResultAndNumbers(it) }
            .filter { equationCanBeSolved(it.first, it.second, allowOperations) }
            .map {
                println("Solvable: ${it}")
                it.first

            }
            .sum()
            .toString()
    }

    private fun splitIntoResultAndNumbers(equation: String): Pair<Long, List<Long>> {
        val result = equation.substringBefore(":").toLong()
        val numbers = equation.substringAfter(": ")
            .trim()
            .split(" ")
            .map { it.toLong() }
        return Pair(result, numbers)

    }

    override fun getAnswerForPartTwo(): String {
        val allowOperations = listOf(MULTIPLY, CONCATENATE, ADD)

        return input
            .map { splitIntoResultAndNumbers(it) }
            .filter { equationCanBeSolved(it.first, it.second, allowOperations) }
            .map { it.first }
            .sum()
            .toString()
    }
}

fun main() {
    BridgeRepair().getAnswers()
}