package y2024.day13

import util.puzzle.AdventOfCodePuzzle

class ClawContraption(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    override fun solvePartOne(): Long {
        val solvableMachines = input.windowed(3, 4)
            .map { extractMachineConfiguration(it) }
            .map { it.findCheapestWayToWin() }
            .filter { it.isPresent }
            .map { it.get() }
        return solvableMachines
            .map { it.calculateCost() }
            .sum()


    }

    private fun extractMachineConfiguration(blockOfLines: List<String>): ClawMachine {
        val buttonA = blockOfLines[0].toButton()
        val buttonB = blockOfLines[1].toButton()
        val price = blockOfLines[2].toLocation()

        return ClawMachine(buttonA, buttonB, price)

    }

    override fun solvePartTwo(): Long {
        val solvableMachines = input.windowed(3, 4)
            .map { extractMachineConfiguration(it) }
            .map { it.findSecondWayToWin() }
            .filter { it.isPresent }
            .map { it.get() }
        println("Found ${solvableMachines.size} machines")

        return solvableMachines
            .map { it.calculateCost() }
            .sum()
    }
}

fun String.toButton(): Button {
    val x = this.substringAfter("X+").substringBefore(",").toInt()
    val y = this.substringAfter("Y+").trim().toInt()
    return Button(x, y)
}
fun String.toLocation(): Location {
    val x = this.substringAfter("X=").substringBefore(",").toInt()
    val y = this.substringAfter("Y=").trim().toInt()
    return Location(x, y)
}

fun main() {
    ClawContraption().getAnswers()
}