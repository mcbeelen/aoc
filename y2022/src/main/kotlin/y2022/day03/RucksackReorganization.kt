package y2022.day03

import util.puzzle.AdventOfCodePuzzle
import java.util.StringJoiner

class RucksackReorganization(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne() = input.sumOf { calculatePriority(it) }



    override fun solvePartTwo(): Int {
        val groups: List<Triple<String, String, String>> = splitElvesIntoGroups()
        return groups.sumOf { getPriorityOfBatch(it) }
    }

    private fun getPriorityOfBatch(group: Triple<String, String, String>): Int {
        val batch = group.first.first { group.second.contains(it) && group.third.contains(it) }
        return getPriorityForItem(batch)
    }

    private fun splitElvesIntoGroups(): List<Triple<String, String, String>> {
            val intRange = 0 until (input.size / 3)
            return intRange.map {
                val offset = it * 3
                Triple(input.get(offset), input.get(offset + 1), input.get(offset + 2))
            }.toList()
        }
    }

fun calculatePriority(it: String): Int {
    val first = findItemInBothCompartments(it)
    return getPriorityForItem(first)
}

private fun findItemInBothCompartments(it: String): Char {
    val itemsInFirstCompartment = it.substring(0, it.length / 2)
    val itemsInSecondCompartment = it.substring(it.length / 2)

    return itemsInFirstCompartment.first { itemsInSecondCompartment.contains(it) }
}

private fun getPriorityForItem(first: Char): Int {
    val priority = if (first.isLowerCase()) {
        first - 'a' + 1
    } else {
        first - 'A' + 27
    }
    return priority
}


fun main() {
    RucksackReorganization().getAnswers()
}