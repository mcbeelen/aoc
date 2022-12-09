package y2022.day08

import util.grid.Grid
import util.grid.ScreenCoordinate
import util.puzzle.AdventOfCodePuzzle

typealias PatchOfTallTrees = Grid<TallTree>

class TreetopTreeHouse(testInput: String = "") : AdventOfCodePuzzle(testInput) {

    val patchOfTallTrees = parseInputIntoPatchOfTallTrees()


    override fun solvePartOne() = patchOfTallTrees.values.count { it.isVisible() }

    override fun solvePartTwo() = patchOfTallTrees.values.maxOf { it.getScenicScore() }

    private fun parseInputIntoPatchOfTallTrees(): PatchOfTallTrees {

        val patchOfTallTrees = PatchOfTallTrees()

        input.forEachIndexed { rowIndex, line ->
            line.forEachIndexed { colIndex, c ->
                val coordinate = ScreenCoordinate(colIndex, rowIndex)
                val tree = TallTree(
                    coordinate,
                    c.digitToInt(),
                    patchOfTallTrees)

                patchOfTallTrees[coordinate] = tree
            }
        }

        return patchOfTallTrees
    }
}

fun main() {
    TreetopTreeHouse().getAnswers()
}