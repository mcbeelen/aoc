package y2021.day24

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class ArithmeticLogicUnitTest {

    @Test
    fun examplePartOne() {
        val puzzle = ArithmeticLogicUnitPuzzle(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartOne() {
        val puzzle = ArithmeticLogicUnitPuzzle()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = ArithmeticLogicUnitPuzzle(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = ArithmeticLogicUnitPuzzle()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """PASTE_HERE"""
