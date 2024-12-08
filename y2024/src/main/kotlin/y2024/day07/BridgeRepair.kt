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

    fun perform(result: Long, numbers: List<Long>): Pair<Long, List<Long>> {
        val last = numbers.last()
        val remainingValues = numbers.dropLast(1)
        when (this) {
            ADD -> return Pair(result - last, remainingValues)
            MULTIPLY -> if (result.mod(last) == 0L) {
                return Pair(result / last, remainingValues)
            } else {
                return Pair(Long.MIN_VALUE, emptyList())
            }
            CONCATENATE -> {
                val concatenatedValues = CONCATENATE.execute(remainingValues.last(), last)
                return Pair(result, remainingValues.drop(1).plus(concatenatedValues))
            }
        }
    }

}


fun equationCanBeSolved(
    result: Long,
    values: List<Long>,
    allowedOperations: Set<Operation> = setOf(ADD, MULTIPLY)
): Boolean {

    println("Checking ${values} to yield ${result}")

    if (result <= 0) return false

    val first = values.first()
    if (values.size == 1) {
        return result == first
    }

    val second = values[1]
    if (values.size == 2) {
        return allowedOperations.any {
            it.execute(first, second) == result
        }
    }

    return allowedOperations.any {
        val appliedOperations = it.perform(result, values)
        equationCanBeSolved(appliedOperations.first, appliedOperations.second, allowedOperations)
    }


}

class BridgeRepair(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun getAnswerForPartOne(): String {

        val allowOperations = setOf(Operation.ADD, MULTIPLY)

        return input
            .map { splitIntoResultAndValues(it) }
            .filter { equationCanBeSolved(it.first, it.second, allowOperations) }
            .map { it.first }
            .sum()
            .toString()
    }

    private fun splitIntoResultAndValues(equation: String): Pair<Long, List<Long>> {
        val result = equation.substringBefore(":").toLong()
        val values = equation.substringAfter(": ")
            .trim()
            .split(" ")
            .map { it.toLong() }
        return Pair(result, values)

    }

    override fun getAnswerForPartTwo(): String {
        val allowOperations = setOf(ADD, MULTIPLY, CONCATENATE)

        return input
            .map { splitIntoResultAndValues(it) }
            .filter { equationCanBeSolved(it.first, it.second, allowOperations) }
            .map { it.first }
            .sum()
            .toString()
    }
}

fun main() {
    BridgeRepair().getAnswers()
}