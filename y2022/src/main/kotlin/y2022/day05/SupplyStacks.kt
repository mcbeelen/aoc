package y2022.day05


import util.puzzle.AdventOfCodePuzzle
import java.lang.Integer.parseInt
import java.util.LinkedList
import kotlin.collections.ArrayDeque

import kotlin.collections.HashMap

typealias Crate = Char

class SupplyStacks(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    val stacks = HashMap<Int, ArrayDeque<Crate>>()
    val moveInstructions = LinkedList<MoveInstruction>()
    var numberOfStacks = 0

    init {
        parseInputIntoStacksAndMoveInstructions()
    }

    override fun getAnswerForPartOne(): String {
        parseInputIntoStacksAndMoveInstructions()
        moveInstructions.forEach {moveCratesOneByOne(it) }
        return readMarkingOfTopCrates()
    }
    
    override fun getAnswerForPartTwo(): String {
        parseInputIntoStacksAndMoveInstructions()
        moveInstructions.forEach {moveCratesPerStack(it) }
        return readMarkingOfTopCrates()
    }

    private fun moveCratesOneByOne(moveInstruction: MoveInstruction) {
        (0 until moveInstruction.quantity).forEach { _ ->
            moveSingleCrate(moveInstruction)
        }
    }

    private fun moveSingleCrate(moveInstruction: MoveInstruction) {
        val crate = stacks.getValue(moveInstruction.fromIndex).removeLast()
        stacks.getValue(moveInstruction.toIndex).add(crate)
    }


    private fun moveCratesPerStack(it: MoveInstruction) {
        val intermediateStack = ArrayDeque<Crate>()
        (0 until it.quantity).forEach { _ ->
            val crate = stacks.getValue(it.fromIndex).removeLast()
            intermediateStack.add(crate)
        }
        (0 until it.quantity).forEach { _ ->
            val crate = intermediateStack.removeLast()
            stacks.getValue(it.toIndex).add(crate)
        }
    }

    private fun readMarkingOfTopCrates() = (1..numberOfStacks).joinToString("") { index ->
        stacks.getValue(index).last().toString()
    }

    private fun parseInputIntoStacksAndMoveInstructions() {
        parseInputIntoStartingStacks()
        parseInputIntoMoveInstructions()
    }

    private fun parseInputIntoStartingStacks() {

        stacks.clear()

        val split = input.indexOfFirst { it.isEmpty() }
        val stackDefinitions = input.subList(0, split).reversed()
        numberOfStacks = (stackDefinitions.get(0).length + 1) / 4
        (1 .. numberOfStacks).forEach {index ->
            stacks[index] = ArrayDeque()
        }

        stackDefinitions.drop(1)
            .forEach {stackDefinition ->
            (1 .. numberOfStacks).forEach {index ->
                val positionOfCrateMark = (index - 1) * 4 + 1
                val crateMark = stackDefinition[positionOfCrateMark]
                if (crateMark != ' ') {
                    stacks.getValue(index).add(crateMark)
                }
            }
        }
    }

    private fun parseInputIntoMoveInstructions() {

        moveInstructions.clear()

        val split = input.indexOfFirst { it.isEmpty() }
        val moveDefinitions = input.drop(split + 1)
        moveDefinitions.forEach {
            val quantity = extractQuantity(it)
            val fromIndex = extractFromIndex(it)
            val toIndex = extractToIndex(it)
            moveInstructions.add(MoveInstruction(quantity, fromIndex, toIndex))
        }
    }

    private fun extractQuantity(moveDefinition: String)= parseInt(moveDefinition
        .substringAfter("move ")
        .substringBefore(" from "))

    private fun extractFromIndex(moveDefinition: String)= parseInt(moveDefinition
        .substringAfter(" from ")
        .substringBefore(" to "))

    private fun extractToIndex(moveDefinition: String)= parseInt(moveDefinition
        .substringAfter("to "))
}

data class MoveInstruction(
    val quantity : Int,
    val fromIndex: Int,
    val toIndex: Int
)


fun main() {
    SupplyStacks().getAnswers()
}