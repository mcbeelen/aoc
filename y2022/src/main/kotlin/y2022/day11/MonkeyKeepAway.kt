package y2022.day11

import util.puzzle.AdventOfCodePuzzle
import java.util.TreeMap

typealias MonkeyIndex = Int
typealias WorryLevel = Long

class MonkeyKeepAway(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    val monkeys = TreeMap<MonkeyIndex, Monkey>()


    override fun getAnswerForPartOne(): String {
        val inputReader = input.asSequence()
        (0 .. input.size / 7).forEach {

            val blockPerMonkey = inputReader
                .drop(it * 7)
                .take(7).toList()
            val monkey = parseBlock(blockPerMonkey, true)
            monkeys[monkey.id] = monkey
        }

        (0 until 20).forEach { _ ->
            monkeys.keys.forEach { id ->
                val monkey = monkeys.getValue(id)
                while (monkey.hasItems()) {
                    val thrownItem = monkey.getThrowItem()
                    monkeys.getValue(thrownItem.destination).catchItem(thrownItem.worryLevel)
                }
            }
        }

        val sortedByDescending = monkeys.values.sortedByDescending { it.numberOfInspections }
        println(sortedByDescending)
        return "${sortedByDescending[0].numberOfInspections * sortedByDescending[1].numberOfInspections}"
    }


    override fun getAnswerForPartTwo(): String {

        val inputReader = input.asSequence()
        (0 .. input.size / 7).forEach {

            val blockPerMonkey = inputReader
                .drop(it * 7)
                .take(7).toList()
            val monkey = parseBlock(blockPerMonkey, false)
            monkeys[monkey.id] = monkey
        }

        val gcd = monkeys.values.map { it.divisible }
            .reduce { acc, i -> acc * i }

        (0 until 10_000).forEach { round ->
            monkeys.keys.forEach { id ->
                val monkey = monkeys.getValue(id)
                while (monkey.hasItems()) {
                    val thrownItem = monkey.getThrowItem()

                    val newWorryLevel = thrownItem.worryLevel % gcd


                    monkeys.getValue(thrownItem.destination).catchItem(newWorryLevel)
                }
            }
        }

        val sortedByDescending = monkeys.values.sortedByDescending { it.numberOfInspections }
        println(sortedByDescending)
        return "${sortedByDescending[0].numberOfInspections * sortedByDescending[1].numberOfInspections}"
    }



    private fun parseBlock(blockPerMonkey: List<String>, applyRelief: Boolean): Monkey {
        val id: MonkeyIndex = blockPerMonkey[0].substringAfter(" ").substringBefore(":").toInt()


        val startingItems = blockPerMonkey[1].substringAfter(": ").split(", ").map { it.toLong() }
        val operationLine = blockPerMonkey[2]
        val operation = Operation.by(operationLine.substringAfter("old ").substringBefore(" "))
        var value: Long? = null
        val valueText = operationLine.substringAfterLast(" ")
        if (valueText != "old") {
            value = valueText.toLong()
        }

        val divisible = blockPerMonkey[3].substringAfterLast(" ").toInt()
        val first = blockPerMonkey[4].substringAfterLast(" ").toInt()
        val second = blockPerMonkey[5].substringAfterLast(" ").toInt()
        return Monkey(id, startingItems, operation, value, divisible, Pair(first, second), applyRelief)

    }

}

data class ThrownItem(val worryLevel: WorryLevel, val destination: MonkeyIndex)


fun main() {
    MonkeyKeepAway().getAnswers()
}