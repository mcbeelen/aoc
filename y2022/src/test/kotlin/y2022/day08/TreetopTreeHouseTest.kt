package y2022.day08

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import util.grid.ScreenCoordinate
import kotlin.Int.Companion.MIN_VALUE

class TreetopTreeHouseTest {

    @Test
    fun parsePatchOfTallTrees() {
        val puzzle = TreetopTreeHouse(testInput)
        assertVisible(puzzle, 0, 1)
        assertVisible(puzzle, 1, 1)
        assertVisible(puzzle, 2, 1)
        assertVisible(puzzle, 3, 2)
        assertVisible(puzzle, 4, 2)

        assertVisible(puzzle, 2, 4)

    }

    private fun assertVisible(puzzle: TreetopTreeHouse, left: Int, top: Int) {
        val tallTree = puzzle.patchOfTallTrees.getValue(ScreenCoordinate(left, top))
        assertTrue(tallTree.isVisible())
    }

    @Test
    fun examplePartOne() {
        val puzzle = TreetopTreeHouse(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(21))
    }

    @Test
    fun actualPartOne() {
        val puzzle = TreetopTreeHouse()
        assertThat(puzzle.solvePartOne(), equalTo(1533))
    }

    @Test
    fun calculateScenicScore() {
        val puzzle = TreetopTreeHouse(testInput)
        verifyScenicScore(puzzle, 2, 1, 4)
        verifyScenicScore(puzzle, 2, 3, 8)

    }

    private fun verifyScenicScore(puzzle: TreetopTreeHouse, left: Int, top: Int, expected: Int) {
        val tallTree = puzzle.patchOfTallTrees.getValue(ScreenCoordinate(left, top))
        assertEquals(expected, tallTree.getScenicScore())
    }

    @Test
    fun examplePartTwo() {
        val puzzle = TreetopTreeHouse(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(8))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = TreetopTreeHouse()
        assertThat(puzzle.solvePartTwo(), equalTo(345744))
    }

}


private const val testInput = """30373
25512
65332
33549
35390"""
